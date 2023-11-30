package com.gamsung.backend.global.openapi.service;

import static com.gamsung.backend.global.openapi.UrlConstants.ACCOMMODATION_PATH;
import static com.gamsung.backend.global.openapi.UrlConstants.ARRANGE;
import static com.gamsung.backend.global.openapi.UrlConstants.CONTENT_TYPE_ID;
import static com.gamsung.backend.global.openapi.UrlConstants.DEFAULT_NUM_OF_ROWS;
import static com.gamsung.backend.global.openapi.UrlConstants.DESCRIPTION_PATH;
import static com.gamsung.backend.global.openapi.UrlConstants.END_POINT;
import static com.gamsung.backend.global.openapi.UrlConstants.MOBILE_APP;
import static com.gamsung.backend.global.openapi.UrlConstants.MOBILE_OS;
import static com.gamsung.backend.global.openapi.UrlConstants.PAGE_NO;
import static com.gamsung.backend.global.openapi.UrlConstants.ROOM_PATH;
import static com.gamsung.backend.global.openapi.UrlConstants.TYPE;
import static com.gamsung.backend.global.openapi.UrlConstants.YES;

import com.gamsung.backend.domain.accommodation.entity.Accommodation;
import com.gamsung.backend.domain.accommodation.repository.AccommodationRepository;
import com.gamsung.backend.domain.image.entity.Image;
import com.gamsung.backend.domain.image.repository.ImageRepository;
import com.gamsung.backend.global.openapi.dto.AccommodationDescriptionDto;
import com.gamsung.backend.global.openapi.dto.AccommodationDescriptionDto.Response.Body;
import com.gamsung.backend.global.openapi.dto.AccommodationInfoDto;
import com.gamsung.backend.global.openapi.dto.AccommodationInfoDto.Response.Body.Item;
import com.gamsung.backend.global.openapi.dto.RoomInfoDto;
import com.gamsung.backend.global.openapi.dto.RoomInfoDto.Response;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class OpenApiService {

    private final AccommodationRepository accommodationRepository;
    private final ImageRepository imageRepository;

    @Value("${open-api.key}")
    private String decodeApiKey;

    public void getAccommodationInfo() {
        processAccommodationInfo();
    }

    private void processAccommodationInfo() {
        String accommodationInfoUrl = buildApiUrl(
            END_POINT + ACCOMMODATION_PATH
            , "numOfRows", DEFAULT_NUM_OF_ROWS
            , "pageNo", PAGE_NO
            , "mobileOS", MOBILE_OS
            , "MobileApp", MOBILE_APP
            , "_type", TYPE
            , "arrange", ARRANGE
            , "serviceKey", decodeApiKey
        );

        AccommodationInfoDto response =
            performApiRequest(accommodationInfoUrl, AccommodationInfoDto.class);
        if (response != null && response.response() != null) {
            try {
                for (AccommodationInfoDto.Response.Body.Item item :
                    response.response().body().items().item()) {

                    if (item.accommodationImage().isEmpty() || item.location().isEmpty()
                        || item.contentId().isEmpty()
                        || item.address().isEmpty() || item.name().isEmpty() || !endsWithJpgOrJpeg(
                        item.accommodationImage())) {
                        continue;
                    }

                    processAccommodationDescription(item);
                }
            } catch (Exception e) {
                System.out.println("AccommodationInfo Error : " + e.getMessage());
            }
        }
    }

    private void processAccommodationDescription(Item accommodationInfoitem) {
        String accommodationDescriptionUrl = buildApiUrl(
            END_POINT + DESCRIPTION_PATH
            , "numOfRows", DEFAULT_NUM_OF_ROWS
            , "mobileOS", MOBILE_OS
            , "MobileApp", MOBILE_APP
            , "contentId", accommodationInfoitem.contentId()
            , "contentTypeId", CONTENT_TYPE_ID
            , "overviewYN", YES
            , "_type", TYPE
            , "serviceKey", decodeApiKey
        );

        AccommodationDescriptionDto response =
            performApiRequest(accommodationDescriptionUrl, AccommodationDescriptionDto.class);
        if (response != null && response.response() != null) {
            try {
                AccommodationDescriptionDto.Response.Body.Item item =
                    response.response().body().items().item().get(0);
                if (item.overview().isEmpty()) {
                    return;
                }

                processRoomInfo(accommodationInfoitem, item);
            } catch (Exception e) {
                System.out.println("Accommodation Description Error : " + e.getMessage());
            }
        }
    }

    private void processRoomInfo(Item accommodationInfoItem,
        Body.Item accommodationDescriptionItem) {
        String roomInfoUrl = buildApiUrl(
            END_POINT + ROOM_PATH
            , "MobileOS", MOBILE_OS
            , "MobileApp", MOBILE_APP
            , "contentId", accommodationInfoItem.contentId()
            , "contentTypeId", CONTENT_TYPE_ID
            , "_type", TYPE
            , "serviceKey", decodeApiKey
        );

        RoomInfoDto response = performApiRequest(roomInfoUrl, RoomInfoDto.class);
        if (response != null && response.response() != null) {
            try {
                RoomInfoDto.Response.Body.Item item =
                    response.response().body().items().item().get(0);

                if (item.limitPeople().equals("0") || item.price().equals("0")) {
                    return;
                }

                saveAccommodationAndImages(accommodationInfoItem, accommodationDescriptionItem,
                    item);
            } catch (Exception e) {
                System.out.println("RoomInfo Error : " + e.getMessage());
            }
        }
    }

    @Transactional
    public void saveAccommodationAndImages(
        Item accommodationInfoItem,
        Body.Item accommodationDescriptionItem,
        Response.Body.Item roomInfoItem
    ) {

        Accommodation accommodation = Accommodation.builder()
            .name(accommodationInfoItem.name())
            .description(accommodationDescriptionItem.overview())
            .address(accommodationInfoItem.address())
            .location(Long.valueOf(accommodationInfoItem.location()))
            .limitPeople(Long.valueOf(roomInfoItem.limitPeople()))
            .price(Long.valueOf(roomInfoItem.price()))
            .build();

        // Accomodation Info 저장
        accommodationRepository.save(accommodation);

        // Accomodation Image 저장
        Image accoommodationImage = Image.builder()
            .imgType(1)
            .accommodation(accommodation)
            .url(accommodationInfoItem.accommodationImage())
            .build();

        imageRepository.save(accoommodationImage);
        accommodation.addImage(accoommodationImage);

        // Room Images 저장
        List<String> imageUrls =
            Arrays.asList(roomInfoItem.roomImg1(), roomInfoItem.roomImg2(), roomInfoItem.roomImg3(),
                roomInfoItem.roomImg4(), roomInfoItem.roomImg5());

        List<String> nonEmptyImageUrls = imageUrls.stream()
            .filter(this::isNotBlank)
            .toList();

        for (String url : nonEmptyImageUrls) {
            Image roomImage = Image.builder()
                .url(url)
                .accommodation(accommodation)
                .imgType(2)
                .build();
            imageRepository.save(roomImage);
        }

//        printSaveAccommodationInfo(accommodation);
    }

    private boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private <T> T performApiRequest(String url, Class<T> responseType) {
        return new RestTemplate().getForObject(url, responseType);
    }

    private String buildApiUrl(String baseUrl, String... queryParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl);
        for (int i = 0; i < queryParams.length; i += 2) {
            String key = queryParams[i];
            String value = queryParams[i + 1];
            builder.queryParam(key, value);
        }
        return builder.build().toUriString();
    }

    private boolean endsWithJpgOrJpeg(String url) {
        int lastDotIndex = url.lastIndexOf(".");
        if (lastDotIndex == -1 || lastDotIndex == url.length() - 1) {
            return false;
        }
        String extension = url.substring(lastDotIndex + 1).toLowerCase();
        return extension.equals("jpg") || extension.equals("jpeg");
    }

    private void printSaveAccommodationInfo(Accommodation accommodation) {
        System.out.println("Address: " + accommodation.getAddress());
        System.out.println("AreaCode: " + accommodation.getLocation());
        System.out.println("Title: " + accommodation.getName());
        System.out.println("RoomFee: " + accommodation.getPrice());
        System.out.println("MaxCount: " + accommodation.getLimitPeople());
        System.out.println("description: " + accommodation.getDescription());
        System.out.println("Image: ");
        accommodation.getImages().stream()
            .forEach(image1 -> System.out.println(
                "Type: " + image1.getImgType() + ", URL: "
                    + image1.getUrl()));
        System.out.println("---------------------------------");
    }
}
