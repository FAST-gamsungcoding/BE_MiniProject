package com.gamsung.backend.domain.cart.entity;

import com.gamsung.backend.domain.accomodation.entity.Accomodation;
import com.gamsung.backend.domain.cart.dto.response.CartFindResponse;
import com.gamsung.backend.domain.member.entity.Member;
import com.gamsung.backend.global.common.BaseTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "accomodation_id")
    @OneToOne
    private Accomodation accomodation;

    @JoinColumn(name = "member_id")
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Member member;

    @Column(name = "reservation_people")
    private Long reservationPeople;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "price")
    private Long price;


    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }






}
