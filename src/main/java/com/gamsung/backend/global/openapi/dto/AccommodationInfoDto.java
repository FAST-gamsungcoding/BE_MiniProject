package com.gamsung.backend.global.openapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record AccommodationInfoDto(@JsonProperty("response") Response response) {

    public record Response(@JsonProperty("body") Body body) {

        public record Body(@JsonProperty("items") Items items) {

            public record Items(@JsonProperty("item") List<Item> item) {}

            public record Item(
                @JsonProperty("title") String name,
                @JsonProperty("areacode") String location,
                @JsonProperty("addr1") String address,
                @JsonProperty("firstimage") String accommodationImage,
                @JsonProperty("contentid") String contentId
            ) {
                @Override
                public String toString() {
                    return "AccommodationInfoItem{" +
                        "addr1='" + address + '\'' +
                        ", areaCode='" + location + '\'' +
                        ", contentId='" + contentId + '\'' +
                        ", title'" + name + '\'' +
                        ", firstImage='" + accommodationImage + '\'' +
                        '}';
                }
            }
        }
    }
}
