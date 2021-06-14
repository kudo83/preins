package ac.encg.preins.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVerificationToken is a Querydsl query type for VerificationToken
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QVerificationToken extends EntityPathBase<VerificationToken> {

    private static final long serialVersionUID = 1411297409L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVerificationToken verificationToken = new QVerificationToken("verificationToken");

    public final DatePath<java.sql.Date> createdDate = createDate("createdDate", java.sql.Date.class);

    public final DatePath<java.sql.Date> expiryDate = createDate("expiryDate", java.sql.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath token = createString("token");

    public final QUser user;

    public QVerificationToken(String variable) {
        this(VerificationToken.class, forVariable(variable), INITS);
    }

    public QVerificationToken(Path<? extends VerificationToken> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVerificationToken(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVerificationToken(PathMetadata metadata, PathInits inits) {
        this(VerificationToken.class, metadata, inits);
    }

    public QVerificationToken(Class<? extends VerificationToken> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

