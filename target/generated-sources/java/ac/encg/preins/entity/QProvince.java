package ac.encg.preins.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProvince is a Querydsl query type for Province
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProvince extends EntityPathBase<Province> {

    private static final long serialVersionUID = 1220578285L;

    public static final QProvince province = new QProvince("province");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lib = createString("lib");

    public QProvince(String variable) {
        super(Province.class, forVariable(variable));
    }

    public QProvince(Path<? extends Province> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProvince(PathMetadata metadata) {
        super(Province.class, metadata);
    }

}

