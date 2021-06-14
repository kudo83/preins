package ac.encg.preins.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInscrit is a Querydsl query type for Inscrit
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QInscrit extends EntityPathBase<Inscrit> {

    private static final long serialVersionUID = 365739627L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInscrit inscrit = new QInscrit("inscrit");

    public final StringPath adresse = createString("adresse");

    public final StringPath AdresseTuteur = createString("AdresseTuteur");

    public final QBac Bac;

    public final StringPath cin = createString("cin");

    public final StringPath cinTuteur = createString("cinTuteur");

    public final NumberPath<Byte> civ = createNumber("civ", Byte.class);

    public final StringPath cne = createString("cne");

    public final NumberPath<Byte> codeTpe = createNumber("codeTpe", Byte.class);

    public final DateTimePath<java.sql.Timestamp> dateCreat = createDateTime("dateCreat", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> dateInvalid = createDateTime("dateInvalid", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> dateModif = createDateTime("dateModif", java.sql.Timestamp.class);

    public final DatePath<java.util.Date> dateNaiss = createDate("dateNaiss", java.util.Date.class);

    public final DateTimePath<java.sql.Timestamp> dateValid = createDateTime("dateValid", java.sql.Timestamp.class);

    public final BooleanPath declaration = createBoolean("declaration");

    public final QEtape etape;

    public final StringPath handicape = createString("handicape");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isVALID = createBoolean("isVALID");

    public final StringPath lieuNaiss = createString("lieuNaiss");

    public final StringPath mentionBac = createString("mentionBac");

    public final StringPath nom = createString("nom");

    public final StringPath nomAr = createString("nomAr");

    public final QPays paysNaiss;

    public final QPcs pcsMere;

    public final QPcs pcsPere;

    public final StringPath photoFileName = createString("photoFileName");

    public final StringPath prenom = createString("prenom");

    public final StringPath prenomAr = createString("prenomAr");

    public final StringPath prenomMere = createString("prenomMere");

    public final StringPath prenomMereAr = createString("prenomMereAr");

    public final StringPath prenomPere = createString("prenomPere");

    public final StringPath prenomPereAr = createString("prenomPereAr");

    public final QProvince provinceNaiss;

    public final ComparablePath<Character> sex = createComparable("sex", Character.class);

    public final StringPath tel = createString("tel");

    public final StringPath telTuteur = createString("telTuteur");

    public final QUser user;

    public final StringPath userInValid = createString("userInValid");

    public final StringPath userModif = createString("userModif");

    public final StringPath userValid = createString("userValid");

    public QInscrit(String variable) {
        this(Inscrit.class, forVariable(variable), INITS);
    }

    public QInscrit(Path<? extends Inscrit> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInscrit(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInscrit(PathMetadata metadata, PathInits inits) {
        this(Inscrit.class, metadata, inits);
    }

    public QInscrit(Class<? extends Inscrit> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.Bac = inits.isInitialized("Bac") ? new QBac(forProperty("Bac"), inits.get("Bac")) : null;
        this.etape = inits.isInitialized("etape") ? new QEtape(forProperty("etape")) : null;
        this.paysNaiss = inits.isInitialized("paysNaiss") ? new QPays(forProperty("paysNaiss")) : null;
        this.pcsMere = inits.isInitialized("pcsMere") ? new QPcs(forProperty("pcsMere")) : null;
        this.pcsPere = inits.isInitialized("pcsPere") ? new QPcs(forProperty("pcsPere")) : null;
        this.provinceNaiss = inits.isInitialized("provinceNaiss") ? new QProvince(forProperty("provinceNaiss")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

