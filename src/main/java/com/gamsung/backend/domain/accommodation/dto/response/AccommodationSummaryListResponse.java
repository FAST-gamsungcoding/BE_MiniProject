package com.gamsung.backend.domain.accommodation.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record AccommodationSummaryListResponse(
    List<AccommodationSummaryResponse> accommodations,
    Integer totalPage
) {

    public static AccommodationSummaryListResponse from(List<AccommodationSummaryResponse> accommodations,int totalPage){
        return AccommodationSummaryListResponse.builder()
            .accommodations(accommodations)
            .totalPage(totalPage)
            .build();
    }
}
