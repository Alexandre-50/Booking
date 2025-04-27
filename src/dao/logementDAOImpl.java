/*package dao;
import modele.Logement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class logementDAOImpl implements logementDAO {

    private DaoFactory daoFactory;

    public logementDAOImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Logement> getLogementsParSite(int idSite) {
        List<Logement> logements = new ArrayList<>();
        String requete = "SELECT * FROM logements WHERE id_site = ?";

        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            stmt.setInt(1, idSite);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                logements.add(extraireLogement(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return logements;
    }

    @Override
    public List<Logement> getTousLesLogements() {
        List<Logement> logements = new ArrayList<>();
        String requete = "SELECT * FROM logements";

        try (Connection conn = daoFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(requete)) {

            while (rs.next()) {
                logements.add(extraireLogement(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return logements;
    }

    private Logement extraireLogement(ResultSet rs) throws SQLException {
        return new Logement(
                rs.getInt("id_logements"),
                rs.getInt("id_site"),
                rs.getString("description"),
                rs.getDouble("prixParNuit"),
                rs.getBoolean("wifi"),
                rs.getBoolean("climatisation"),
                rs.getBoolean("fumeur"),
                rs.getBoolean("petitDejeuner"),
                rs.getBoolean("vueMer"),
                rs.getBoolean("minibar")
        );
    }
}*/

package dao;

import modele.Logement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class logementDAOImpl implements logementDAO {
    private DaoFactory daoFactory;

    public logementDAOImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Logement> getTousLesLogements() {

        List<Logement> logements = new ArrayList<>();
        String requete = "SELECT * FROM logements";

        try (Connection conn = daoFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(requete)) {

            while (rs.next()) {
                Logement logement = mapLogement(rs);
                logements.add(logement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logements;
    }

    @Override
    public List<Logement> getLogementsParSite(int idSite) {
        List<Logement> logements = new ArrayList<>();
        String query = "SELECT * FROM logements WHERE id_site = ?";

        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement statement = connexion.prepareStatement(query)) {

            statement.setInt(1, idSite);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    logements.add(mapLogement(resultSet));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return logements;
    }

    @Override
    public List<Logement> getLogementsDisponibles(Date dateDebut, Date dateFin) {
        List<Logement> logementsDisponibles = new ArrayList<>();

        String query = """
                SELECT l.* FROM logements l
                WHERE NOT EXISTS (
                    SELECT 1 FROM disponibilites d
                    WHERE d.id_logements = l.id_logements
                    AND d.dateDisponible BETWEEN ? AND ?
                    AND d.estDisponible = 0
                )
                AND EXISTS (
                    SELECT 1 FROM disponibilites d2
                    WHERE d2.id_logements = l.id_logements
                    AND d2.dateDisponible BETWEEN ? AND ?
                )
                GROUP BY l.id_logements
                """;

        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement statement = connexion.prepareStatement(query)) {

            statement.setDate(1, dateDebut);
            statement.setDate(2, dateFin);
            statement.setDate(3, dateDebut);
            statement.setDate(4, dateFin);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    logementsDisponibles.add(mapLogement(resultSet));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return logementsDisponibles;
    }

    @Override
    public Logement getLogementParId(int idLogement) {
        String query = "SELECT * FROM logements WHERE id_logements = ?";
        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(query)) {

            stmt.setInt(1, idLogement);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapLogement(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Logement mapLogement(ResultSet rs) throws SQLException {
        return new Logement(
                rs.getInt("id_logements"),
                rs.getInt("id_site"),
                rs.getString("description"),
                rs.getDouble("prixParNuit"),
                rs.getBoolean("wifi"),
                rs.getBoolean("climatisation"),
                rs.getBoolean("fumeur"),
                rs.getBoolean("petitDejeuner"),
                rs.getBoolean("vueMer"),
                rs.getBoolean("minibar")
        );
    }
    @Override
    public void addLogement(Logement logement) {
        String sql = """
        INSERT INTO logements (id_site, description, prixParNuit, wifi, climatisation, fumeur, petitDejeuner, vueMer, minibar)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(sql)) {

            stmt.setInt(1, logement.getIdSite());
            stmt.setString(2, logement.getDescription());
            stmt.setDouble(3, logement.getPrixParNuit());
            stmt.setBoolean(4, logement.isWifi());
            stmt.setBoolean(5, logement.isClimatisation());
            stmt.setBoolean(6, logement.estFumeur());
            stmt.setBoolean(7, logement.isPetitDejeuner());
            stmt.setBoolean(8, logement.isVueMer());
            stmt.setBoolean(9, logement.isMinibar());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void updateLogement(Logement logement) {
        String sql = """
        UPDATE logements SET 
            description = ?, 
            prixParNuit = ?, 
            wifi = ?, 
            climatisation = ?, 
            fumeur = ?, 
            petitDejeuner = ?, 
            vueMer = ?, 
            minibar = ?
        WHERE id_logements = ?
    """;

        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, logement.getDescription());
            stmt.setDouble(2, logement.getPrixParNuit());
            stmt.setBoolean(3, logement.isWifi());
            stmt.setBoolean(4, logement.isClimatisation());
            stmt.setBoolean(5, logement.estFumeur());
            stmt.setBoolean(6, logement.isPetitDejeuner());
            stmt.setBoolean(7, logement.isVueMer());
            stmt.setBoolean(8, logement.isMinibar());
            stmt.setInt(9, logement.getIdLogement());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void supprimerLogementParId(int id) {
        String sql = "DELETE FROM logements WHERE id_logements = ?";
        try (Connection conn = daoFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
