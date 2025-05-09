package dao;

import modele.Administrateur;

import java.sql.*;

public class AdminDAOImpl implements AdminDAO
{
    private final DaoFactory daoFactory;

    public AdminDAOImpl(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    @Override
    public void ajouter(Administrateur administrateur)
    {
        String sql = "INSERT INTO Administrateurs (nom, prenom, email, motDePasse, ancienClient) VALUES (?, ?, ?, ?, ?)";

        try(Connection conn = daoFactory.getConnection();
            PreparedStatement infoClients = conn.prepareStatement(sql))
        {

            infoClients.setString(1, administrateur.getNom());
            infoClients.setString(2, administrateur.getPrenom());
            infoClients.setString(3, administrateur.getEmail());
            infoClients.setString(4, administrateur.getMotDePasse());
            infoClients.executeUpdate();
        }catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Erreur lors de l'inscription.");
        }
    }

    @Override
    public Administrateur connecter(String email, String motDePasse)
    {
        Administrateur administrateur = null;
        String sql = "SELECT * FROM administrateurs WHERE email = ? AND motDePasse = ?";

        try(Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, email);
            ps.setString(2, motDePasse);
            ResultSet info = ps.executeQuery();

            if (info.next())
            {
                administrateur = new Administrateur();
                administrateur.setId(info.getInt("id_administrateurs"));
                administrateur.setNom(info.getString("nom"));
                administrateur.setPrenom(info.getString("prenom"));
                administrateur.setEmail(info.getString("email"));
                administrateur.setMotDePasse(info.getString("motDePasse"));

            }
        } catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Erreur de connexion.");
        }
        return administrateur;
    }
    @Override
    public boolean estAdministrateur(String email) {
        String sql = "SELECT COUNT(*) FROM administrateurs WHERE email = ?";
        try (Connection conn = daoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la vérification de l'administrateur.");
        }
        return false;
    }
}
