package ac.encg.preins.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAcademie is a Querydsl query type for Academie
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAcademie extends EntityPathBase<Academie> {

    private static final long serialVersionUID = 3022278L;

    public static final QAcademie academie = new QAcademie("academie");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lib = createString("lib");

    public QAcademie(String variable) {
        super(Academie.class, forVariable(variable));
    }

    public QAcademie(Path<? extends Academie> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAcademie(PathMetadata metadata) {
        super(Academie.class, metadata);
    }

}

