package ac.encg.preins.predicate;

import ac.encg.preins.entity.QAdmis;
import com.querydsl.core.types.Predicate;

/**
 *
 * @author Aissam
 */
public final class AdmisPredicate {

    public AdmisPredicate() {
    }

    public static Predicate existCne(String cne) {
        return QAdmis.admis.cne.eq(cne);
    }

    public static Predicate existCin(String cin) {
        return QAdmis.admis.cin.eq(cin);
    }

    public static Predicate existCneOrCin(String cne, String cin) {
        return QAdmis.admis.cne.eq(cne).or(QAdmis.admis.cin.eq(cin));
    }
}
