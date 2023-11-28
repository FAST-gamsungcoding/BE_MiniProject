package com.gamsung.backend.domain.cart.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gamsung.backend.domain.cart.entity.Cart;
import com.gamsung.backend.domain.image.entity.Image;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CartFindResponse {
   /** @Serial
    private static final long serialVersionUID = 533152613397714180L;

   **/
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
