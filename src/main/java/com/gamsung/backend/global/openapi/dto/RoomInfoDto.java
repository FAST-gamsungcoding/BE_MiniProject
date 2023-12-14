package com.gamsung.backend.global.openapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record RoomInfoDto(@JsonProperty("response") Response response) {

    public record Response(@JsonProperty("body") Body body) {

        public record Body(@JsonProperty("items") Items items) {

            public record Items(@JsonProperty("item") List<Item> item) {

            }
            public record Item(
                @JsonProperty("roomoffseasonminfee1") String price,
                @JsonProperty("roommaxcount") String limitPeople,
                @JsonProperty("roomimg1") String roomImg1,
                @JsonProperty("roomimg2") String roomImg2,
                @JsonProperty("roomimg3") String roomImg3,
                @JsonProperty("roomimg4") String roomImg4,
                @JsonProperty("roomimg5") String roomImg5
            ) {
                @Override
                public String toString() {
                    return "DetailInfoItem{" +
                        "price='" + price + '\'' +
                        ", limitPeople='" + limitPeople + '\'' +
                        ", roomImg1='" + roomImg1 + '\'' +
                        ", roomImg2='" + roomImg2 + '\'' +
                        ", roomImg3='" + roomImg3 + '\'' +
                        ", roomImg4='" + roomImg4 + '\'' +
                        ", roomImg5='" + roomImg5 + '\'' +
                        '}';
                }
            }
        }
    }
}
