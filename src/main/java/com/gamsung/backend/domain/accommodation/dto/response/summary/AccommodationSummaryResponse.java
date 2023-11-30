package com.gamsung.backend.domain.accommodation.dto.response.summary;

import com.gamsung.backend.domain.accommodation.entity.Accommodation;
import lombok.Builder;

@Builder
public record AccommodationSummaryResponse(
        Long accommodationId,
        String accommodationName,
        String shortAddress,
        Long accommodationPrice,
        String accommodationImg
) {
    public static AccommodationSummaryResponse from(Accommodation accommodation , String accommodationImg){
        return AccommodationSummaryResponse.builder()
            .accommodationId(accommodation.getId())
            .accommodationName(accommodation.getName())
            .shortAddress(makeShortAddress(accommodation.getAddress()))
            .accommodationPrice(accommodation.getPrice())
            .accommodationImg(accommodationImg)
            .build();
    }

    private static String makeShortAddress(String address) {
        String[] parts = address.split(" ");
        return parts.length > 1 ? parts[0] + " " + parts[1] : address;
    }
}
