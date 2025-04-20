package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class BarreDeRecherche{    
    public static void main(String[] args) {
        // Création de la fenêtre principale
        JFrame frame = new JFrame("Recherche");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 120);
        frame.setLocationRelativeTo(null); // centre la fenêtre

        // Création du panneau de recherche
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        searchPanel.setBackground(Color.WHITE);

        // Champ de recherche avec "placeholder"
        JTextField searchField = new JTextField("Recherche...", 25);
        searchField.setForeground(Color.GRAY);

        // Supprimer le texte quand on clique dedans (placeholder)
        searchField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Recherche...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Recherche...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });

        // Bouton de recherche stylisé
        JButton searchButton = new JButton("🔍");
        searchButton.setPreferredSize(new Dimension(50, 30));
        searchButton.setBackground(new Color(70, 130, 180));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);

        // Ajout des composants au panneau
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Ajout à la fenêtre
        frame.add(searchPanel);
        frame.setVisible(true);
    }
}
