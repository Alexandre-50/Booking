package dao;

import modele.Logement;
import java.sql.Date;
import java.util.List;

public interface logementDAO {
    List<Logement> getLogementsParSite(int idSite);
    List<Logement> getTousLesLogements();
    List<Logement> getLogementsDisponibles(Date dateDebut, Date dateFin);
    Logement getLogementParId(int idLogement);
}