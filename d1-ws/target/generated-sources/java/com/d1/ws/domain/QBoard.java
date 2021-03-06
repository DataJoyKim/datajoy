package com.d1.ws.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 1406100698L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final ListPath<Board, QBoard> childList = this.<Board, QBoard>createList("childList", Board.class, QBoard.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final QEntityCreateUpdateData entityCreateUpdateData;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QBoard parent;

    public final QProject project;

    public final EnumPath<com.d1.ws.code.BoardStatus> status = createEnum("status", com.d1.ws.code.BoardStatus.class);

    public final StringPath title = createString("title");

    public final QUser user;

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.entityCreateUpdateData = inits.isInitialized("entityCreateUpdateData") ? new QEntityCreateUpdateData(forProperty("entityCreateUpdateData")) : null;
        this.parent = inits.isInitialized("parent") ? new QBoard(forProperty("parent"), inits.get("parent")) : null;
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project"), inits.get("project")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

