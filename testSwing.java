
package modèle;
import javax.swing.*;
import java.awt.*;


public class BarreDerecherche {
    public static void main(String[] args) {
        // Création de la fenêtre principale
        JFrame frame = new JFrame("Barre de recherche");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 100);

        // Création du panneau de recherche
        JPanel searchPanel = new JPanel(new FlowLayout());

        JLabel searchLabel = new JLabel("Recherche :");
        JTextField searchField = new JTextField(20); // 20 colonnes
        JButton searchButton = new JButton("Rechercher");

        // Ajout des composants au panneau
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Ajout du panneau à la fenêtre
        frame.add(searchPanel);

        // Rendre la fenêtre visible
        frame.setVisible(true);
    }
}
