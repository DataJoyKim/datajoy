package com.d1.ws.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QEntityCreateUpdateData is a Querydsl query type for EntityCreateUpdateData
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QEntityCreateUpdateData extends BeanPath<EntityCreateUpdateData> {

    private static final long serialVersionUID = 1408119998L;

    public static final QEntityCreateUpdateData entityCreateUpdateData = new QEntityCreateUpdateData("entityCreateUpdateData");

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public QEntityCreateUpdateData(String variable) {
        super(EntityCreateUpdateData.class, forVariable(variable));
    }

    public QEntityCreateUpdateData(Path<? extends EntityCreateUpdateData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEntityCreateUpdateData(PathMetadata<?> metadata) {
        super(EntityCreateUpdateData.class, metadata);
    }

}

