package vue;

import javax.swing.*;
import java.awt.*;
import dao.siteDAOImpl;
import dao.logementDAOImpl;
import dao.siteDAO;
import dao.logementDAO;
import dao.DaoFactory;

public class AccueilAdminPanel extends JFrame {
    private siteDAO siteDAO = new siteDAOImpl(DaoFactory.getInstance("booking", "root", ""));
    private logementDAO logementDAO = new logementDAOImpl(DaoFactory.getInstance("booking", "root", ""));

    public AccueilAdminPanel() {
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

        styleButton(btnAddSite);
        styleButton(btnAddLogement);
        styleButton(btnEdit);
        styleButton(btnDelete);
        styleButton(btnReduction);

        btnAddSite.addActionListener(e -> new AjouterSiteForm(siteDAO));
        btnAddLogement.addActionListener(e -> new AjouterLogementForm(siteDAO, logementDAO));
        btnEdit.addActionListener(e -> new ModifierSelectionPanel());
        btnDelete.addActionListener(e -> new SupprimerSelectionPanel(siteDAO, logementDAO));
        btnReduction.addActionListener(e -> new GestionReductionsPanel());

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnAddSite);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnAddLogement);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnEdit);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnDelete);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnReduction);

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
            AccueilAdminPanel adminPanel = new AccueilAdminPanel();
            adminPanel.setVisible(true);
        });
    }
}
