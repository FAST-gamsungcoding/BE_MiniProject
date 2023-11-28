package com.gamsung.backend.domain.order.entity;

import com.gamsung.backend.global.common.BaseTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor//(access = AccessLevel.PROTECTED)
public class Order extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "member_id",nullable = false)
    private long  memberId;

    @Column(name = "accommodation_id",nullable = false)
    private long accommodationId;

    @Column(name = "people_number",nullable = false)
    private int peopleNumber;

    @Column(name = "start_date",nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date",nullable = false)
    private LocalDate endDate;

    @Column(name = "representative_name",nullable = false)
    private String representativeName;

    @Column(name = "representative_email",nullable = false)
    private String representativeEmail;

    @Column(name = "order_price",nullable = false)
    private long orderPrice;

    private Order(long memberId, long accommodationId, int peopleNumber,
                  LocalDate startDate, LocalDate endDate, String representativeName,
                  String representativeEmail, long orderPrice) {
        this.memberId = memberId;
        this.accommodationId = accommodationId;
        this.peopleNumber = peopleNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.representativeName = representativeName;
        this.representativeEmail = representativeEmail;
        this.orderPrice = orderPrice;
    }

    public static Order of(long memberId, long accommodationId, int peopleNumber,
                           LocalDate startDate, LocalDate endDate, String representativeName,
                           String representativeEmail, long orderPrice) {
        return new Order(memberId, accommodationId, peopleNumber,
                startDate, endDate, representativeName,
                representativeEmail, orderPrice);
    }
}
