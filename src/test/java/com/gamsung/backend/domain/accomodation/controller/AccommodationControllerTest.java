package com.gamsung.backend.domain.accomodation.controller;

import static com.gamsung.backend.global.factory.AccommodationWithImagesTestFactory.createAccommodationWithImage;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.gamsung.backend.domain.accommodation.entity.Accommodation;
import com.gamsung.backend.domain.accommodation.repository.AccommodationRepository;
import com.gamsung.backend.domain.image.repository.ImageRepository;
import com.gamsung.backend.global.common.BaseIntegrationTest;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

public class AccommodationControllerTest extends BaseIntegrationTest {

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Test
    @DisplayName("메인 페이지 지역별 조회 - 성공")
    void getCategoryAccommodation() throws Exception {
        // given
        List<Long> regionIds = List.of(1L,2L); // 예시로 지역 번호 1을 사용
        List<Accommodation> accommodations = IntStream.range(0, 3)
            .mapToObj(i -> createAccommodationWithImage())
            .toList();
        accommodationRepository.saveAll(accommodations);
        accommodations.stream()
            .map(Accommodation::getImages)
            .forEach(imageRepository::saveAll);

        String url = "/v1/accommodations";

        // when
        ResultActions resultActions = mockMvc.perform(get(url)
            .param("regionIds", regionIds.stream().map(String::valueOf).toArray(String[]::new))
            .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(3000))
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.size()").value(3))
            .andDo(print());
    }

    @Test
    @DisplayName("상품 상세 조회 - 성공")
    void getAccommodationDetailInfo() throws Exception {

        // given
        Accommodation accommodation = createAccommodationWithImage();
        accommodationRepository.save(accommodation);
        imageRepository.saveAll(accommodation.getImages());

        Long id = accommodation.getId();
        String url = "/v1/accommodations/" + id;

        // when
        ResultActions detailAccommodationAction = mockMvc.perform(get(url)
            .contentType(MediaType.APPLICATION_JSON));

        // then
        detailAccommodationAction
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value(3001))
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.description").value("테스트 데이터 입니다."))
            .andDo(print());
    }
}
