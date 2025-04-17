package vue;

import controleur.UtilisateurControleur;
import modele.Utilisateur;

import javax.swing.*;
import java.awt.*;

public class vueUtilisateur extends JFrame {
    private UtilisateurControleur controler;

    public vueUtilisateur(UtilisateurControleur controller) {
        this.controler = controller;
        setTitle("Booking - Connexion / Inscription Client");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        try{
            ImageIcon icon = new ImageIcon("src/images/logo.png");
            Image img = icon.getImage().getScaledInstance(250, 100, Image.SCALE_SMOOTH);
            JLabel logo = new JLabel(new ImageIcon(img), JLabel.CENTER);
            add(logo, BorderLayout.NORTH);
        } catch (Exception e) {
            System.out.println("Image logo non trouvée !");
        }

        JPanel panelBoutons = new JPanel();
        panelBoutons.setLayout(new BoxLayout(panelBoutons, BoxLayout.Y_AXIS));
        panelBoutons.setBorder(BorderFactory.createEmptyBorder(20, 80, 20, 80));

        JButton btnInscription = new JButton("S'inscrire");
        JButton btnConnexion = new JButton("Se connecter");

        btnInscription.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnConnexion.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnInscription.addActionListener(e -> afficherFormulaireInscription());
        btnConnexion.addActionListener(e -> afficherFormulaireConnexion());

        panelBoutons.add(btnInscription);
        panelBoutons.add(Box.createVerticalStrut(15));
        panelBoutons.add(btnConnexion);

        add(panelBoutons, BorderLayout.CENTER);

        setVisible(true);
    }

    private void afficherFormulaireInscription()
    {
        JTextField nom = new JTextField(15);
        JTextField prenom = new JTextField(15);
        JTextField email = new JTextField(15);
        JPasswordField motDePasse = new JPasswordField(15);

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.add(new JLabel("Nom :"));
        panel.add(nom);
        panel.add(new JLabel("Prénom :"));
        panel.add(prenom);
        panel.add(new JLabel("Email :"));
        panel.add(email);
        panel.add(new JLabel("Mot de passe :"));
        panel.add(motDePasse);

        int result = JOptionPane.showConfirmDialog(this, panel, "Inscription", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            controler.inscrire(nom.getText(), prenom.getText(), email.getText(), new String(motDePasse.getPassword()));
            JOptionPane.showMessageDialog(this, "Inscription réussie !");
        }
    }

    private void afficherFormulaireConnexion()
    {
        JTextField email = new JTextField(15);
        JPasswordField motDePasse = new JPasswordField(15);

        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel("Email :"));
        panel.add(email);
        panel.add(new JLabel("Mot de passe :"));
        panel.add(motDePasse);

        int result = JOptionPane.showConfirmDialog(this, panel, "Connexion", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            Utilisateur utilisateur = controler.seConnecter(email.getText(), new String(motDePasse.getPassword()));
            if (utilisateur != null) {
                JOptionPane.showMessageDialog(this, "Bienvenue " + utilisateur.getPrenom() + " !");
            } else {
                JOptionPane.showMessageDialog(this, "Identifiants incorrects.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}