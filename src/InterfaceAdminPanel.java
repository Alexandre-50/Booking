import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import dao.siteDAO;
import dao.siteDAOImpl;
import dao.logementDAO;
import dao.logementDAOImpl;
import modele.Logement;
import modele.Site;
import dao.DaoFactory;

public class InterfaceAdminPanel extends JFrame {
    private siteDAO siteDAO = new siteDAOImpl(
            DaoFactory.getInstance("projet_booking_bdd", "root", "")
    );
    private logementDAO logementDAO = new logementDAOImpl(
            DaoFactory.getInstance("projet_booking_bdd", "root", "")
    );

    public InterfaceAdminPanel() {
        setTitle("Gestion des Sites et Logements");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel titleLabel = new JLabel("Espace Administrateur");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(new Color(33, 33, 33));

        JButton btnAddSite = new JButton("Ajouter un site");
        JButton btnAddLogement = new JButton("Ajouter un logement à un site");
        JButton btnEdit = new JButton("Modifier un site ou un logement");
        JButton btnDelete = new JButton("Supprimer un site ou un logement");
        JButton btnReduction = new JButton("Gérer les réductions des anciens clients");

        JButton[] buttons = {btnAddSite, btnAddLogement, btnEdit, btnDelete, btnReduction};
        for (JButton button : buttons) {
            styleButton(button);
            panel.add(button);
            panel.add(Box.createVerticalStrut(10));
        }

        btnAddLogement.addActionListener(e -> {
            JFrame logementForm = new JFrame("Ajouter un logement à un site");
            logementForm.setSize(450, 500);
            logementForm.setLocationRelativeTo(null);
            logementForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
            styleButton(ajouterLogementBtn);
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
                    JOptionPane.showMessageDialog(logementForm, "Le logement a été ajouté avec succès.");
                    logementForm.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(logementForm, "Erreur lors de l'ajout du logement : " + ex.getMessage());
                }
            });

            logementForm.add(formPanel);
            logementForm.setVisible(true);
        });

        panel.add(Box.createVerticalStrut(20));
        add(panel);
    }

    private void styleButton(JButton button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(new Color(0, 113, 194));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfaceAdminPanel adminPanel = new InterfaceAdminPanel();
            adminPanel.setVisible(true);
        });
    }
}
