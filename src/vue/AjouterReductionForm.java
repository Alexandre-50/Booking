package vue;

import javax.swing.*;
import java.awt.*;
import dao.ReductionDAO;
import modele.Reduction;
import java.sql.Date;

public class AjouterReductionForm extends JFrame {
    public AjouterReductionForm(GestionReductionsPanel parent, ReductionDAO reductionDAO) {
        setTitle("Ajouter une réduction");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField descriptionField = new JTextField();
        JTextField pourcentageField = new JTextField();
        JTextField dateDebutField = new JTextField("yyyy-mm-dd");
        JTextField dateFinField = new JTextField("yyyy-mm-dd");

        panel.add(new JLabel("Description:")); panel.add(descriptionField);
        panel.add(new JLabel("Pourcentage:")); panel.add(pourcentageField);
        panel.add(new JLabel("Date Début:")); panel.add(dateDebutField);
        panel.add(new JLabel("Date Fin:")); panel.add(dateFinField);

        JButton ajouterBtn = new JButton("Ajouter");
        panel.add(new JLabel());
        panel.add(ajouterBtn);

        ajouterBtn.addActionListener(e -> {
            try {
                Reduction r = new Reduction();
                r.setDescription(descriptionField.getText());
                r.setPourcentage(Double.parseDouble(pourcentageField.getText()));
                r.setDateDebut(Date.valueOf(dateDebutField.getText()));
                r.setDateFin(Date.valueOf(dateFinField.getText()));

                reductionDAO.addReduction(r);
                JOptionPane.showMessageDialog(this, "Réduction ajoutée avec succès.");
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
