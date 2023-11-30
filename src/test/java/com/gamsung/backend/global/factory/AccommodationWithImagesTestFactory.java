package com.gamsung.backend.global.factory;

import com.gamsung.backend.domain.accommodation.entity.Accommodation;
import com.gamsung.backend.domain.image.entity.Image;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AccommodationWithImagesTestFactory {

    private AccommodationWithImagesTestFactory() {
    }

    public static Accommodation createAccommodationWithImage() {
        Accommodation accommodation = Accommodation.builder()
            .name("테스트")
            .address("테스트 주소")
            .location(1L)
            .description("테스트 데이터 입니다.")
            .price(160000L)
            .limitPeople(4L)
            .build();

        Image accommodationImage = createAccommodationImageWithAccommodation(accommodation);
        accommodation.addImage(accommodationImage);

        createRoomImagesWithAccommodation(accommodation)
            .forEach(accommodation::addImage);

        return accommodation;
    }

    public static List<Image> createRoomImagesWithAccommodation(Accommodation accommodation) {
        return IntStream.range(0, 5)
            .mapToObj(i ->
                Image.builder()
                    .url("room_test_image" + i + ".jpg")
                    .imgType(2)
                    .accommodation(accommodation)
                    .build()
            )
            .collect(Collectors.toList());
    }

    public static Image createAccommodationImageWithAccommodation(Accommodation accommodation) {
        return Image.builder()
            .url("accommodation_test_image.jpg")
            .imgType(1)
            .accommodation(accommodation)
            .build();
    }


}
