package vue;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import modele.Logement;
import dao.logementDAO;

public class SupprimerLogementForm extends JFrame {
    public SupprimerLogementForm(logementDAO logementDAO) {
        setTitle("Supprimer un logement");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        List<Logement> logements = logementDAO.getTousLesLogements();
        String[] logementOptions = logements.stream()
                .map(l -> l.getIdLogement() + " - " + l.getDescription())
                .toArray(String[]::new);

        JComboBox<String> logementCombo = new JComboBox<>(logementOptions);
        JButton supprimerBtn = new JButton("Supprimer");

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(new JLabel("Choisissez un logement à supprimer :"));
        panel.add(logementCombo);
        panel.add(supprimerBtn);

        supprimerBtn.addActionListener(e -> {
            int id = Integer.parseInt(logementCombo.getSelectedItem().toString().split(" - ")[0]);
            int confirm = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir supprimer ce logement ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                logementDAO.supprimerLogementParId(id);
                JOptionPane.showMessageDialog(this, "Logement supprimé avec succès.");
                this.dispose();
            }
        });

        add(panel);
        setVisible(true);
    }
}
