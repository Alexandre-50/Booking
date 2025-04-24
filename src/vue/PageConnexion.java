package vue;

import controleur.UtilisateurControleur;
import modele.Utilisateur;

import javax.swing.*;
import java.awt.*;

public class PageConnexion extends JFrame
{

    private JTextField emailChamp;
    private JPasswordField motDePasseChamp;
    private UtilisateurControleur controleur;

    public PageConnexion(UtilisateurControleur controleur)
    {
        this.controleur = controleur;

        setTitle("Connexion");
        setSize(350, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Connexion");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        emailChamp = new JTextField();
        motDePasseChamp = new JPasswordField();

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(creerChamp("Email", emailChamp));
        panel.add(creerChamp("Mot de passe", motDePasseChamp));

        JButton seConnecterBtn = new JButton("Se connecter");
        seConnecterBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        seConnecterBtn.setBackground(new Color(0, 120, 215));
        seConnecterBtn.setForeground(Color.WHITE);
        seConnecterBtn.setFocusPainted(false);
        seConnecterBtn.addActionListener(e -> tenterConnexion());

        panel.add(Box.createVerticalStrut(15));
        panel.add(seConnecterBtn);

        add(panel, BorderLayout.CENTER);
    }

    private JPanel creerChamp(String label, JComponent champ)
    {
        JPanel champPanel = new JPanel(new BorderLayout());
        champPanel.setBackground(Color.WHITE);
        JLabel lbl = new JLabel(label);
        champPanel.add(lbl, BorderLayout.NORTH);
        champPanel.add(champ, BorderLayout.CENTER);
        champ.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        return champPanel;
    }

    private void tenterConnexion()
    {
        String email = emailChamp.getText().trim();
        String mdp = new String(motDePasseChamp.getPassword()).trim();

        if (email.isEmpty() || mdp.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Utilisateur utilisateur = controleur.seConnecter(email, mdp);
        if (utilisateur != null)
        {
            JOptionPane.showMessageDialog(this, "Bienvenue " + utilisateur.getPrenom() + " !");
            dispose();
            new accueilClient(controleur); // On affiche la page accueil
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Email ou mot de passe incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}


/*package vue;

import controleur.UtilisateurControleur;
import modele.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PageConnexion extends JFrame
{
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private UtilisateurControleur controleur;

    public PageConnexion(UtilisateurControleur controleur)
    {
        this.controleur = controleur;

        setTitle("Connexion");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Connexion");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(new Color(33, 33, 33));

        emailField = new JTextField();
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Se connecter");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setBackground(new Color(0, 113, 194));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        loginButton.addActionListener(new LoginListener());

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(createLabeledField("Email", emailField));
        panel.add(createLabeledField("Mot de passe", passwordField));
        panel.add(Box.createVerticalStrut(15));
        panel.add(loginButton);

        add(panel);
    }

    private JPanel createLabeledField(String labelText, JComponent field)
    {
        JPanel fieldPanel = new JPanel(new BorderLayout(5, 5));
        fieldPanel.setBackground(Color.WHITE);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label.setForeground(new Color(55, 55, 55));

        fieldPanel.add(label, BorderLayout.NORTH);
        fieldPanel.add(field, BorderLayout.CENTER);
        field.setPreferredSize(new Dimension(300, 30));

        return fieldPanel;
    }

    private class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = emailField.getText().trim();
            String motDePasse = new String(passwordField.getPassword()).trim();

            if (email.isEmpty() || motDePasse.isEmpty()) {
                JOptionPane.showMessageDialog(ConnexionPanel.this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/projet_booking_bdd", "root", "")) {
                String sql = "SELECT * FROM utilisateurs WHERE email = ? AND motDePasse = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, email);
                stmt.setString(2, motDePasse);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(ConnexionPanel.this, "Connexion réussie !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new accueilClient(controleur).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(ConnexionPanel.this, "Email ou mot de passe incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(ConnexionPanel.this, "Erreur de connexion à la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}*/

