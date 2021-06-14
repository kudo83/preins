package ac.encg.preins.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPays is a Querydsl query type for Pays
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPays extends EntityPathBase<Pays> {

    private static final long serialVersionUID = -705647608L;

    public static final QPays pays = new QPays("pays");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lib = createString("lib");

    public QPays(String variable) {
        super(Pays.class, forVariable(variable));
    }

    public QPays(Path<? extends Pays> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPays(PathMetadata metadata) {
        super(Pays.class, metadata);
    }

}

