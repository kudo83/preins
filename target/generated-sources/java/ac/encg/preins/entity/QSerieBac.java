package ac.encg.preins.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSerieBac is a Querydsl query type for SerieBac
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSerieBac extends EntityPathBase<SerieBac> {

    private static final long serialVersionUID = -719851099L;

    public static final QSerieBac serieBac = new QSerieBac("serieBac");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lib = createString("lib");

    public QSerieBac(String variable) {
        super(SerieBac.class, forVariable(variable));
    }

    public QSerieBac(Path<? extends SerieBac> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSerieBac(PathMetadata metadata) {
        super(SerieBac.class, metadata);
    }

}

