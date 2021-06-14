package ac.encg.preins.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -705481976L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final QAdmis admis;

    public final BooleanPath enabled = createBoolean("enabled");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QInscrit inscrit;

    public final StringPath nom = createString("nom");

    public final StringPath password = createString("password");

    public final SetPath<Role, QRole> roles = this.<Role, QRole>createSet("roles", Role.class, QRole.class, PathInits.DIRECT2);

    public final StringPath username = createString("username");

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.admis = inits.isInitialized("admis") ? new QAdmis(forProperty("admis"), inits.get("admis")) : null;
        this.inscrit = inits.isInitialized("inscrit") ? new QInscrit(forProperty("inscrit"), inits.get("inscrit")) : null;
    }

}

