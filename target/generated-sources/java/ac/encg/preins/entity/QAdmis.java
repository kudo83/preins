package ac.encg.preins.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAdmis is a Querydsl query type for Admis
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAdmis extends EntityPathBase<Admis> {

    private static final long serialVersionUID = -414014537L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAdmis admis = new QAdmis("admis");

    public final StringPath cin = createString("cin");

    public final StringPath cne = createString("cne");

    public final DateTimePath<java.sql.Timestamp> dateCREAT = createDateTime("dateCREAT", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> dateModif = createDateTime("dateModif", java.sql.Timestamp.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nom = createString("nom");

    public final QUser user;

    public final StringPath userCreat = createString("userCreat");

    public final StringPath userModif = createString("userModif");

    public QAdmis(String variable) {
        this(Admis.class, forVariable(variable), INITS);
    }

    public QAdmis(Path<? extends Admis> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAdmis(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAdmis(PathMetadata metadata, PathInits inits) {
        this(Admis.class, metadata, inits);
    }

    public QAdmis(Class<? extends Admis> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

