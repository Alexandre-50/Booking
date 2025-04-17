package dao;

import modele.Site;
import java.util.List;

public interface siteDAO
{
    List<Site> getAllSites();
    List<Site> getSitesParVille(String ville);
}