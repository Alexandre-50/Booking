package vue;
import modele.Site;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;
import javax.swing.border.EmptyBorder;

public class RechercheHebergement extends JFrame
{

    public RechercheHebergement(List<Site> resultats, int nbAdultes, int nbEnfants)
    {
        setTitle("Résultats de la recherche");
        setSize(1000, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création du panneau principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(new Color(250, 250, 250));

        JScrollPane scrollPane = new JScrollPane(panelPrincipal);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Titre des résultats
        JLabel titre = new JLabel("Hébergements trouvés :", JLabel.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 18));
        titre.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        panelPrincipal.add(titre);

        // Affichage des résultats un à un
        for (int i = 0; i < resultats.size(); i++)
        {
            Site site = resultats.get(i);
            JPanel panneauSite = new JPanel();
            panneauSite.setLayout(new BorderLayout(10, 10));
            panneauSite.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            panneauSite.setMaximumSize(new Dimension(850, 160));
            panneauSite.setBackground(Color.WHITE);
            panneauSite.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel imageLabel;
            String cheminImage = "src/images/" + site.getPhoto(); // ex : appartParis.jpg

            File imageFile = new File(cheminImage);
            if (imageFile.exists())
            {
                ImageIcon icon = new ImageIcon(cheminImage);
                Image img = icon.getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH);
                imageLabel = new JLabel(new ImageIcon(img));
            }
            else
            {
                imageLabel = new JLabel("Image non dispo", JLabel.CENTER);
                imageLabel.setOpaque(true);
                imageLabel.setBackground(new Color(220, 220, 220));
                imageLabel.setHorizontalAlignment(JLabel.CENTER);
                imageLabel.setPreferredSize(new Dimension(150, 120));
            }

            // Informations textuelles sur le site
            JTextArea infos = new JTextArea();
            infos.setText(
                    "Nom : " + site.getNom() + "\nVille : " + site.getVille() + "\nPrix : " + site.getPrixParNuit() + " €/nuit" + "\n\nPour " + nbAdultes + " adultes et " + nbEnfants + " enfants."
            );
            infos.setFont(new Font("Arial", Font.PLAIN, 13));
            infos.setEditable(false);
            infos.setBackground(Color.WHITE);
            infos.setLineWrap(true);
            infos.setWrapStyleWord(true);

            // Ajout d'un bouton "Détails" fictif
            JButton boutonDetails = new JButton("Voir les détails");
            boutonDetails.setFont(new Font("Arial", Font.PLAIN, 12));
            boutonDetails.setBackground(new Color(0, 120, 215));
            boutonDetails.setForeground(Color.WHITE);
            boutonDetails.setFocusPainted(false);
            boutonDetails.setPreferredSize(new Dimension(140, 30));
            boutonDetails.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Détails de l'hébergement :\n\n" + site.getNom() + "\n" + site.getDescription() + "\nVille : " + site.getVille() + "\nPrix par nuit : " + site.getPrixParNuit() + "€", "Informations", JOptionPane.INFORMATION_MESSAGE);
            });

            // Panneau pour la droite avec infos et bouton
            JPanel panneauDroite = new JPanel(new BorderLayout());
            panneauDroite.setBackground(Color.WHITE);
            panneauDroite.add(infos, BorderLayout.CENTER);
            panneauDroite.add(boutonDetails, BorderLayout.SOUTH);

            panneauSite.add(imageLabel, BorderLayout.WEST);
            panneauSite.add(panneauDroite, BorderLayout.CENTER);

            panelPrincipal.add(panneauSite);
            panelPrincipal.add(Box.createVerticalStrut(10));
        }

        add(scrollPane);
        setVisible(true);
    }
}
