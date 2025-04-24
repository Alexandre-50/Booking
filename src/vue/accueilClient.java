/*package vue;
import controleur.UtilisateurControleur;
import dao.DaoFactory;
import dao.logementDAO;
import dao.logementDAOImpl;
import dao.siteDAO;
import dao.siteDAOImpl;
import modele.Logement;
import modele.Site;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class accueilClient extends JFrame
{

    private JTextField champVille;
    private JComboBox<String> typeHebergement;
    private JSpinner nbAdultes;
    private JSpinner nbEnfants;
    private JSpinner nbChambres;
    private UtilisateurControleur controleur;

    public accueilClient(UtilisateurControleur controleur) {
        this.controleur = controleur;
        afficheAccueil();
    }

    private void afficheAccueil()
    {
        setTitle("Booking Accueil");
        setSize(900, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        try {
            ImageIcon logoIcon = new ImageIcon(getClass().getResource("/images/Logo.jpg"));
            Image img = logoIcon.getImage().getScaledInstance(200, 80, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(img));

            JPanel logoPanel = new JPanel(new GridBagLayout());
            logoPanel.setOpaque(false);
            logoPanel.setBackground(Color.WHITE);
            logoPanel.add(logoLabel);
            topPanel.add(logoPanel, BorderLayout.CENTER);

        } catch (Exception e) {
            JLabel labelTitre = new JLabel("Booking");
            labelTitre.setFont(new Font("Arial", Font.BOLD, 26));
            JPanel panelTitre = new JPanel(new GridBagLayout());
            panelTitre.setOpaque(false);
            panelTitre.setBackground(Color.WHITE);
            panelTitre.add(labelTitre);
            topPanel.add(panelTitre, BorderLayout.CENTER);
        }

        JPanel boutonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        boutonPanel.setOpaque(false);
        JButton boutonConnexion = new JButton("Connexion / Inscription");
        boutonConnexion.setBackground(Color.WHITE);
        boutonConnexion.setForeground(new Color(0, 120, 215));
        boutonConnexion.setFocusPainted(false);
        boutonConnexion.setFont(new Font("Arial", Font.PLAIN, 11));
        boutonConnexion.setOpaque(true);
        boutonConnexion.setBorderPainted(false);
        boutonConnexion.setPreferredSize(new Dimension(150, 25));
        boutonConnexion.addActionListener(e -> new vueUtilisateur(controleur));
        boutonPanel.add(boutonConnexion);
        topPanel.add(boutonPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        JPanel formulaireRecherche = new JPanel(new GridBagLayout());
        formulaireRecherche.setBackground(new Color(245, 245, 245));
        GridBagConstraints grille = new GridBagConstraints();
        grille.insets = new Insets(10, 10, 10, 10);
        grille.fill = GridBagConstraints.HORIZONTAL;

        grille.gridx = 0;
        grille.gridy = 0;
        formulaireRecherche.add(new JLabel("Destination :"), grille);
        grille.gridx = 1;
        champVille = new JTextField(20);
        formulaireRecherche.add(champVille, grille);

        grille.gridx = 0;
        grille.gridy = 1;
        formulaireRecherche.add(new JLabel("Type d'hébergement :"), grille);
        grille.gridx = 1;
        typeHebergement = new JComboBox<>(new String[]{"Tous", "Hôtel", "Villa", "Chalet", "Appartement", "Colocation", "Maison"});
        formulaireRecherche.add(typeHebergement, grille);

        grille.gridx = 0;
        grille.gridy = 2;
        formulaireRecherche.add(new JLabel("Adultes :"), grille);
        grille.gridx = 1;
        nbAdultes = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        formulaireRecherche.add(nbAdultes, grille);

        grille.gridx = 0;
        grille.gridy = 3;
        formulaireRecherche.add(new JLabel("Enfants :"), grille);
        grille.gridx = 1;
        nbEnfants = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        formulaireRecherche.add(nbEnfants, grille);

        grille.gridx = 0;
        grille.gridy = 4;
        formulaireRecherche.add(new JLabel("Chambres :"), grille);
        grille.gridx = 1;
        nbChambres = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
        formulaireRecherche.add(nbChambres, grille);

        grille.gridx = 0;
        grille.gridy = 5;
        grille.gridwidth = 2;

        JButton boutonRechercher = new JButton("Rechercher");
        boutonRechercher.setBackground(new Color(0, 120, 215));
        boutonRechercher.setForeground(Color.WHITE);
        boutonRechercher.setFocusPainted(false);
        boutonRechercher.setFont(new Font("Arial", Font.BOLD, 14));
        boutonRechercher.setOpaque(true);
        boutonRechercher.setBorderPainted(false);
        formulaireRecherche.add(boutonRechercher, grille);
        add(formulaireRecherche, BorderLayout.CENTER);

        boutonRechercher.addActionListener(e -> lancerRecherche());

        setVisible(true);
    }

    private void lancerRecherche()
    {
        String ville = champVille.getText().trim();
        String type = (String) typeHebergement.getSelectedItem();
        int adultes = (Integer) nbAdultes.getValue();
        int enfants = (Integer) nbEnfants.getValue();

        DaoFactory daoFactory = DaoFactory.getInstance("booking", "root", "");
        siteDAO siteDao = new siteDAOImpl(daoFactory);
        logementDAO logementDao = new logementDAOImpl(daoFactory);

        List<Site> sites = siteDao.rechercherSites(ville, type, adultes, enfants);
        List<Logement> logements = new ArrayList<>();

        for (int i = 0; i < sites.size(); i++)
        {
            Site s = sites.get(i);
            List<Logement> logementsDuSite = logementDao.getLogementsParSite(s.getIdSite());
            logements.addAll(logementsDuSite);
        }

        if (logements.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Aucun logement trouvé.", "Résultats", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            new RechercheHebergement(sites, logements, adultes, enfants);
        }
    }
}*/

