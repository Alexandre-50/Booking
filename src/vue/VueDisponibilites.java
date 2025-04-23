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

    private JPanel panelDates;
    private JButton[] boutonsDates;

    public VueDisponibilites(int idLogement, String nomHebergement)
    {
        setTitle("Dates disponibles pour " + nomHebergement);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titre = new JLabel("Dates disponibles pour " + nomHebergement, JLabel.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 20));
        titre.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(titre, BorderLayout.NORTH);

        panelDates = new JPanel();
        panelDates.setLayout(new GridLayout(0, 3, 10, 10));
        panelDates.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        panelDates.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(panelDates);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        afficherDisponibilites(idLogement);

        JPanel basPanel = new JPanel();
        basPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        basPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        basPanel.setBackground(Color.WHITE);

        JButton fermer = new JButton("Fermer");
        fermer.setFont(new Font("Arial", Font.PLAIN, 12));
        fermer.setBackground(new Color(220, 220, 220));
        fermer.setFocusPainted(false);
        fermer.addActionListener(e -> dispose());
        basPanel.add(fermer);

        add(basPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void afficherDisponibilites(int idLogement)
    {
        DaoFactory daoFactory = DaoFactory.getInstance("booking", "root", "");
        DateDisponibleDAO dao = new DateDisponibleDAOImpl(daoFactory);
        List<Disponibilites> disponibilites = dao.getDisponibilitesParLogement(idLogement);

        if (disponibilites.isEmpty())
        {
            JLabel vide = new JLabel("Aucune date disponible.");
            vide.setFont(new Font("Arial", Font.ITALIC, 14));
            vide.setForeground(Color.GRAY);
            vide.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelDates.add(vide);
        } else {
            boutonsDates = new JButton[disponibilites.size()];

            for (int i = 0; i < disponibilites.size(); i++)
            {
                Disponibilites d = disponibilites.get(i);
                if (d.isEstDisponible())
                {
                    boutonsDates[i] = new JButton(d.getDateDisponible().toString());
                    boutonsDates[i].setFont(new Font("Arial", Font.PLAIN, 12));
                    boutonsDates[i].setFocusPainted(false);
                    boutonsDates[i].setBackground(new Color(0, 150, 100));
                    boutonsDates[i].setForeground(Color.WHITE);
                    boutonsDates[i].setPreferredSize(new Dimension(20,20));
                    boutonsDates[i].setMaximumSize(new Dimension(20,20));
                    boutonsDates[i].setMinimumSize(new Dimension(20,20));

                    int finalIndex = i;
                    boutonsDates[i].addActionListener(e -> {
                        JOptionPane.showMessageDialog(this,
                                "Créneau sélectionné : " + disponibilites.get(finalIndex).getDateDisponible(),
                                "Créneau", JOptionPane.INFORMATION_MESSAGE);
                    });
                    panelDates.add(boutonsDates[i]);
                }
            }
        }
    }
*/

package vue;

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
}
