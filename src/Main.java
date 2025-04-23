import controleur.UtilisateurControleur;
import dao.DaoFactory;
import dao.UtilisateurDAO;
import vue.accueilClient;
import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            // Connexion à la base de données
            DaoFactory daoFactory = DaoFactory.getInstance("booking", "root", "");
            UtilisateurDAO utilisateurDAO = daoFactory.getUtilisateurDAO();
            UtilisateurControleur controleur = new UtilisateurControleur(utilisateurDAO);
            // Lancer la fenêtre d'accueil avec le contrôleur
            new accueilClient(controleur);
        });
    }
}
