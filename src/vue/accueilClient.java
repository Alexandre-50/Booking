package vue;

import dao.DaoFactory;
import dao.siteDAO;
import dao.siteDAOImpl;
import modele.Site;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class accueilClient extends JFrame {

    public accueilClient() {
        // Titre de la fenêtre
        setTitle("Accueil - Hébergements disponibles");

        // Taille de la fenêtre
        setSize(1000, 700);

        // Fermer l'application quand on ferme la fenêtre
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Centrer la fenêtre à l'écran
        setLocationRelativeTo(null);

        // Utiliser une disposition avec 5 zones : Nord, Sud, Est, Ouest, Centre
        setLayout(new BorderLayout());

        // Connexion à la base de données
        DaoFactory daoFactory = DaoFactory.getInstance("booking", "root", "");
        siteDAO siteDAO = new siteDAOImpl(daoFactory);

        // Logo Booking en haut de la page
        try {
            //ImageIcon logo = new ImageIcon(getClass().getResource("/images/logo.png"));
            //Image img = logo.getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH);
            //JLabel logoLabel = new JLabel(new ImageIcon(img), JLabel.CENTER);
            //logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            //add(logoLabel, BorderLayout.NORTH);
        } catch (Exception e) {
            JLabel titre = new JLabel("Bienvenue sur Booking", JLabel.CENTER);
            titre.setFont(new Font("Arial", Font.BOLD, 24));
            titre.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
            add(titre, BorderLayout.NORTH);
        }

        // Panneau qui va contenir tous les hébergements
        JPanel contentPanel = new JPanel();

        // Utiliser un affichage vertical des hébergements
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Ajouter une barre de défilement au panneau principal
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Récupérer la liste des sites depuis la base de données
        List<Site> sites = siteDAO.getAllSites();

        // Boucle avec index
        for (int i = 0; i < sites.size(); i++) {
            Site site = sites.get(i);
            JPanel panelSite = new JPanel(new BorderLayout(10, 10));
            panelSite.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            panelSite.setMaximumSize(new Dimension(850, 160));
            panelSite.setPreferredSize(new Dimension(850, 160));
            panelSite.setMinimumSize(new Dimension(850, 160));

            // Image du site
            JLabel imageLabel;
            try {
                String imagePath = "/images/" + site.getPhoto();
                java.net.URL imgURL = getClass().getResource(imagePath);
                if (imgURL != null) {
                    ImageIcon icon = new ImageIcon(imgURL);
                    Image img = icon.getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH);
                    imageLabel = new JLabel(new ImageIcon(img));
                    imageLabel.setPreferredSize(new Dimension(150, 120));
                    imageLabel.setMinimumSize(new Dimension(150, 120));
                    imageLabel.setMaximumSize(new Dimension(150, 120));
                } else {
                    imageLabel = new JLabel("[Image non disponible]");
                }
            } catch (Exception e) {
                imageLabel = new JLabel("[Erreur de chargement image]");
            }

            // Texte avec les informations du site
            JTextArea info = new JTextArea();
            info.setText(site.getNom() +
                    "\nVille : " + site.getVille() +
                    "\nPrix : " + site.getPrixParNuit() + " €/nuit" +
                    "\n" + site.getDescription());
            info.setEditable(false);
            info.setBackground(null);
            info.setBorder(null);

            // Bouton pour voir les détails du site
            JButton boutonDetails = new JButton("Détails");
            boutonDetails.setFont(new Font("Arial", Font.PLAIN, 10));
            boutonDetails.setPreferredSize(new Dimension(70, 25));
            boutonDetails.setMaximumSize(new Dimension(70, 25));
            boutonDetails.setMinimumSize(new Dimension(70, 25));
            boutonDetails.addActionListener(e -> {
                JOptionPane.showMessageDialog(this,
                        "Détails de l'hébergement :\n" +
                                site.getNom() + " à " + site.getVille());
            });

            // Ajouter les éléments au panneau de chaque site
            panelSite.add(imageLabel, BorderLayout.WEST);
            panelSite.add(info, BorderLayout.CENTER);
            panelSite.add(boutonDetails, BorderLayout.EAST);

            // Ajouter le panneau du site à la liste générale
            contentPanel.add(panelSite);
            contentPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espace entre les sites
        }

        // Rendre la fenêtre visible
        setVisible(true);
    }
}
