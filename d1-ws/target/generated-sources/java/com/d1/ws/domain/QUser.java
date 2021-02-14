package com.d1.ws.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1893734569L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final StringPath bio = createString("bio");

    public final StringPath company = createString("company");

    public final StringPath email = createString("email");

    public final DateTimePath<java.time.LocalDateTime> endDate = createDateTime("endDate", java.time.LocalDateTime.class);

    public final QEntityCreateUpdateData entityCreateUpdateData;

    public final StringPath groupCd = createString("groupCd");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath location = createString("location");

    public final StringPath password = createString("password");

    public final StringPath phoneNo = createString("phoneNo");

    public final ArrayPath<byte[], Byte> profileImage = createArray("profileImage", byte[].class);

    public final StringPath residentNo = createString("residentNo");

    public final DateTimePath<java.time.LocalDateTime> staDate = createDateTime("staDate", java.time.LocalDateTime.class);

    public final StringPath userNm = createString("userNm");

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUser(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUser(PathMetadata<?> metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.entityCreateUpdateData = inits.isInitialized("entityCreateUpdateData") ? new QEntityCreateUpdateData(forProperty("entityCreateUpdateData")) : null;
    }

}

