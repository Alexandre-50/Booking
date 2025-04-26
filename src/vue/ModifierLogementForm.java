package vue;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import modele.Logement;
import dao.logementDAO;
import dao.siteDAO;

public class ModifierLogementForm extends JFrame {
    public ModifierLogementForm(logementDAO logementDAO, siteDAO siteDAO) {
        setTitle("Modifier un logement");
        setSize(500, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        List<Logement> logements = logementDAO.getTousLesLogements();
        String[] logementOptions = logements.stream()
                .map(l -> l.getIdLogement() + " - " + l.getDescription())
                .toArray(String[]::new);

        JComboBox<String> logementCombo = new JComboBox<>(logementOptions);

        JTextField descriptionField = new JTextField();
        JTextField prixField = new JTextField();
        JCheckBox wifiBox = new JCheckBox("Wi-Fi");
        JCheckBox climBox = new JCheckBox("Climatisation");
        JCheckBox fumeurBox = new JCheckBox("Fumeur");
        JCheckBox petitDejBox = new JCheckBox("Petit déjeuner");
        JCheckBox vueMerBox = new JCheckBox("Vue mer");
        JCheckBox minibarBox = new JCheckBox("Minibar");

        JPanel formPanel = new JPanel(new GridLayout(10, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Choisir un logement :")); formPanel.add(logementCombo);
        formPanel.add(new JLabel("Description:")); formPanel.add(descriptionField);
        formPanel.add(new JLabel("Prix par nuit:")); formPanel.add(prixField);
        formPanel.add(wifiBox); formPanel.add(climBox);
        formPanel.add(fumeurBox); formPanel.add(petitDejBox);
        formPanel.add(vueMerBox); formPanel.add(minibarBox);

        JButton modifierBtn = new JButton("Mettre à jour");
        formPanel.add(new JLabel());
        formPanel.add(modifierBtn);

        logementCombo.addActionListener(e -> {
            int idLogement = Integer.parseInt(logementCombo.getSelectedItem().toString().split(" - ")[0]);
            Logement l = logementDAO.getLogementParId(idLogement);
            if (l != null) {
                descriptionField.setText(l.getDescription());
                prixField.setText(String.valueOf(l.getPrixParNuit()));
                wifiBox.setSelected(l.isWifi());
                climBox.setSelected(l.isClimatisation());
                fumeurBox.setSelected(l.estFumeur());
                petitDejBox.setSelected(l.isPetitDejeuner());
                vueMerBox.setSelected(l.isVueMer());
                minibarBox.setSelected(l.isMinibar());
            }
        });

        modifierBtn.addActionListener(e -> {
            try {
                int idLogement = Integer.parseInt(logementCombo.getSelectedItem().toString().split(" - ")[0]);
                Logement modifie = new Logement(
                        idLogement,
                        logementDAO.getLogementParId(idLogement).getIdSite(),
                        descriptionField.getText(),
                        Double.parseDouble(prixField.getText()),
                        wifiBox.isSelected(),
                        climBox.isSelected(),
                        fumeurBox.isSelected(),
                        petitDejBox.isSelected(),
                        vueMerBox.isSelected(),
                        minibarBox.isSelected()
                );

                logementDAO.updateLogement(modifie);
                JOptionPane.showMessageDialog(this, "Logement mis à jour avec succès.");
                this.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        });

        add(formPanel);
        setVisible(true);
    }
}
