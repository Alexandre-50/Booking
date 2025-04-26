package vue;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import modele.Site;
import dao.siteDAO;

public class SupprimerSiteForm extends JFrame {
    public SupprimerSiteForm(siteDAO siteDAO) {
        setTitle("Supprimer un site");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        List<Site> sites = siteDAO.getAllSites();
        String[] siteOptions = sites.stream()
                .map(s -> s.getIdSite() + " - " + s.getNom())
                .toArray(String[]::new);

        JComboBox<String> siteCombo = new JComboBox<>(siteOptions);
        JButton supprimerBtn = new JButton("Supprimer");

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(new JLabel("Choisissez un site à supprimer :"));
        panel.add(siteCombo);
        panel.add(supprimerBtn);

        supprimerBtn.addActionListener(e -> {
            int selectedId = Integer.parseInt(siteCombo.getSelectedItem().toString().split(" - ")[0]);
            int confirm = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir supprimer ce site ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                siteDAO.supprimerSiteParId(selectedId);
                JOptionPane.showMessageDialog(this, "Site supprimé avec succès.");
                this.dispose();
            }
        });

        add(panel);
        setVisible(true);
    }
}
