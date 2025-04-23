/*package vue;

import modele.Site;
import modele.Logement;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RechercheHebergement extends JFrame
{
    private JPanel panelPrincipal;
    private List<Site> Sites;
    private List<Logement> Logements;
    private int nbAdultes;
    private int nbEnfants;

    private JCheckBox checkWifi;
    private JCheckBox checkClimatisation;
    private JCheckBox checkFumeur;
    private JCheckBox checkPetitDejeuner;
    private JCheckBox checkVueMer;
    private JCheckBox checkMinibar;
    private JCheckBox checkParking;
    private JCheckBox checkPiscine;
    private JCheckBox checkTransport;

    public RechercheHebergement(List<Site> sites, List<Logement> logements, int nbAdultes, int nbEnfants)
    {
        this.Sites = sites;
        this.Logements = logements;
        this.nbAdultes = nbAdultes;
        this.nbEnfants = nbEnfants;

        setTitle("Résultats de la recherche");
        setSize(1000, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(new Color(250, 250, 250));

        JScrollPane scrollPane = new JScrollPane(panelPrincipal);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JPanel panelFiltres = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltres.setBorder(BorderFactory.createTitledBorder("Filtres d'options"));
        panelFiltres.setBackground(Color.WHITE);

        JCheckBox[] filtres = new JCheckBox[9];
        filtres[0] = checkWifi = new JCheckBox("WiFi");
        filtres[1] = checkClimatisation = new JCheckBox("Climatisation");
        filtres[2] = checkFumeur = new JCheckBox("Fumeur");
        filtres[3] = checkPetitDejeuner = new JCheckBox("Petit Déjeuner");
        filtres[4] = checkVueMer = new JCheckBox("Vue Mer");
        filtres[5] = checkMinibar = new JCheckBox("Minibar");
        filtres[6] = checkParking = new JCheckBox("Parking");
        filtres[7] = checkPiscine = new JCheckBox("Piscine");
        filtres[8] = checkTransport = new JCheckBox("Transport Proche");

        for (int i = 0; i < filtres.length; i++)
        {
            panelFiltres.add(filtres[i]);
            filtres[i].addActionListener(e -> appliquerFiltres());
        }

        panelPrincipal.add(panelFiltres);
        afficherSitesEtLogements(Sites,Logements);

        add(scrollPane);
        setVisible(true);
    }

    private void afficherSitesEtLogements(List<Site> sites, List<Logement> logements)
    {
        panelPrincipal.removeAll();

        JLabel titre = new JLabel("Hébergements trouvés :", JLabel.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 18));
        titre.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panelPrincipal.add(titre);

        JPanel panelFiltresActuel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltresActuel.setBackground(Color.WHITE);
        JCheckBox[] filtres = {checkWifi, checkClimatisation, checkFumeur, checkPetitDejeuner, checkVueMer, checkMinibar, checkParking, checkPiscine, checkTransport};
        for (int i = 0; i < filtres.length; i++)
        {
            panelFiltresActuel.add(filtres[i]);
        }
        panelPrincipal.add(panelFiltresActuel);

        for (int i = 0; i < logements.size(); i++)
        {
            Logement logement = logements.get(i);
            Site siteAssocie = sites.stream().filter(s -> s.getIdSite() == logement.getIdSite()).findFirst().orElse(null);
            if (siteAssocie == null)
                continue;

            final Site finalSite = siteAssocie;
            final Logement finalLogement = logement;

            JPanel panneauSite = new JPanel(new BorderLayout(10, 10));
            panneauSite.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            panneauSite.setMaximumSize(new Dimension(850, 160));
            panneauSite.setBackground(Color.WHITE);
            panneauSite.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel imageLabel = new JLabel();
            imageLabel.setPreferredSize(new Dimension(150, 120));
            imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            imageLabel.setOpaque(true);

            try{
                String imagePath = "/images/" + finalSite.getPhoto();
                ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
                Image img = icon.getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
            } catch (Exception e) {
                imageLabel.setText("Image non dispo");
                imageLabel.setHorizontalAlignment(JLabel.CENTER);
                imageLabel.setBackground(new Color(220, 220, 220));
            }

            JTextArea infos = new JTextArea();
            infos.setText("Nom : " + finalSite.getNom() +
                    "\nVille : " + finalSite.getVille() +
                    "\nPrix : " + finalLogement.getPrixParNuit() + " €/nuit"
            );
            infos.setFont(new Font("Arial", Font.PLAIN, 13));
            infos.setEditable(false);
            infos.setBackground(Color.WHITE);
            infos.setLineWrap(true);
            infos.setWrapStyleWord(true);

            JButton boutonDetails = new JButton("Voir les détails");
            boutonDetails.setFont(new Font("Arial", Font.PLAIN, 12));
            boutonDetails.setBackground(new Color(0, 120, 215));
            boutonDetails.setForeground(Color.WHITE);
            boutonDetails.setFocusPainted(false);
            boutonDetails.setPreferredSize(new Dimension(140, 30));
            boutonDetails.addActionListener(e -> {
                JOptionPane.showMessageDialog(this,
                        "Détails de l'hébergement :\n\n" + finalSite.getNom() +
                                "\n" + finalSite.getDescription() +
                                "\nVille : " + finalSite.getVille() +
                                "\nPrix : " + finalLogement.getPrixParNuit() + "€",
                        "Détails", JOptionPane.INFORMATION_MESSAGE);
            });

            JPanel droite = new JPanel(new BorderLayout());
            droite.setBackground(Color.WHITE);
            droite.add(infos, BorderLayout.CENTER);
            droite.add(boutonDetails, BorderLayout.SOUTH);

            panneauSite.add(imageLabel, BorderLayout.WEST);
            panneauSite.add(droite, BorderLayout.CENTER);
            panelPrincipal.add(panneauSite);
            panelPrincipal.add(Box.createVerticalStrut(10));
        }

        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }

    private void appliquerFiltres() {
        List<Logement> filtresLogements = new ArrayList<>();

        for (int i = 0; i < Logements.size(); i++)
        {
            Logement logement = Logements.get(i);
            Site site = Sites.stream().filter(s -> s.getIdSite() == logement.getIdSite()).findFirst().orElse(null);
            if (site == null) continue;

            if (checkWifi.isSelected() && !logement.isWifi()) continue;
            if (checkClimatisation.isSelected() && !logement.isClimatisation()) continue;
            if (checkFumeur.isSelected() && !logement.estFumeur()) continue;
            if (checkPetitDejeuner.isSelected() && !logement.isPetitDejeuner()) continue;
            if (checkVueMer.isSelected() && !logement.isVueMer()) continue;
            if (checkMinibar.isSelected() && !logement.isMinibar()) continue;
            if (checkParking.isSelected() && !site.isParking()) continue;
            if (checkPiscine.isSelected() && !site.isPiscine()) continue;
            if (checkTransport.isSelected() && !site.isTransportProche()) continue;

            filtresLogements.add(logement);
        }

        afficherSitesEtLogements(Sites, filtresLogements);
    }
}*/

