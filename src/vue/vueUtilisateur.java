/*package vue;
import controleur.UtilisateurControleur;
import dao.DaoFactory;
import dao.logementDAO;
import dao.logementDAOImpl;
import dao.siteDAO;
import dao.siteDAOImpl;
import modele.Logement;
import modele.Site;
import modele.Utilisateur;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class vueUtilisateur extends JFrame
{
    private UtilisateurControleur controleur;
    private accueilClient fenetreAccueil;

    public vueUtilisateur(UtilisateurControleur controller)
    {
        this.controleur = controller;
        setTitle("Booking - Connexion / Inscription Client");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelBoutons = new JPanel();
        panelBoutons.setLayout(new BoxLayout(panelBoutons, BoxLayout.Y_AXIS));
        panelBoutons.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));

        JButton btnInscription = new JButton("S'inscrire");
        JButton btnConnexion = new JButton("Se connecter");
        btnInscription.setBackground(new Color(0,120,215));
        btnInscription.setForeground(Color.WHITE);
        btnInscription.setFocusPainted(false);
        btnInscription.setFont(new Font("Arial", Font.BOLD, 14));

        btnConnexion.setBackground(new Color(0,120,215));
        btnConnexion.setForeground(Color.WHITE);
        btnConnexion.setFocusPainted(false);
        btnConnexion.setFont(new Font("Arial", Font.BOLD, 14));

        btnInscription.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnConnexion.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnInscription.addActionListener(e -> CreationCompte());
        btnConnexion.addActionListener(e -> ConnexionCompte());

        panelBoutons.add(btnInscription);
        panelBoutons.add(Box.createVerticalStrut(15));
        panelBoutons.add(btnConnexion);

        add(panelBoutons, BorderLayout.CENTER);

        setVisible(true);
    }
    private void CreationCompte()
    {
        PageInscription inscription = new PageInscription(controleur);
        inscription.setVisible(true);
    }

    private void ConnexionCompte()
    {
        PageConnexion connexion = new PageConnexion(controleur);
        connexion.setVisible(true);
    }
}*/

package vue;

import controleur.UtilisateurControleur;
import modele.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class vueUtilisateur extends JFrame
{
    private UtilisateurControleur controleur;
    private accueilClient fenetreAccueil;

    public vueUtilisateur(UtilisateurControleur controleur, accueilClient fenetreAccueil)
    {
        this.controleur = controleur;
        this.fenetreAccueil = fenetreAccueil;

        setTitle("Connexion ou Inscription");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        JButton boutonConnexion = new JButton("Se connecter");
        JButton boutonInscription = new JButton("S'inscrire");
        boutonInscription.setBackground(new Color(0,120,215));
        boutonInscription.setForeground(Color.WHITE);
        boutonInscription.setFocusPainted(false);
        boutonInscription.setFont(new Font("Arial", Font.BOLD, 14));

        boutonConnexion.setBackground(new Color(0,120,215));
        boutonConnexion.setForeground(Color.WHITE);
        boutonConnexion.setFocusPainted(false);
        boutonConnexion.setFont(new Font("Arial", Font.BOLD, 14));

        boutonInscription.setAlignmentX(Component.CENTER_ALIGNMENT);
        boutonConnexion.setAlignmentX(Component.CENTER_ALIGNMENT);

        boutonConnexion.addActionListener(e -> ouvrirConnexion());
        boutonInscription.addActionListener(e -> ouvrirInscription());

        panelPrincipal.add(boutonConnexion);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(boutonInscription);

        add(panelPrincipal, BorderLayout.CENTER);
        setVisible(true);
    }

    private void ouvrirConnexion()
    {
        PageConnexion connexion = new PageConnexion(controleur, fenetreAccueil);
        connexion.setVisible(true);
        dispose();
    }

    private void ouvrirInscription()
    {
        PageInscription inscription = new PageInscription(controleur);
        inscription.setVisible(true);
        dispose();
    }
}