package vue;
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

public class accueilClient extends JFrame
{
    private JTextField champVille;
    private JComboBox<String> typeHebergement;
    private JSpinner nbAdultes;
    private JSpinner nbEnfants;
    private JSpinner nbChambres;
    private UtilisateurControleur controleur;
    private Utilisateur utilisateurConnecte;
    private JPanel boutonPanel;

    public accueilClient(UtilisateurControleur controleur)
    {
        this.controleur = controleur;
        this.utilisateurConnecte = null;
        afficheAccueil();
    }

    private void afficheAccueil()
    {
        setTitle("Booking Accueil");
        setSize(900, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        try {
            ImageIcon logoIcon = new ImageIcon(getClass().getResource("/images/Logo.jpg"));
            Image img = logoIcon.getImage().getScaledInstance(200, 80, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(img));

            JPanel logoPanel = new JPanel(new GridBagLayout());
            logoPanel.setOpaque(false);
            logoPanel.setBackground(Color.WHITE);
            logoPanel.add(logoLabel);
            topPanel.add(logoPanel, BorderLayout.CENTER);

        } catch (Exception e)
        {
            JLabel labelTitre = new JLabel("Booking");
            labelTitre.setFont(new Font("Arial", Font.BOLD, 26));
            JPanel panelTitre = new JPanel(new GridBagLayout());
            panelTitre.setOpaque(false);
            panelTitre.setBackground(Color.WHITE);
            panelTitre.add(labelTitre);
            topPanel.add(panelTitre, BorderLayout.CENTER);
        }

        boutonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        boutonPanel.setOpaque(false);
        ajouterBoutonConnexion();
        topPanel.add(boutonPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        JPanel formulaireRecherche = new JPanel(new GridBagLayout());
        formulaireRecherche.setBackground(new Color(245, 245, 245));
        GridBagConstraints grille = new GridBagConstraints();
        grille.insets = new Insets(10, 10, 10, 10);
        grille.fill = GridBagConstraints.HORIZONTAL;

        grille.gridx = 0;
        grille.gridy = 0;
        formulaireRecherche.add(new JLabel("Destination :"), grille);
        grille.gridx = 1;
        champVille = new JTextField(20);
        formulaireRecherche.add(champVille, grille);

        grille.gridx = 0;
        grille.gridy = 1;
        formulaireRecherche.add(new JLabel("Type d'hébergement :"), grille);
        grille.gridx = 1;
        typeHebergement = new JComboBox<>(new String[]{"Tous", "Hôtel", "Villa", "Chalet", "Appartement", "Colocation", "Maison"});
        formulaireRecherche.add(typeHebergement, grille);

        grille.gridx = 0;
        grille.gridy = 2;
        formulaireRecherche.add(new JLabel("Adultes :"), grille);
        grille.gridx = 1;
        nbAdultes = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        formulaireRecherche.add(nbAdultes, grille);

        grille.gridx = 0;
        grille.gridy = 3;
        formulaireRecherche.add(new JLabel("Enfants :"), grille);
        grille.gridx = 1;
        nbEnfants = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        formulaireRecherche.add(nbEnfants, grille);

        grille.gridx = 0;
        grille.gridy = 4;
        formulaireRecherche.add(new JLabel("Chambres :"), grille);
        grille.gridx = 1;
        nbChambres = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
        formulaireRecherche.add(nbChambres, grille);

        grille.gridx = 0;
        grille.gridy = 5;
        grille.gridwidth = 2;

        JButton boutonRechercher = new JButton("Rechercher");
        boutonRechercher.setBackground(new Color(0, 120, 215));
        boutonRechercher.setForeground(Color.WHITE);
        boutonRechercher.setFocusPainted(false);
        boutonRechercher.setFont(new Font("Arial", Font.BOLD, 14));
        boutonRechercher.setOpaque(true);
        boutonRechercher.setBorderPainted(false);
        formulaireRecherche.add(boutonRechercher, grille);
        add(formulaireRecherche, BorderLayout.CENTER);

        boutonRechercher.addActionListener(e -> lancerRecherche());
        setVisible(true);
    }

    public void setUtilisateurConnecte(Utilisateur utilisateur)
    {
        this.utilisateurConnecte = utilisateur;
    }

    private void ajouterBoutonConnexion()
    {
        boutonPanel.removeAll();
        JButton boutonConnexion = new JButton("Connexion / Inscription");
        boutonConnexion.setBackground(Color.WHITE);
        boutonConnexion.setForeground(new Color(0, 120, 215));
        boutonConnexion.setFocusPainted(false);
        boutonConnexion.setFont(new Font("Arial", Font.PLAIN, 11));
        boutonConnexion.setOpaque(true);
        boutonConnexion.setBorderPainted(false);
        boutonConnexion.setPreferredSize(new Dimension(150, 25));
        boutonConnexion.addActionListener(e -> new vueUtilisateur(controleur, this));
        boutonPanel.add(boutonConnexion);
        boutonPanel.revalidate();
        boutonPanel.repaint();
    }

    public void mettreAJourConnexion(Utilisateur utilisateur)
    {
        this.utilisateurConnecte = utilisateur;
        boutonPanel.removeAll();

        JButton boutonUtilisateur = new JButton(utilisateur.getPrenom());
        boutonUtilisateur.setBackground(new Color(0, 120, 215));
        boutonUtilisateur.setForeground(Color.WHITE);
        boutonUtilisateur.setFocusPainted(false);
        boutonUtilisateur.setFont(new Font("Arial", Font.PLAIN, 11));
        boutonUtilisateur.setPreferredSize(new Dimension(150, 25));

        JPopupMenu menu = new JPopupMenu();
        JMenuItem itemDeconnexion = new JMenuItem("Déconnexion");
        itemDeconnexion.addActionListener(e -> {utilisateurConnecte = null;ajouterBoutonConnexion();});
        menu.add(itemDeconnexion);

        boutonUtilisateur.addActionListener(e -> menu.show(boutonUtilisateur, 0, boutonUtilisateur.getHeight()));
        boutonPanel.add(boutonUtilisateur);
        boutonPanel.revalidate();
        boutonPanel.repaint();
    }

    public Utilisateur getUtilisateurConnecte()
    {
        return utilisateurConnecte;
    }

    public JPanel getBoutonPanel()
    {
        return boutonPanel;
    }

    private void lancerRecherche()
    {
        String ville = champVille.getText().trim();
        String type = (String) typeHebergement.getSelectedItem();
        int adultes = (Integer) nbAdultes.getValue();
        int enfants = (Integer) nbEnfants.getValue();

        DaoFactory daoFactory = DaoFactory.getInstance("booking", "root", "");
        siteDAO siteDao = new siteDAOImpl(daoFactory);
        logementDAO logementDao = new logementDAOImpl(daoFactory);

        List<Site> sites = siteDao.rechercherSites(ville, type, adultes, enfants);
        List<Logement> logements = new ArrayList<>();

        for(int i = 0; i < sites.size(); i++)
        {
            Site s = sites.get(i);
            List<Logement> logementsDuSite = logementDao.getLogementsParSite(s.getIdSite());
            logements.addAll(logementsDuSite);
        }

        if (logements.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Aucun logement trouvé.", "Résultats", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            RechercheHebergement recherche = new RechercheHebergement(sites, logements, adultes, enfants);
            recherche.setUtilisateurConnecte(utilisateurConnecte);
            recherche.ajouterBoutonUtilisateur(getBoutonPanel());
        }
    }
}
