package com.gamsung.backend.domain.accomodation.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Accomodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    String description;

    @Column(nullable = false)
    String location;

    @Column(nullable = false)
    String address;

    @Column(nullable = false)
    String limitPeople;

    @Column(nullable = false)
    String price;

    @OneToMany(
        fetch = FetchType.LAZY, mappedBy = "product",
        cascade = CascadeType.PERSIST, orphanRemoval = true
    )
    List<Image> images = new ArrayList<>();

    @Builder
    private Product(
        String name,
        String description,
        String location,
        String address,
        String limitPeople,
        String price
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
