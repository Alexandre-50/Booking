/*import controleur.UtilisateurControleur;
import dao.DaoFactory;
import dao.UtilisateurDAO;
import vue.vueUtilisateur;

public class Main
{
    public static void main(String[] args)
    {
        DaoFactory daoFactory = DaoFactory.getInstance("booking", "root", "");
        UtilisateurDAO utilisateurDAO = daoFactory.getUtilisateurDAO();
        UtilisateurControleur controler = new UtilisateurControleur(utilisateurDAO);
        javax.swing.SwingUtilities.invokeLater(() -> new vueUtilisateur(controler));
    }
}*/

import controleur.UtilisateurControleur;
import dao.DaoFactory;
import dao.UtilisateurDAO;
import vue.accueilClient;
import vue.vueUtilisateur;
import javax.swing.*;
import java.awt.*;

public class Main
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            DaoFactory daoFactory = DaoFactory.getInstance("booking", "root", "");
            UtilisateurDAO utilisateurDAO = daoFactory.getUtilisateurDAO();
            UtilisateurControleur controleur = new UtilisateurControleur(utilisateurDAO);

            accueilClient accueil = new accueilClient();

            JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton btnConnexion = new JButton("Connexion / Inscription");
            topPanel.add(btnConnexion);

            accueil.add(topPanel, BorderLayout.NORTH);
            accueil.setVisible(true);

            btnConnexion.addActionListener(e -> new vueUtilisateur(controleur));
        });
    }
}