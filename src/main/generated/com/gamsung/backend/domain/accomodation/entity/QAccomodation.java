package com.gamsung.backend.domain.accomodation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccomodation is a Querydsl query type for Accomodation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccomodation extends EntityPathBase<Accomodation> {

    private static final long serialVersionUID = 233581954L;

    public static final QAccomodation accomodation = new QAccomodation("accomodation");

    public final StringPath address = createString("address");

    public final StringPath contentId = createString("contentId");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.gamsung.backend.domain.image.entity.Image, com.gamsung.backend.domain.image.entity.QImage> images = this.<com.gamsung.backend.domain.image.entity.Image, com.gamsung.backend.domain.image.entity.QImage>createList("images", com.gamsung.backend.domain.image.entity.Image.class, com.gamsung.backend.domain.image.entity.QImage.class, PathInits.DIRECT2);

    public final NumberPath<Long> limitPeople = createNumber("limitPeople", Long.class);

    public final NumberPath<Long> location = createNumber("location", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public QAccomodation(String variable) {
        super(Accomodation.class, forVariable(variable));
    }

    public QAccomodation(Path<? extends Accomodation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAccomodation(PathMetadata metadata) {
        super(Accomodation.class, metadata);
    }

}

