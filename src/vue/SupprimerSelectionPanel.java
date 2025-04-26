package vue;

import javax.swing.*;
import java.awt.*;
import dao.siteDAO;
import dao.logementDAO;

public class SupprimerSelectionPanel extends JFrame {
    private siteDAO siteDAO;
    private logementDAO logementDAO;

    public SupprimerSelectionPanel(siteDAO siteDAO, logementDAO logementDAO) {
        this.siteDAO = siteDAO;
        this.logementDAO = logementDAO;

        setTitle("Supprimer un site ou un logement");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Que souhaitez-vous supprimer ?");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnSupprimerSite = new JButton("Supprimer un site");
        JButton btnSupprimerLogement = new JButton("Supprimer un logement");
        styleButton(btnSupprimerSite);
        styleButton(btnSupprimerLogement);

        btnSupprimerSite.addActionListener(e -> new SupprimerSiteForm(siteDAO));
        btnSupprimerLogement.addActionListener(e -> new SupprimerLogementForm(logementDAO));

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnSupprimerSite);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnSupprimerLogement);

        add(panel);
        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(new Color(220, 53, 69));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }
}
