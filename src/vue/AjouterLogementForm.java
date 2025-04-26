package vue;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import modele.Site;
import modele.Logement;
import dao.siteDAO;
import dao.logementDAO;

public class AjouterLogementForm extends JFrame {
    public AjouterLogementForm(siteDAO siteDAO, logementDAO logementDAO) {
        setTitle("Ajouter un logement à un site");
        setSize(450, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel formPanel = new JPanel(new GridLayout(10, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<Site> sites = siteDAO.getAllSites();
        String[] siteOptions = sites.stream()
                .map(s -> s.getIdSite() + " - " + s.getNom())
                .toArray(String[]::new);
        JComboBox<String> siteCombo = new JComboBox<>(siteOptions);

        JTextField descriptionField = new JTextField();
        JTextField prixField = new JTextField();
        JCheckBox wifiBox = new JCheckBox("Wi-Fi");
        JCheckBox climBox = new JCheckBox("Climatisation");
        JCheckBox fumeurBox = new JCheckBox("Fumeur autorisé");
        JCheckBox petitDejBox = new JCheckBox("Petit déjeuner inclus");
        JCheckBox vueMerBox = new JCheckBox("Vue sur mer");
        JCheckBox minibarBox = new JCheckBox("Minibar");

        formPanel.add(new JLabel("Choisir un site :")); formPanel.add(siteCombo);
        formPanel.add(new JLabel("Description:")); formPanel.add(descriptionField);
        formPanel.add(new JLabel("Prix par nuit:")); formPanel.add(prixField);
        formPanel.add(wifiBox); formPanel.add(climBox);
        formPanel.add(fumeurBox); formPanel.add(petitDejBox);
        formPanel.add(vueMerBox); formPanel.add(minibarBox);

        JButton ajouterLogementBtn = new JButton("Ajouter");
        formPanel.add(new JLabel());
        formPanel.add(ajouterLogementBtn);

        ajouterLogementBtn.addActionListener(ev -> {
            try {
                int idSite = Integer.parseInt(siteCombo.getSelectedItem().toString().split(" - ")[0]);
                Logement logement = new Logement();
                logement.setIdSite(idSite);
                logement.setDescription(descriptionField.getText());
                logement.setPrixParNuit(Double.parseDouble(prixField.getText()));
                logement.setWifi(wifiBox.isSelected());
                logement.setClimatisation(climBox.isSelected());
                logement.setFumeur(fumeurBox.isSelected());
                logement.setPetitDejeuner(petitDejBox.isSelected());
                logement.setVueMer(vueMerBox.isSelected());
                logement.setMinibar(minibarBox.isSelected());

                logementDAO.addLogement(logement);
                JOptionPane.showMessageDialog(this, "Le logement a été ajouté avec succès.");
                this.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        });

        add(formPanel);
        setVisible(true);
    }
}
