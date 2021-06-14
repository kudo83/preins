package ac.encg.preins.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBac is a Querydsl query type for Bac
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBac extends EntityPathBase<Bac> {

    private static final long serialVersionUID = 808507687L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBac bac = new QBac("bac");

    public final QAcademie academie;

    public final StringPath Annee = createString("Annee");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath montion = createString("montion");

    public final QProvince provinceBac;

    public final QSerieBac SerieBac;

    public QBac(String variable) {
        this(Bac.class, forVariable(variable), INITS);
    }

    public QBac(Path<? extends Bac> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBac(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBac(PathMetadata metadata, PathInits inits) {
        this(Bac.class, metadata, inits);
    }

    public QBac(Class<? extends Bac> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.academie = inits.isInitialized("academie") ? new QAcademie(forProperty("academie")) : null;
        this.provinceBac = inits.isInitialized("provinceBac") ? new QProvince(forProperty("provinceBac")) : null;
        this.SerieBac = inits.isInitialized("SerieBac") ? new QSerieBac(forProperty("SerieBac")) : null;
    }

}

