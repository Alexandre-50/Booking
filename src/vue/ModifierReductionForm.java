package vue;

import javax.swing.*;
import java.awt.*;
import dao.ReductionDAO;
import modele.Reduction;
import java.sql.Date;

public class ModifierReductionForm extends JFrame {
    public ModifierReductionForm(GestionReductionsPanel parent, ReductionDAO reductionDAO) {
        setTitle("Modifier une réduction");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JComboBox<String> reductionCombo = new JComboBox<>();
        for (Reduction r : reductionDAO.getAllReductions()) {
            reductionCombo.addItem(r.getIdReduction() + " - " + r.getDescription());
        }

        JTextField descriptionField = new JTextField();
        JTextField pourcentageField = new JTextField();
        JTextField dateDebutField = new JTextField("yyyy-mm-dd");
        JTextField dateFinField = new JTextField("yyyy-mm-dd");

        panel.add(new JLabel("Choisir réduction:")); panel.add(reductionCombo);
        panel.add(new JLabel("Description:")); panel.add(descriptionField);
        panel.add(new JLabel("Pourcentage:")); panel.add(pourcentageField);
        panel.add(new JLabel("Date Début:")); panel.add(dateDebutField);
        panel.add(new JLabel("Date Fin:")); panel.add(dateFinField);

        reductionCombo.addActionListener(e -> {
            int idReduction = Integer.parseInt(reductionCombo.getSelectedItem().toString().split(" - ")[0]);
            Reduction r = reductionDAO.getReductionById(idReduction);
            if (r != null) {
                descriptionField.setText(r.getDescription());
                pourcentageField.setText(String.valueOf(r.getPourcentage()));
                dateDebutField.setText(r.getDateDebut().toString());
                dateFinField.setText(r.getDateFin().toString());
            }
        });

        JButton modifierBtn = new JButton("Modifier");
        panel.add(new JLabel());
        panel.add(modifierBtn);

        modifierBtn.addActionListener(e -> {
            try {
                int idReduction = Integer.parseInt(reductionCombo.getSelectedItem().toString().split(" - ")[0]);
                Reduction r = new Reduction();
                r.setIdReduction(idReduction);
                r.setDescription(descriptionField.getText());
                r.setPourcentage(Double.parseDouble(pourcentageField.getText()));
                r.setDateDebut(Date.valueOf(dateDebutField.getText()));
                r.setDateFin(Date.valueOf(dateFinField.getText()));

                reductionDAO.updateReduction(r);
                JOptionPane.showMessageDialog(this, "Réduction modifiée avec succès.");
                parent.rafraichirTableau();
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
            }
        });

        add(panel);
        setVisible(true);
    }
}
