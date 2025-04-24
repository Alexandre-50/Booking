package vue;
import controleur.UtilisateurControleur;
import modele.Utilisateur;
import vue.PageConnexion;
import vue.PageInscription;

import javax.swing.*;
import java.awt.*;

public class vueUtilisateur extends JFrame
{
    private UtilisateurControleur controleur;

    public vueUtilisateur(UtilisateurControleur controller)
    {
        this.controleur = controller;
        setTitle("Booking - Connexion / Inscription Client");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelBoutons = new JPanel();
        panelBoutons.setLayout(new BoxLayout(panelBoutons, BoxLayout.Y_AXIS));
        panelBoutons.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));

        JButton btnInscription = new JButton("S'inscrire");
        JButton btnConnexion = new JButton("Se connecter");
        btnInscription.setBackground(new Color(0,120,215));
        btnInscription.setForeground(Color.WHITE);
        btnInscription.setFocusPainted(false);
        btnInscription.setFont(new Font("Arial", Font.BOLD, 14));

        btnConnexion.setBackground(new Color(0,120,215));
        btnConnexion.setForeground(Color.WHITE);
        btnConnexion.setFocusPainted(false);
        btnConnexion.setFont(new Font("Arial", Font.BOLD, 14));

        btnInscription.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnConnexion.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnInscription.addActionListener(e -> CreationCompte());
        btnConnexion.addActionListener(e -> ConnexionCompte());

        panelBoutons.add(btnInscription);
        panelBoutons.add(Box.createVerticalStrut(15));
        panelBoutons.add(btnConnexion);

        add(panelBoutons, BorderLayout.CENTER);

        setVisible(true);
    }
    private void CreationCompte()
    {
        PageInscription inscription = new PageInscription(controleur);
        inscription.setVisible(true);
    }

    private void ConnexionCompte()
    {
        PageConnexion connexion = new PageConnexion(controleur);
        connexion.setVisible(true);
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

public class vueUtilisateur extends JFrame
{
    private UtilisateurControleur controleur;

    public vueUtilisateur(UtilisateurControleur controleur)
    {
        this.controleur = controleur;
        setTitle("Booking - Connexion / Inscription Client");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelBoutons = new JPanel();
        panelBoutons.setLayout(new BoxLayout(panelBoutons, BoxLayout.Y_AXIS));
        panelBoutons.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));

        JButton btnInscription = new JButton("S'inscrire");
        JButton btnConnexion = new JButton("Se connecter");

        btnInscription.setBackground(new Color(0,120,215));
        btnInscription.setForeground(Color.WHITE);
        btnInscription.setFocusPainted(false);
        btnInscription.setFont(new Font("Arial", Font.BOLD, 14));

        btnConnexion.setBackground(new Color(0,120,215));
        btnConnexion.setForeground(Color.WHITE);
        btnConnexion.setFocusPainted(false);
        btnConnexion.setFont(new Font("Arial", Font.BOLD, 14));

        btnInscription.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnConnexion.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnInscription.addActionListener(e -> ouvrirCreationPanel());
        btnConnexion.addActionListener(e -> ouvrirConnexionPanel());

        panelBoutons.add(btnInscription);
        panelBoutons.add(Box.createVerticalStrut(15));
        panelBoutons.add(btnConnexion);

        add(panelBoutons, BorderLayout.CENTER);
        setVisible(true);
    }

    private void ouvrirCreationPanel()
    {
        JFrame frame = new JFrame("Inscription");
        frame.setSize(400, 425);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Créer un compte");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(new Color(33, 33, 33));

        JTextField nomField = new JTextField();
        JTextField prenomField = new JTextField();
        JTextField mailField = new JTextField();
        JPasswordField motDePasseField = new JPasswordField();

        styleField(nomField);
        styleField(prenomField);
        styleField(mailField);
        styleField(motDePasseField);

        JButton submitButton = new JButton("Créer un compte");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setBackground(new Color(0, 113, 194));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        submitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        submitButton.addActionListener(e -> {
            String nom = nomField.getText().trim();
            String prenom = prenomField.getText().trim();
            String mail = mailField.getText().trim();
            String motdepasse = new String(motDePasseField.getPassword()).trim();

            if (nom.isEmpty() || prenom.isEmpty() || mail.isEmpty() || motdepasse.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Booking", "root", "")) {
                String checkQuery = "SELECT email FROM utilisateurs WHERE email = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
                checkStmt.setString(1, mail);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(frame, "Cet email est déjà utilisé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sql = "INSERT INTO utilisateurs (nom, prenom, email, motDePasse) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, nom);
                statement.setString(2, prenom);
                statement.setString(3, mail);
                statement.setString(4, motdepasse);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(frame, "Compte créé avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Erreur lors de l'enregistrement.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Erreur de connexion à la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(createLabeledField("Nom", nomField));
        panel.add(createLabeledField("Prénom", prenomField));
        panel.add(createLabeledField("Email", mailField));
        panel.add(createLabeledField("Mot de passe", motDePasseField));
        panel.add(Box.createVerticalStrut(15));
        panel.add(submitButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void ouvrirConnexionPanel()
    {
        JFrame frame = new JFrame("Connexion");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Connexion");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        styleField(emailField);
        styleField(passwordField);

        JButton loginButton = new JButton("Se connecter");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setBackground(new Color(0, 113, 194));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();
                String motDePasse = new String(passwordField.getPassword()).trim();

                if (email.isEmpty() || motDePasse.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Booking", "root", "")) {
                    String sql = "SELECT * FROM utilisateurs WHERE email = ? AND motDePasse = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, email);
                    stmt.setString(2, motDePasse);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(frame, "Connexion réussie !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Email ou mot de passe incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Erreur de connexion à la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(createLabeledField("Email", emailField));
        panel.add(createLabeledField("Mot de passe", passwordField));
        panel.add(Box.createVerticalStrut(15));
        panel.add(loginButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private JPanel createLabeledField(String labelText, JComponent field) {
        JPanel fieldPanel = new JPanel(new BorderLayout(5, 5));
        fieldPanel.setBackground(Color.WHITE);
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label.setForeground(new Color(55, 55, 55));
        fieldPanel.add(label, BorderLayout.NORTH);
        fieldPanel.add(field, BorderLayout.CENTER);
        fieldPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        return fieldPanel;
    }

    private void styleField(JTextField field) {
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
    }
}*/
