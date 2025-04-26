package vue;

import javax.swing.*;
import java.awt.*;
import modele.Site;
import dao.siteDAO;

public class AjouterSiteForm extends JFrame {
    public AjouterSiteForm(siteDAO siteDAO) {
        setTitle("Ajouter un nouveau site");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel formPanel = new JPanel(new GridLayout(14, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField nomField = new JTextField();
        JTextField descriptionField = new JTextField();
        String[] categories = {
                "1 - Hôtel", "2 - Villa", "3 - Chalet", "4 - Colocation / Loft",
                "5 - Maison", "6 - Gîte", "7 - Chambre", "8 - Loft",
                "9 - Colocation", "10 - Suite"
        };
        JComboBox<String> categorieCombo = new JComboBox<>(categories);
        JTextField adresseField = new JTextField();
        JTextField villeField = new JTextField();
        JTextField etoilesField = new JTextField();
        JTextField prixField = new JTextField();
        JTextField photoField = new JTextField();
        JCheckBox petitDejCheck = new JCheckBox("Petit déjeuner inclus");
        JCheckBox parkingCheck = new JCheckBox("Parking disponible");
        JCheckBox piscineCheck = new JCheckBox("Piscine disponible");
        JCheckBox transportCheck = new JCheckBox("Transports à proximité");

        formPanel.add(new JLabel("Nom:")); formPanel.add(nomField);
        formPanel.add(new JLabel("Description:")); formPanel.add(descriptionField);
        formPanel.add(new JLabel("Catégorie:")); formPanel.add(categorieCombo);
        formPanel.add(new JLabel("Adresse:")); formPanel.add(adresseField);
        formPanel.add(new JLabel("Ville:")); formPanel.add(villeField);
        formPanel.add(new JLabel("Nombre d'étoiles:")); formPanel.add(etoilesField);
        formPanel.add(new JLabel("Prix par nuit:")); formPanel.add(prixField);
        formPanel.add(new JLabel("Photo (nom de fichier):")); formPanel.add(photoField);
        formPanel.add(petitDejCheck); formPanel.add(new JLabel());
        formPanel.add(parkingCheck); formPanel.add(new JLabel());
        formPanel.add(piscineCheck); formPanel.add(new JLabel());
        formPanel.add(transportCheck); formPanel.add(new JLabel());

        JButton ajouterBtn = new JButton("Ajouter");
        formPanel.add(new JLabel());
        formPanel.add(ajouterBtn);

        ajouterBtn.addActionListener(ev -> {
            try {
                Site nouveauSite = new Site();
                nouveauSite.setNom(nomField.getText());
                nouveauSite.setDescription(descriptionField.getText());
                nouveauSite.setIdCategorie(Integer.parseInt(categorieCombo.getSelectedItem().toString().split(" - ")[0]));
                nouveauSite.setAdresse(adresseField.getText());
                nouveauSite.setVille(villeField.getText());
                nouveauSite.setNbEtoiles(Integer.parseInt(etoilesField.getText()));
                nouveauSite.setPrixParNuit(Double.parseDouble(prixField.getText()));
                nouveauSite.setPhoto(photoField.getText());
                nouveauSite.setPetitDejeuner(petitDejCheck.isSelected());
                nouveauSite.setParking(parkingCheck.isSelected());
                nouveauSite.setPiscine(piscineCheck.isSelected());
                nouveauSite.setTransportProche(transportCheck.isSelected());

                siteDAO.addSite(nouveauSite);
                JOptionPane.showMessageDialog(this, "Le site a été ajouté avec succès.");
                this.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        });

        add(formPanel);
        setVisible(true);
    }
}
