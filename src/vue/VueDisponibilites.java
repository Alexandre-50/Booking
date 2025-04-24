
/*package vue;

import dao.DaoFactory;
import dao.DateDisponibleDAO;
import dao.DateDisponibleDAOImpl;
import modele.Disponibilites;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VueDisponibilites extends JFrame
{

    public VueDisponibilites(int idLogement, String nomHebergement)
    {
        // Configuration de la fenêtre
        setTitle("Dates disponibles pour " + nomHebergement);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Création du titre affiché en haut
        JLabel titre = new JLabel("Dates disponibles pour " + nomHebergement, JLabel.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 20));
        titre.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(titre, BorderLayout.NORTH);

        // Panneau principal qui contiendra les boutons de dates
        JPanel panelDates = new JPanel();
        panelDates.setLayout(new BoxLayout(panelDates, BoxLayout.Y_AXIS));
        panelDates.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        panelDates.setBackground(Color.WHITE);

        // Permet d'ajouter un défilement si les dates sont trop nombreuses
        JScrollPane scrollPane = new JScrollPane(panelDates);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        // Connexion à la base de données pour récupérer les disponibilités
        DaoFactory daoFactory = DaoFactory.getInstance("booking", "root", "");
        DateDisponibleDAO dao = new DateDisponibleDAOImpl(daoFactory);
        List<Disponibilites> disponibilites = dao.getDisponibilitesParLogement(idLogement);

        // Si aucune date n'est disponible, afficher un message
        if (disponibilites.isEmpty())
        {
            JLabel vide = new JLabel("Aucune date disponible.");
            vide.setFont(new Font("Arial", Font.ITALIC, 14));
            vide.setForeground(Color.GRAY);
            panelDates.add(vide);
        }
        else
        {
            int total = disponibilites.size();
            JButton[] boutons = new JButton[total];
            int boutonsParLigne = 3;
            int indexBouton = 0;
            JPanel ligne = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
            ligne.setBackground(Color.WHITE);
            panelDates.add(ligne);

            for (int i = 0; i < total; i++)
            {
                Disponibilites dispo = disponibilites.get(i);

                if (!dispo.isEstDisponible())
                {
                    continue;
                }
                boutons[indexBouton] = new JButton(dispo.getDateDisponible().toString());
                boutons[indexBouton].setFont(new Font("Arial", Font.PLAIN, 11));
                boutons[indexBouton].setBackground(new Color(0, 120, 215));
                boutons[indexBouton].setForeground(Color.WHITE);
                boutons[indexBouton].setFocusPainted(false);
                boutons[indexBouton].setPreferredSize(new Dimension(110, 30));

                final String date = dispo.getDateDisponible().toString();
                boutons[indexBouton].addActionListener(e -> JOptionPane.showMessageDialog(this, "Créneau sélectionné : " + date, "Info", JOptionPane.INFORMATION_MESSAGE));

                ligne.add(boutons[indexBouton]);
                indexBouton++;

                if(indexBouton % boutonsParLigne == 0 && i != total - 1)
                {
                    ligne = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
                    ligne.setBackground(Color.WHITE);
                    panelDates.add(ligne);
                }
            }
        }

        // Panneau du bas avec le bouton de fermeture
        JPanel basPanel = new JPanel();
        basPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        basPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        basPanel.setBackground(Color.WHITE);

        JButton boutonFermer = new JButton("Fermer");
        boutonFermer.setFont(new Font("Arial", Font.PLAIN, 12));
        boutonFermer.setBackground(new Color(220, 220, 220));
        boutonFermer.setFocusPainted(false);
        boutonFermer.addActionListener(e -> dispose());
        basPanel.add(boutonFermer);

        add(basPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
}*/

package vue;

