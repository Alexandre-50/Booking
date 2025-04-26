package vue;

import javax.swing.*;
import java.awt.*;
import dao.siteDAO;
import dao.siteDAOImpl;
import dao.logementDAO;
import dao.logementDAOImpl;
import dao.DaoFactory;

public class ModifierSelectionPanel extends JFrame {
    private siteDAO siteDAO = new siteDAOImpl(DaoFactory.getInstance("booking", "root", ""));
    private logementDAO logementDAO = new logementDAOImpl(DaoFactory.getInstance("booking", "root", ""));

    public ModifierSelectionPanel() {
        setTitle("Modifier un site ou un logement");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Que souhaitez-vous modifier ?");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnModifierSite = new JButton("Modifier un site");
        JButton btnModifierLogement = new JButton("Modifier un logement");
        styleButton(btnModifierSite);
        styleButton(btnModifierLogement);

        btnModifierSite.addActionListener(e -> {
            new ModifierSiteForm(siteDAO);
        });

        btnModifierLogement.addActionListener(e -> {
            new ModifierLogementForm(logementDAO, siteDAO);
        });

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnModifierSite);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnModifierLogement);

        add(panel);
        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(new Color(0, 113, 194));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }
}
