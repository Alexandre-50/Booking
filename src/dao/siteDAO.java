package dao;

import modele.Site;
import java.util.List;

public interface siteDAO {
    List<Site> getAllSites();
    List<Site> getSitesParVille(String ville);
    List<Site> rechercherSites(String ville, String type, int nbAdultes, int nbEnfants);
    Site getSiteParId(int idSite);
    void addSite(Site site);
    void updateSite(Site site);
    void supprimerSiteParId(int id);
}
