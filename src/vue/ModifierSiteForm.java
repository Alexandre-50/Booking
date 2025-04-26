package vue;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import modele.Site;
import dao.siteDAO;

public class ModifierSiteForm extends JFrame {
    public ModifierSiteForm(siteDAO siteDAO) {
        setTitle("Modifier un site");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        List<Site> sites = siteDAO.getAllSites();
        String[] siteOptions = sites.stream()
                .map(s -> s.getIdSite() + " - " + s.getNom())
                .toArray(String[]::new);

        JComboBox<String> siteCombo = new JComboBox<>(siteOptions);

        JTextField nomField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField adresseField = new JTextField();
        JTextField villeField = new JTextField();
        JTextField etoilesField = new JTextField();
        JTextField prixField = new JTextField();
        JTextField photoField = new JTextField();
        JCheckBox petitDejCheck = new JCheckBox("Petit déjeuner inclus");
        JCheckBox parkingCheck = new JCheckBox("Parking disponible");
        JCheckBox piscineCheck = new JCheckBox("Piscine disponible");
        JCheckBox transportCheck = new JCheckBox("Transports à proximité");

        JPanel formPanel = new JPanel(new GridLayout(13, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Sélectionnez un site:")); formPanel.add(siteCombo);
        formPanel.add(new JLabel("Nom:")); formPanel.add(nomField);
        formPanel.add(new JLabel("Description:")); formPanel.add(descriptionField);
        formPanel.add(new JLabel("Adresse:")); formPanel.add(adresseField);
        formPanel.add(new JLabel("Ville:")); formPanel.add(villeField);
        formPanel.add(new JLabel("Nombre d'étoiles:")); formPanel.add(etoilesField);
        formPanel.add(new JLabel("Prix par nuit:")); formPanel.add(prixField);
        formPanel.add(new JLabel("Photo:")); formPanel.add(photoField);
        formPanel.add(petitDejCheck); formPanel.add(parkingCheck);
        formPanel.add(piscineCheck); formPanel.add(transportCheck);

        JButton updateBtn = new JButton("Mettre à jour");
        formPanel.add(new JLabel());
        formPanel.add(updateBtn);

        siteCombo.addActionListener(e -> {
            int selectedId = Integer.parseInt(siteCombo.getSelectedItem().toString().split(" - ")[0]);
            Site site = siteDAO.getSiteParId(selectedId);
            if (site != null) {
                nomField.setText(site.getNom());
                descriptionField.setText(site.getDescription());
                adresseField.setText(site.getAdresse());
                villeField.setText(site.getVille());
                etoilesField.setText(String.valueOf(site.getNbEtoiles()));
                prixField.setText(String.valueOf(site.getPrixParNuit()));
                photoField.setText(site.getPhoto());
                petitDejCheck.setSelected(site.isPetitDejeuner());
                parkingCheck.setSelected(site.isParking());
                piscineCheck.setSelected(site.isPiscine());
                transportCheck.setSelected(site.isTransportProche());
            }
        });

        updateBtn.addActionListener(e -> {
            try {
                int selectedId = Integer.parseInt(siteCombo.getSelectedItem().toString().split(" - ")[0]);
                Site updated = new Site();
                updated.setIdSite(selectedId);
                updated.setNom(nomField.getText());
                updated.setDescription(descriptionField.getText());
                updated.setAdresse(adresseField.getText());
                updated.setVille(villeField.getText());
                updated.setNbEtoiles(Integer.parseInt(etoilesField.getText()));
                updated.setPrixParNuit(Double.parseDouble(prixField.getText()));
                updated.setPhoto(photoField.getText());
                updated.setPetitDejeuner(petitDejCheck.isSelected());
                updated.setParking(parkingCheck.isSelected());
                updated.setPiscine(piscineCheck.isSelected());
                updated.setTransportProche(transportCheck.isSelected());

                siteDAO.updateSite(updated);
                JOptionPane.showMessageDialog(this, "Site mis à jour avec succès.");
                this.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        });

        add(formPanel);
        setVisible(true);
    }
}