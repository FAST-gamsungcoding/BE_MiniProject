package com.gamsung.backend.domain.accomodation.controller;

import com.gamsung.backend.domain.accommodation.entity.Accommodation;
import com.gamsung.backend.domain.accommodation.repository.AccommodationRepository;
import com.gamsung.backend.domain.image.repository.ImageRepository;
import com.gamsung.backend.global.common.BaseIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AccommodationControllerTest extends BaseIntegrationTest {

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Test
    @DisplayName("상품 상세 조회")
    void getAccommodation() {
        // given
        Long id = -1L;

        Accommodation accommodation

        accommodationRepository.save(accommodation);

        String url = "/v1/accommodations/" + id;

        // when

        // then
    }


}
