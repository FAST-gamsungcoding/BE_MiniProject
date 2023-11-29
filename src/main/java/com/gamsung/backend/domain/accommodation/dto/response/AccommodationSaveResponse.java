package com.gamsung.backend.domain.accommodation.dto.response;

public record AccommodationSaveResponse(
    String name,
    String description,
    String address,
    Long limitPeople,
    Long price
) {

}
