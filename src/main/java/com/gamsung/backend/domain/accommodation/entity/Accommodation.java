package com.gamsung.backend.domain.accommodation.entity;

import com.gamsung.backend.domain.image.entity.Image;
import com.gamsung.backend.global.common.BaseTime;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Accommodation extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    String description;

    @Column(nullable = false)
    Long location;

    @Column(nullable = false)
    String address;

    @Column(nullable = false)
    Long limitPeople;

    @Column(nullable = false)
    Long price;

    @OneToMany(
        fetch = FetchType.LAZY, mappedBy = "accommodation",
        cascade = CascadeType.REMOVE, orphanRemoval = true
    )
    List<Image> images = new ArrayList<>();

    @Builder
    private Accommodation(
        String name,
        String description,
        Long location,
        String address,
        Long limitPeople,
        Long price
    ) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.address = address;
        this.limitPeople = limitPeople;
        this.price = price;
    }

    public void addImage(Image image) {
        this.images.add(image);
    }
}
