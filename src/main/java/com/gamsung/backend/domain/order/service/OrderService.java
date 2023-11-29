package com.gamsung.backend.domain.order.service;

import com.gamsung.backend.domain.cart.repository.CartRepository;
import com.gamsung.backend.domain.order.dto.request.OrderAccommodationRequest;
import com.gamsung.backend.domain.order.dto.response.BookDateAvailableResponse;
import com.gamsung.backend.domain.order.dto.response.OrderAccommodationResponse;
import com.gamsung.backend.domain.order.dto.response.OrderResponse;
import com.gamsung.backend.domain.order.dto.response.SoldOutOrder;
import com.gamsung.backend.domain.order.entity.Order;
import com.gamsung.backend.domain.order.exception.OrderSoldOutException;
import com.gamsung.backend.domain.order.repository.OrderRepository;
import com.gamsung.backend.global.exception.BookDateUnavailableException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final int ID_ZERO = 0;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    @Transactional
    public OrderAccommodationResponse orderAccommodation(List<OrderAccommodationRequest> orderAccommodationRequestList,
                                                         long userId) {
        List<Order> orderList = new ArrayList<>();
        List<Long> deleteCartIdList = new ArrayList<>();
        List<SoldOutOrder> soldOutOrders = new ArrayList<>();

        for (OrderAccommodationRequest orderRequest : orderAccommodationRequestList) {
            Order order = Order.of(
                    userId,
                    orderRequest.getAccommodationId(), orderRequest.getPeopleNumber(),
                    orderRequest.getStartDate(), orderRequest.getEndDate(),
                    orderRequest.getRepresentativeName(), orderRequest.getRepresentativeEmail(),
                    orderRequest.getOrderPrice()
            );

            if (orderRepository.existsByAccommodationIdAndStartDateBeforeAndEndDateAfter(
                    order.getAccommodationId(), order.getEndDate(), order.getStartDate()).isPresent()
            ) {
                soldOutOrders.add(SoldOutOrder.from(order.getAccommodationId(),
                        order.getStartDate(), order.getEndDate()));
                continue;
            }

            orderList.add(order);

            if(isFromCart(orderRequest.getCartId())) continue;
            deleteCartIdList.add(orderRequest.getCartId());
        }

        if(!soldOutOrders.isEmpty()){
            throw new OrderSoldOutException(soldOutOrders);
        }

        orderRepository.saveAll(orderList);
        cartRepository.deleteAllByIdInBatch(deleteCartIdList);

        return OrderAccommodationResponse.create();
    }

    @Transactional
    public BookDateAvailableResponse checkBookDate(long id, LocalDate startDate, LocalDate endDate) {
        Optional<Order> soldOrder = orderRepository.existsByAccommodationIdAndStartDateBeforeAndEndDateAfter(id, startDate, endDate);
        if (soldOrder.isPresent()) {
            throw new BookDateUnavailableException();
        }
        return BookDateAvailableResponse.create();
    }

    public List<OrderResponse> getMemberOrdersList(Pageable pageable, long id) {
        List<Order> orderList = orderRepository.findByMemberIdOrderByCreatedAtDesc(id, pageable);

        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orderList) {
            OrderResponse orderResponse = OrderResponse.from(
                    order.getCreatedAt(),
                    order.getAccommodationId(),
                    order.getAccommodation().getName(),
                    order.getAccommodation().getImages().get(0).getUrl(),
                    order.getPeopleNumber(),
                    order.getStartDate(),
                    order.getEndDate(),
                    order.getRepresentativeName(),
                    order.getOrderPrice());
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }

    private boolean isFromCart(long cartId) {
        return cartId == ID_ZERO;
    }

}
