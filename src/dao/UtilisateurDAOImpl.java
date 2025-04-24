package dao;

import modele.Utilisateur;

import java.sql.*;

public class UtilisateurDAOImpl implements UtilisateurDAO
{
    private final DaoFactory daoFactory;

    public UtilisateurDAOImpl(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    @Override
    public void ajouter(Utilisateur utilisateur)
    {
        String sql = "INSERT INTO utilisateurs (nom, prenom, email, motDePasse, ancienClient) VALUES (?, ?, ?, ?, ?)";

        try(Connection conn = daoFactory.getConnection();
             PreparedStatement infoClients = conn.prepareStatement(sql))
        {

            infoClients.setString(1, utilisateur.getNom());
            infoClients.setString(2, utilisateur.getPrenom());
            infoClients.setString(3, utilisateur.getEmail());
            infoClients.setString(4, utilisateur.getMotDePasse());
            infoClients.setBoolean(5, utilisateur.isAncienClient());
            infoClients.executeUpdate();
        }catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Erreur lors de l'inscription.");
        }
    }

    @Override
    public Utilisateur connecter(String email, String motDePasse)
    {
        Utilisateur utilisateur = null;
        String sql = "SELECT * FROM utilisateurs WHERE email = ? AND motDePasse = ?";

        try(Connection conn = daoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, email);
            ps.setString(2, motDePasse);
            ResultSet info = ps.executeQuery();

            if (info.next())
            {
                utilisateur = new Utilisateur();
                utilisateur.setId(info.getInt("id_utilisateurs"));
                utilisateur.setNom(info.getString("nom"));
                utilisateur.setPrenom(info.getString("prenom"));
                utilisateur.setEmail(info.getString("email"));
                utilisateur.setMotDePasse(info.getString("motDePasse"));
                utilisateur.setAncienClient(info.getBoolean("ancienClient"));
                utilisateur.setDateInscription(info.getDate("dateInscription"));
            }
        } catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Erreur de connexion.");
        }
        return utilisateur;
    }
}
