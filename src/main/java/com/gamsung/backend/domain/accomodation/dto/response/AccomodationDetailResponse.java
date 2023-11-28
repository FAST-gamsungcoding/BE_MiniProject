package com.gamsung.backend.domain.accomodation.dto.response;

import com.gamsung.backend.domain.accomodation.entity.Accommodation;
import lombok.Builder;

import java.util.List;

@Builder
public record AccomodationDetailResponse(
    String accommodationName,
    String description,
    String address,
    Long limitPeople,
    Long accomodationPrice,
    String accomodationImg,
    List<String> roomImg
) {

    public static AccomodationDetailResponse from(
        Accommodation accomodation,
        String accomodationImg,
        List<String> roomImages
    ) {
        return AccomodationDetailResponse.builder()
            .accommodationName(accomodation.getName())
            .description(accomodation.getDescription())
            .address(accomodation.getAddress())
            .limitPeople(accomodation.getLimitPeople())
            .accomodationPrice(accomodation.getPrice())
            .accomodationImg(accomodationImg)
            .roomImg(roomImages)
            .build();
    }
}