import dao.DaoFactory;
import dao.DateDisponibleDAO;
import dao.DateDisponibleDAOImpl;
import modele.Disponibilites;
import modele.Utilisateur;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VueDisponibilites extends JFrame
{

    private JPanel boutonPanel;

    public VueDisponibilites(int idLogement, String nomHebergement, Utilisateur utilisateurConnecte, JPanel boutonPanelOrigine)
    {
        setTitle("Dates disponibles pour " + nomHebergement);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Bandeau haut avec bouton utilisateur ou Connexion
        boutonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        boutonPanel.setOpaque(false);
        add(boutonPanel, BorderLayout.NORTH);
        ajouterBoutonUtilisateur(boutonPanelOrigine);

        // Titre de la fenêtre
        JLabel titre = new JLabel("Dates disponibles pour " + nomHebergement, JLabel.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 20));
        titre.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(titre, BorderLayout.SOUTH);

        // Panneau central contenant les boutons
        JPanel panelDates = new JPanel();
        panelDates.setLayout(new BoxLayout(panelDates, BoxLayout.Y_AXIS));
        panelDates.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        panelDates.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(panelDates);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        // Récupération des disponibilités
        DaoFactory daoFactory = DaoFactory.getInstance("booking", "root", "");
        DateDisponibleDAO dao = new DateDisponibleDAOImpl(daoFactory);
        List<Disponibilites> disponibilites = dao.getDisponibilitesParLogement(idLogement);

        if (disponibilites.isEmpty())
        {
            JLabel vide = new JLabel("Aucune date disponible.");
            vide.setFont(new Font("Arial", Font.ITALIC, 14));
            vide.setForeground(Color.GRAY);
            panelDates.add(vide);
        }
        else
        {
            int total = disponibilites.size();
            JButton[] boutons = new JButton[total];
            int boutonsParLigne = 3;
            int indexBouton = 0;
            JPanel ligne = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
            ligne.setBackground(Color.WHITE);
            panelDates.add(ligne);

            for (int i = 0; i < total; i++)
            {
                Disponibilites dispo = disponibilites.get(i);

                if (!dispo.isEstDisponible())
                {
                    continue;
                }

                boutons[indexBouton] = new JButton(dispo.getDateDisponible().toString());
                boutons[indexBouton].setFont(new Font("Arial", Font.PLAIN, 11));
                boutons[indexBouton].setBackground(new Color(0, 120, 215));
                boutons[indexBouton].setForeground(Color.WHITE);
                boutons[indexBouton].setFocusPainted(false);
                boutons[indexBouton].setPreferredSize(new Dimension(90, 26));

                final String date = dispo.getDateDisponible().toString();
                boutons[indexBouton].addActionListener(e -> JOptionPane.showMessageDialog(this, "Créneau sélectionné : " + date, "Info", JOptionPane.INFORMATION_MESSAGE));

                ligne.add(boutons[indexBouton]);
                indexBouton++;

                if (indexBouton % boutonsParLigne == 0 && i != total - 1)
                {
                    ligne = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
                    ligne.setBackground(Color.WHITE);
                    panelDates.add(ligne);
                }
            }
        }

        // Panneau du bas avec bouton fermer
        JPanel basPanel = new JPanel();
        basPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        basPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        basPanel.setBackground(Color.WHITE);

        JButton boutonFermer = new JButton("Fermer");
        boutonFermer.setFont(new Font("Arial", Font.PLAIN, 12));
        boutonFermer.setBackground(new Color(220, 220, 220));
        boutonFermer.setFocusPainted(false);
        boutonFermer.addActionListener(e -> dispose());
        basPanel.add(boutonFermer);

        add(basPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void ajouterBoutonUtilisateur(JPanel boutonPanelOrigine)
    {
        boutonPanel.removeAll();
        if (boutonPanelOrigine != null)
        {
            Component[] composants = boutonPanelOrigine.getComponents();
            for (int i=0;i<composants.length;i++)
            {
                boutonPanel.add(composants[i]);
            }
        }
        boutonPanel.revalidate();
        boutonPanel.repaint();
    }
}


