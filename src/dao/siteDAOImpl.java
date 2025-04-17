package dao;

import modele.Site;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class siteDAOImpl implements siteDAO {
    private DaoFactory daoFactory;

    public siteDAOImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Site> getAllSites() {
        List<Site> sites = new ArrayList<>();
        String requete = "SELECT * FROM site";

        try (Connection conn = daoFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(requete)) {

            while (rs.next()) {
                Site site = extraireSite(rs);
                sites.add(site);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sites;
    }

    @Override
    public List<Site> getSitesParVille(String ville) {
        List<Site> sites = new ArrayList<>();
        String requete = "SELECT * FROM site WHERE ville LIKE ?";

        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setString(1, "%" + ville + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Site site = extraireSite(rs);
                sites.add(site);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sites;
    }

    private Site extraireSite(ResultSet rs) throws SQLException {
        Site site = new Site();
        site.setIdSite(rs.getInt("id_site"));
        site.setNom(rs.getString("nom"));
        site.setDescription(rs.getString("description"));
        site.setIdCategorie(rs.getInt("id_categorie"));
        site.setAdresse(rs.getString("adresse"));
        site.setVille(rs.getString("ville"));
        site.setNbEtoiles(rs.getInt("nbEtoiles"));
        site.setPrixParNuit(rs.getDouble("prixParNuit"));
        site.setPhoto(rs.getString("photo"));
        site.setPetitDejeuner(rs.getBoolean("petitDejeuner"));
        site.setParking(rs.getBoolean("parking"));
        site.setPiscine(rs.getBoolean("piscine"));
        site.setTransportProche(rs.getBoolean("transportProche"));
        return site;
    }
}
