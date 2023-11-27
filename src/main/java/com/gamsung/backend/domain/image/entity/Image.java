package com.gamsung.backend.domain.image.entity;

import com.gamsung.backend.domain.accomodation.entity.Accomodation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "accomodation_image")
@Getter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Integer imgType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accomodation_id")
    Accomodation accomodation;

    @Column(nullable = false)
    String url;

    @Builder
    private Image(
        Accomodation accomodation,
        Integer imgType,
        String url
    ) {
        this.accomodation = accomodation;
        this.imgType = imgType;
        this.url = url;
    }

    public void setAccomodation(Accomodation accomodation) {
        this.accomodation = accomodation;
    }
}
