package com.gamsung.backend.domain.accomodation.dto.response;

import com.gamsung.backend.domain.accomodation.entity.Accomodation;
import java.util.List;
import lombok.Builder;

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
        Accomodation accomodation,
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
