package com.gamsung.backend.global.openapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AccommodationDescriptionDto(@JsonProperty("response") Response response) {

    public record Response(@JsonProperty("body") Body body) {

        public record Body(@JsonProperty("items") Items items) {

            public record Items(@JsonProperty("item") List<Item> item) {

            }

            public record Item(
                @JsonProperty("overview") String overview
            ) {

            }
        }
    }


}