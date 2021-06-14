package ac.encg.preins.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEtape is a Querydsl query type for Etape
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEtape extends EntityPathBase<Etape> {

    private static final long serialVersionUID = -409855126L;

    public static final QEtape etape = new QEtape("etape");

    public final StringPath cod = createString("cod");

    public final StringPath lib = createString("lib");

    public QEtape(String variable) {
        super(Etape.class, forVariable(variable));
    }

    public QEtape(Path<? extends Etape> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEtape(PathMetadata metadata) {
        super(Etape.class, metadata);
    }

}

