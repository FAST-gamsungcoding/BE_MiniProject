package com.gamsung.backend.domain.accommodation.dto.response.summary;

import lombok.Builder;

import java.util.List;

@Builder
public record AccommodationSummaryListResponse(
    List<AccommodationSummaryResponse> accommodations,
    Integer totalPage,
    Integer pageNumber

) {

    public static AccommodationSummaryListResponse to(List<AccommodationSummaryResponse> accommodations,int totalPage,int pageNumber){
        return AccommodationSummaryListResponse.builder()
            .accommodations(accommodations)
            .totalPage(totalPage)
            .pageNumber(pageNumber+1)
            .build();
    }
}
