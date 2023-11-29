package com.gamsung.backend.domain.accommodation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccommodation is a Querydsl query type for Accommodation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccommodation extends EntityPathBase<Accommodation> {

    private static final long serialVersionUID = -2088461082L;

    public static final QAccommodation accommodation = new QAccommodation("accommodation");

    public final com.gamsung.backend.global.common.QBaseTime _super = new com.gamsung.backend.global.common.QBaseTime(this);

    public final StringPath address = createString("address");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.gamsung.backend.domain.image.entity.Image, com.gamsung.backend.domain.image.entity.QImage> images = this.<com.gamsung.backend.domain.image.entity.Image, com.gamsung.backend.domain.image.entity.QImage>createList("images", com.gamsung.backend.domain.image.entity.Image.class, com.gamsung.backend.domain.image.entity.QImage.class, PathInits.DIRECT2);

    public final NumberPath<Long> limitPeople = createNumber("limitPeople", Long.class);

    public final NumberPath<Long> location = createNumber("location", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public QAccommodation(String variable) {
        super(Accommodation.class, forVariable(variable));
    }

    public QAccommodation(Path<? extends Accommodation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAccommodation(PathMetadata metadata) {
        super(Accommodation.class, metadata);
    }

}

