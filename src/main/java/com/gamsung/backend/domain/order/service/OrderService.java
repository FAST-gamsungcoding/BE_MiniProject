package com.gamsung.backend.domain.order.service;

import com.gamsung.backend.domain.order.dto.request.OrderAccommodationRequest;
import com.gamsung.backend.domain.order.dto.response.BookDateAvailableResponse;
import com.gamsung.backend.domain.order.dto.response.OrderAccommodationResponse;
import com.gamsung.backend.domain.order.dto.response.OrderResponse;
import com.gamsung.backend.domain.order.dto.response.SoldOutOrder;
import com.gamsung.backend.domain.order.entity.Order;
import com.gamsung.backend.domain.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private static final int NO_ID_VALUE = 1;
    OrderRepository orderRepository;

    @Transactional
    public OrderAccommodationResponse orderAccommodation(List<OrderAccommodationRequest> orderAccommodationRequestList,
                                                         long userId) {
//      List<Orders> ordersList = orderRequestListToOrdersList(orderAccommodationRequestList);
        List<Order> orderList = new ArrayList<>();
        List<Long> cartIdList = new ArrayList<>();
        List<SoldOutOrder> soleOutOrders = new ArrayList<>();

        for (OrderAccommodationRequest orderAccommodationRequest : orderAccommodationRequestList) {
            Order order = Order.of(
                    userId,
                    orderAccommodationRequest.getAccommodationId(),
                    orderAccommodationRequest.getPeopleNumber(),
                    orderAccommodationRequest.getStartDate(),
                    orderAccommodationRequest.getEndDate(),
                    orderAccommodationRequest.getRepresentativeName(),
                    orderAccommodationRequest.getRepresentativeEmail(),
                    orderAccommodationRequest.getOrderPrice()
            );

//            결제 가능한지 품절 확인
//            if (!orderRepository.existsByAccommodationIdAndStartDateBeforeAndEndDateAfter(
//                    order.getAccommodationId(), order.getEndDate(), order.getStartDate())
//            ) {
//                soleOutOrders.add(SoldOutOrder.from(order.getAccommodationId(),
//                        order.getStartDate(), order.getEndDate()));
//                continue;
//            }

            orderList.add(order);
            if(orderAccommodationRequest.getCartId() == NO_ID_VALUE) continue;
            cartIdList.add(orderAccommodationRequest.getCartId());
        }

        orderRepository.saveAll(orderList);
        // cartRepository.deleteAllById(cartIdList);
        return OrderAccommodationResponse.create();
    }

    @Transactional
    public BookDateAvailableResponse checkBookDate(long id, LocalDate startDate, LocalDate endDate) {
        if (!orderRepository.existsByAccommodationIdAndStartDateBeforeAndEndDateAfter(id, startDate, endDate)) {

        }

        //
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



//        //숙박id로 필요한 정보들 가져오기(미정)
//        List<Accommodation> accommodations = accommodationRepository.findByIdIn(accommodationIds);
//        List<AccommodationImage> accommodationImages = accommodationImageRepository.findByAccommodationIdInAndImgType(accommodationIds, 1);
//        return new PageImpl<>(orderResponses, pageable, orderPages.getTotalElements());


    //엔티티 변환하는 함수 따로 만들어서 빼야 하나? ..그러면 cart_id 0 판별은 어떻게?
//    private List<Orders> orderRequestListToOrdersList(List<OrderAccommodationRequest> orderAccommodationRequestList) {
//        List<Orders> ordersList = new ArrayList<>();
//
//        for (OrderAccommodationRequest orderAccommodationRequest : orderAccommodationRequestList) {
//            Orders orders = Orders.of(
//                    1, // 추후 수정
//                    orderAccommodationRequest.getAccommodationId(),
//                    orderAccommodationRequest.getPeopleNumber(),
//                    orderAccommodationRequest.getStartDate(),
//                    orderAccommodationRequest.getEndDate(),
//                    orderAccommodationRequest.getRepresentativeName(),
//                    orderAccommodationRequest.getRepresentativeEmail(),
//                    orderAccommodationRequest.getOrderPrice()
//            );
//            ordersList.add(orders);
//        }
//
//        return ordersList;
//    }

}
