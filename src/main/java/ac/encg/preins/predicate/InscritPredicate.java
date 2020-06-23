package ac.encg.preins.predicate;

import ac.encg.preins.entity.QAdmis;
import ac.encg.preins.entity.QInscrit;
import com.querydsl.core.types.Predicate;

/**
 *
 * @author Aissam
 */
public final class InscritPredicate {

    public InscritPredicate() {
    }

    public static Predicate existCneOrCin(String cne, String cin) {
        return QInscrit.inscrit.cne.eq(cne).or(QInscrit.inscrit.cin.eq(cin));
    }
}