package vue;

import modele.Site;
import modele.Logement;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RechercheHebergement extends JFrame
{
    private JPanel panelPrincipal;
    private List<Site> Sites;
    private List<Logement> Logements;
    private int nbAdultes;
    private int nbEnfants;

    private JCheckBox checkWifi;
    private JCheckBox checkClimatisation;
    private JCheckBox checkFumeur;
    private JCheckBox checkPetitDejeuner;
    private JCheckBox checkVueMer;
    private JCheckBox checkMinibar;
    private JCheckBox checkParking;
    private JCheckBox checkPiscine;
    private JCheckBox checkTransport;

    private JPanel panelFiltres;

    public RechercheHebergement(List<Site> sites, List<Logement> logements, int nbAdultes, int nbEnfants)
    {
        this.Sites = sites;
        this.Logements = logements;
        this.nbAdultes = nbAdultes;
        this.nbEnfants = nbEnfants;

        setTitle("Résultats de la recherche");
        setSize(1000, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(new Color(250, 250, 250));

        JScrollPane scrollPane = new JScrollPane(panelPrincipal);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        panelFiltres = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltres.setBorder(BorderFactory.createTitledBorder("Options"));

        JCheckBox[] filtres = new JCheckBox[9];
        filtres[0] = checkWifi = new JCheckBox("WiFi");
        filtres[1] = checkClimatisation = new JCheckBox("Climatisation");
        filtres[2] = checkFumeur = new JCheckBox("Fumeur");
        filtres[3] = checkPetitDejeuner = new JCheckBox("Petit Déjeuner");
        filtres[4] = checkVueMer = new JCheckBox("Vue Mer");
        filtres[5] = checkMinibar = new JCheckBox("Minibar");
        filtres[6] = checkParking = new JCheckBox("Parking");
        filtres[7] = checkPiscine = new JCheckBox("Piscine");
        filtres[8] = checkTransport = new JCheckBox("Transport Proche");

        for (int i = 0; i < filtres.length; i++) {
            panelFiltres.add(filtres[i]);
            filtres[i].addActionListener(e -> appliquerFiltres());
        }

        panelPrincipal.add(panelFiltres);
        afficherSitesEtLogements(Sites, Logements);

        add(scrollPane);
        setVisible(true);
    }

    private void afficherSitesEtLogements(List<Site> sites, List<Logement> logements)
    {
        panelPrincipal.removeAll();

        // Reprendre les filtres
        panelPrincipal.add(panelFiltres); // Important : on remet le panel déjà existant

        for (int i = 0; i < logements.size(); i++)
        {
            Logement logement = logements.get(i);
            Site siteAssocie = sites.stream().filter(s -> s.getIdSite() == logement.getIdSite()).findFirst().orElse(null);
            if (siteAssocie == null)
                continue;

            final Site finalSite = siteAssocie;
            final Logement finalLogement = logement;

            JPanel panneauSite = new JPanel(new BorderLayout(10, 10));
            panneauSite.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            panneauSite.setMaximumSize(new Dimension(850, 160));
            panneauSite.setBackground(Color.WHITE);
            panneauSite.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel imageLabel = new JLabel();
            imageLabel.setPreferredSize(new Dimension(150, 120));
            imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            imageLabel.setOpaque(true);

            try {
                String imagePath = "/images/" + finalSite.getPhoto();
                ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
                Image img = icon.getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(img));
            } catch (Exception e) {
                imageLabel.setText("Image non dispo");
                imageLabel.setHorizontalAlignment(JLabel.CENTER);
                imageLabel.setBackground(new Color(220, 220, 220));
            }

            JTextArea infos = new JTextArea();
            infos.setText("Nom : " + finalSite.getNom() +
                    "\nVille : " + finalSite.getVille() +
                    "\nPrix : " + finalLogement.getPrixParNuit() + " €/nuit"
            );
            infos.setFont(new Font("Arial", Font.PLAIN, 13));
            infos.setEditable(false);
            infos.setBackground(Color.WHITE);
            infos.setLineWrap(true);
            infos.setWrapStyleWord(true);

            JButton boutonDetails = new JButton("Voir les détails");
            boutonDetails.setFont(new Font("Arial", Font.PLAIN, 12));
            boutonDetails.setBackground(new Color(0, 120, 215));
            boutonDetails.setForeground(Color.WHITE);
            boutonDetails.setFocusPainted(false);
            boutonDetails.setPreferredSize(new Dimension(140, 30));
            boutonDetails.addActionListener(e -> {
                JOptionPane.showMessageDialog(this,
                        "Détails de l'hébergement :\n\n" + finalSite.getNom() +
                                "\n" + finalSite.getDescription() +
                                "\nVille : " + finalSite.getVille() +
                                "\nPrix : " + finalLogement.getPrixParNuit() + "€",
                        "Détails", JOptionPane.INFORMATION_MESSAGE);
            });

            JButton boutonDispo = new JButton("Voir les dates disponibles");
            boutonDispo.setFont(new Font("Arial", Font.PLAIN, 12));
            boutonDispo.setBackground(new Color(0, 120, 215));
            boutonDispo.setForeground(Color.WHITE);
            boutonDispo.setFocusPainted(false);
            boutonDispo.setPreferredSize(new Dimension(180, 30));
            boutonDispo.addActionListener(e -> new VueDisponibilites(finalLogement.getIdLogement(), finalSite.getNom()));
            JPanel boutons = new JPanel(new FlowLayout(FlowLayout.LEFT));
            boutons.setBackground(Color.WHITE);
            boutons.add(boutonDetails);
            boutons.add(boutonDispo);

            JPanel droite = new JPanel(new BorderLayout());
            droite.setBackground(Color.WHITE);
            droite.add(infos, BorderLayout.CENTER);
            droite.add(boutons, BorderLayout.SOUTH);

            panneauSite.add(imageLabel, BorderLayout.WEST);
            panneauSite.add(droite, BorderLayout.CENTER);
            panelPrincipal.add(panneauSite);
            panelPrincipal.add(Box.createVerticalStrut(10));
        }

        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }

    private void appliquerFiltres()
    {
        List<Logement> filtresLogements = new ArrayList<>();
        for (int i = 0; i < Logements.size(); i++)
        {
            Logement logement = Logements.get(i);
            Site site = Sites.stream().filter(s -> s.getIdSite() == logement.getIdSite()).findFirst().orElse(null);
            if (site == null) continue;
            if (checkWifi.isSelected() && !logement.isWifi())
            {
                continue;
            }
            if (checkClimatisation.isSelected() && !logement.isClimatisation())
            {
                continue;
            }
            if (checkFumeur.isSelected() && !logement.estFumeur())
            {
                continue;
            }
            if (checkPetitDejeuner.isSelected() && !logement.isPetitDejeuner())
            {
                continue;
            }
            if (checkVueMer.isSelected() && !logement.isVueMer())
            {
                continue;
            }
            if (checkMinibar.isSelected() && !logement.isMinibar())
            {
                continue;
            }
            if (checkParking.isSelected() && !site.isParking())
            {
                continue;
            }
            if (checkPiscine.isSelected() && !site.isPiscine())
            {
                continue;
            }
            if (checkTransport.isSelected() && !site.isTransportProche())
            {
                continue;
            }
            filtresLogements.add(logement);
        }
        afficherSitesEtLogements(Sites, filtresLogements);
    }
}

