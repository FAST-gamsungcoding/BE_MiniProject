package com.gamsung.backend.domain.order.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = 1182973286L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final com.gamsung.backend.global.common.QBaseTime _super = new com.gamsung.backend.global.common.QBaseTime(this);

    public final com.gamsung.backend.domain.accommodation.entity.QAccommodation accommodation;

    public final NumberPath<Long> accommodationId = createNumber("accommodationId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.gamsung.backend.domain.member.entity.QMember member;

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Long> orderPrice = createNumber("orderPrice", Long.class);

    public final NumberPath<Integer> peopleNumber = createNumber("peopleNumber", Integer.class);

    public final StringPath representativeEmail = createString("representativeEmail");

    public final StringPath representativeName = createString("representativeName");

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.accommodation = inits.isInitialized("accommodation") ? new com.gamsung.backend.domain.accommodation.entity.QAccommodation(forProperty("accommodation")) : null;
        this.member = inits.isInitialized("member") ? new com.gamsung.backend.domain.member.entity.QMember(forProperty("member")) : null;
    }

}

