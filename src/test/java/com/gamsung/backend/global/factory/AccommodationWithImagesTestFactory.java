package com.gamsung.backend.global.factory;

import com.gamsung.backend.domain.accommodation.entity.Accommodation;
import com.gamsung.backend.domain.image.entity.Image;
import java.util.List;

public class AccommodationWithImagesTestFactory {

    private AccommodationWithImagesTestFactory(){}

    public static Accommodation createAccommodationWithImage(List<Image> images){
        Accommodation accommodation = Accommodation.builder()
            .name("테스트")
            .address("서울특별시 송파구 ㄱㄱㄱ")
            .location(1L)
            .description("테스트 데이터 입니다.")
            .price(160000L)
            .limitPeople(4L)
            .build();

        for(Image image : images) {
            accommodation.addImage(image);
        }

        return accommodation;
    }

    public static List<Image> createImages(){
        Image
    }


}
