package ac.encg.preins.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPcs is a Querydsl query type for Pcs
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPcs extends EntityPathBase<Pcs> {

    private static final long serialVersionUID = 808521219L;

    public static final QPcs pcs = new QPcs("pcs");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lib = createString("lib");

    public QPcs(String variable) {
        super(Pcs.class, forVariable(variable));
    }

    public QPcs(Path<? extends Pcs> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPcs(PathMetadata metadata) {
        super(Pcs.class, metadata);
    }

}

