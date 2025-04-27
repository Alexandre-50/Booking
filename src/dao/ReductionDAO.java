package dao;

import modele.Reduction;
import java.util.List;

public interface ReductionDAO {
    void addReduction(Reduction reduction);
    void updateReduction(Reduction reduction);
    void deleteReduction(int idReduction);
    List<Reduction> getAllReductions();
    Reduction getReductionById(int idReduction);
}
