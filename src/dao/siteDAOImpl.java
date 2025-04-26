/*package dao;

import modele.Site;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class siteDAOImpl implements siteDAO
{
    private DaoFactory daoFactory;

    public siteDAOImpl(DaoFactory daoFactory)
    {
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

    @Override
    public List<Site> rechercherSites(String ville, String type, int nbAdultes, int nbEnfants)
    {
        List<Site> resultats = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultatsRequete = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();

            StringBuilder requete = new StringBuilder();
            requete.append("SELECT site.* FROM site ");
            requete.append("INNER JOIN categories ON site.id_categorie = categories.id_categories ");
            requete.append("WHERE 1=1 ");

            if (ville != null && !ville.trim().isEmpty())
            {
                requete.append(" AND ville LIKE '%").append(ville.trim()).append("%' ");
            }

            if (type != null && !type.equalsIgnoreCase("Tous"))
            {
                requete.append(" AND categories.nom LIKE '%").append(type.trim()).append("%' ");
            }

            System.out.println("Requête SQL générée : " + requete.toString());

            resultatsRequete = statement.executeQuery(requete.toString());

            while (resultatsRequete.next())
            {
                Site site = extraireSite(resultatsRequete);
                resultats.add(site);
            }

            System.out.println("\nSites trouvés :");
            for (int i = 0; i < resultats.size(); i++)
            {
                System.out.println((i + 1) + ". " + resultats.get(i).getNom());
            }

        }catch (SQLException e)
        {
            System.err.println("Erreur SQL lors de la recherche des sites : " + e.getMessage());
        }
        finally
        {
            try{
                if (resultatsRequete != null)
                    resultatsRequete.close();
                if (statement != null)
                    statement.close();
                if (connexion != null)
                    connexion.close();
            }catch (SQLException e)
            {
                System.err.println("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }
        return resultats;
    }

    private Site extraireSite(ResultSet rs) throws SQLException
    {
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
}*/

package dao;

import modele.Site;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class siteDAOImpl implements siteDAO
{
    private DaoFactory daoFactory;

    public siteDAOImpl(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }
    @Override
    public void addSite(Site site) {
        String sql = "INSERT INTO site (nom, description, id_categorie, adresse, ville, nbEtoiles, prixParNuit, photo, petitDejeuner, parking, piscine, transportProche) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, site.getNom());
            stmt.setString(2, site.getDescription());
            stmt.setInt(3, site.getIdCategorie());
            stmt.setString(4, site.getAdresse());
            stmt.setString(5, site.getVille());
            stmt.setInt(6, site.getNbEtoiles());
            stmt.setDouble(7, site.getPrixParNuit());
            stmt.setString(8, site.getPhoto());
            stmt.setBoolean(9, site.isPetitDejeuner());
            stmt.setBoolean(10, site.isParking());
            stmt.setBoolean(11, site.isPiscine());
            stmt.setBoolean(12, site.isTransportProche());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    @Override
    public List<Site> rechercherSites(String ville, String type, int nbAdultes, int nbEnfants)
    {
        List<Site> resultats = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultatsRequete = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();

            StringBuilder requete = new StringBuilder();
            requete.append("SELECT site.* FROM site ");
            requete.append("INNER JOIN categories ON site.id_categorie = categories.id_categories ");
            requete.append("WHERE 1=1 ");

            if (ville != null && !ville.trim().isEmpty())
            {
                requete.append(" AND ville LIKE '%").append(ville.trim()).append("%' ");
            }

            if (type != null && !type.equalsIgnoreCase("Tous"))
            {
                requete.append(" AND categories.nom LIKE '%").append(type.trim()).append("%' ");
            }

            System.out.println("Requête SQL générée : " + requete.toString());

            resultatsRequete = statement.executeQuery(requete.toString());

            while (resultatsRequete.next())
            {
                Site site = extraireSite(resultatsRequete);
                resultats.add(site);
            }

            System.out.println("\nSites trouvés :");
            for (int i = 0; i < resultats.size(); i++)
            {
                System.out.println((i + 1) + ". " + resultats.get(i).getNom());
            }

        }catch (SQLException e)
        {
            System.err.println("Erreur SQL lors de la recherche des sites : " + e.getMessage());
        }
        finally
        {
            try{
                if (resultatsRequete != null)
                    resultatsRequete.close();
                if (statement != null)
                    statement.close();
                if (connexion != null)
                    connexion.close();
            }catch (SQLException e)
            {
                System.err.println("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }
        return resultats;
    }

    @Override
    public Site getSiteParId(int idSite) {
        String requete = "SELECT * FROM site WHERE id_site = ?";

        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setInt(1, idSite);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extraireSite(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Site extraireSite(ResultSet rs) throws SQLException
    {
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
    @Override
    public void updateSite(Site site) {
        String query = """
        UPDATE site SET 
            nom = ?, 
            description = ?, 
            adresse = ?, 
            ville = ?, 
            nbEtoiles = ?, 
            prixParNuit = ?, 
            photo = ?, 
            petitDejeuner = ?, 
            parking = ?, 
            piscine = ?, 
            transportProche = ?
        WHERE id_site = ?
    """;

        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, site.getNom());
            stmt.setString(2, site.getDescription());
            stmt.setString(3, site.getAdresse());
            stmt.setString(4, site.getVille());
            stmt.setInt(5, site.getNbEtoiles());
            stmt.setDouble(6, site.getPrixParNuit());
            stmt.setString(7, site.getPhoto());
            stmt.setBoolean(8, site.isPetitDejeuner());
            stmt.setBoolean(9, site.isParking());
            stmt.setBoolean(10, site.isPiscine());
            stmt.setBoolean(11, site.isTransportProche());
            stmt.setInt(12, site.getIdSite());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void supprimerSiteParId(int id) {
        String sql = "DELETE FROM site WHERE id_site = ?";
        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

