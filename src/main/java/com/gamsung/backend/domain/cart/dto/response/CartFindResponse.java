package com.gamsung.backend.domain.cart.dto.response;

import com.gamsung.backend.domain.cart.entity.Cart;
import com.gamsung.backend.domain.image.entity.Image;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class CartFindResponse {
    private Long cartId;
    private Long accommodationId;
    private String accommodationName;
    private String address;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long peopleNumber;
    private Long cartPrice;
    private List<Image> accommodationImg;
    private Boolean accommodationSoldOut;


    public static CartFindResponse fromEntity(Cart cart) {
        return CartFindResponse.builder()
                .cartId(cart.getId())
                .accommodationId(cart.getAccomodation().getId())
                .accommodationName(cart.getAccomodation().getName())
                .address(cart.getAccomodation().getAddress())
                .startDate(cart.getStartDate())
                .endDate(cart.getEndDate())
                .peopleNumber(cart.getReservationPeople())
                .cartPrice(cart.getPrice())
                .accommodationImg(cart.getAccomodation().getImages())
                .accommodationSoldOut(cart.getIsDeleted())
                .build();
    }


}
