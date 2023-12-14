package com.gamsung.backend.domain.cart.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gamsung.backend.domain.cart.entity.Cart;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CartFindResponse {

 private Long cartId;
 private Long accommodationId;
 private String accommodationName;
 private String address;
 private LocalDate startDate;
 private LocalDate endDate;
 private Long peopleNumber;
 private Long cartPrice;
 private String accommodationImg;
 private Boolean accommodationSoldOut;

 private CartFindResponse() {}

 private static class Builder {
  private Long cartId;
  private Long accommodationId;
  private String accommodationName;
  private String address;
  private LocalDate startDate;
  private LocalDate endDate;
  private Long peopleNumber;
  private Long cartPrice;
  private String accommodationImg;
  private Boolean accommodationSoldOut;

  private Builder() {}

  private static Builder builder() {
   return new Builder();
  }

  private Builder cartId(Long cartId) {
   this.cartId = cartId;
   return this;
  }

  private Builder accommodationId(Long accommodationId) {
   this.accommodationId = accommodationId;
   return this;
  }

  private Builder accommodationName(String accommodationName) {
   this.accommodationName = accommodationName;
   return this;
  }

  private Builder address(String address) {
   this.address = address;
   return this;
  }

  private Builder startDate(LocalDate startDate) {
   this.startDate = startDate;
   return this;
  }

  private Builder endDate(LocalDate endDate) {
   this.endDate = endDate;
   return this;
  }

  private Builder peopleNumber(Long peopleNumber) {
   this.peopleNumber = peopleNumber;
   return this;
  }

  private Builder cartPrice(Long cartPrice) {
   this.cartPrice = cartPrice;
   return this;
  }

  private Builder accommodationImg(String accommodationImg) {
   this.accommodationImg = accommodationImg;
   return this;
  }

  private Builder accommodationSoldOut(Boolean accommodationSoldOut) {
   this.accommodationSoldOut = accommodationSoldOut;
   return this;
  }

  private CartFindResponse build() {
   CartFindResponse response = new CartFindResponse();
   response.cartId = this.cartId;
   response.accommodationId = this.accommodationId;
   response.accommodationName = this.accommodationName;
   response.address = this.address;
   response.startDate = this.startDate;
   response.endDate = this.endDate;
   response.peopleNumber = this.peopleNumber;
   response.cartPrice = this.cartPrice;
   response.accommodationImg = this.accommodationImg;
   response.accommodationSoldOut = this.accommodationSoldOut;
   return response;
  }
 }

 public static CartFindResponse fromEntity(Cart cart) {
  return Builder.builder()
          .cartId(cart.getId())
          .accommodationId(cart.getAccommodation().getId())
          .accommodationName(cart.getAccommodation().getName())
          .address(cart.getAccommodation().getAddress())
          .startDate(cart.getStartDate())
          .endDate(cart.getEndDate())
          .peopleNumber(cart.getReservationPeople())
          .cartPrice(cart.getPrice())
          .accommodationImg(String.valueOf(cart.getAccommodation().getImages().get(0).getUrl()))
          .accommodationSoldOut(cart.getIsDeleted())
          .build();
 }
}