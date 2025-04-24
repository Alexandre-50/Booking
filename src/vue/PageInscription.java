package vue;

import controleur.UtilisateurControleur;

import javax.swing.*;
import java.awt.*;

public class PageInscription extends JFrame
{
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField emailField;
    private JPasswordField motDePasseField;
    private UtilisateurControleur controleur;

    public PageInscription(UtilisateurControleur controleur)
    {
        this.controleur = controleur;

        setTitle("Créer un compte");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Inscription");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        nomField = new JTextField();
        prenomField = new JTextField();
        emailField = new JTextField();
        motDePasseField = new JPasswordField();

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(creerChamp("Nom", nomField));
        panel.add(creerChamp("Prénom", prenomField));
        panel.add(creerChamp("Email", emailField));
        panel.add(creerChamp("Mot de passe", motDePasseField));

        JButton inscrireBouton = new JButton("Créer le compte");
        inscrireBouton.setAlignmentX(Component.CENTER_ALIGNMENT);
        inscrireBouton.setBackground(new Color(0, 120, 215));
        inscrireBouton.setForeground(Color.WHITE);
        inscrireBouton.setFocusPainted(false);
        inscrireBouton.addActionListener(e -> creerCompte());

        panel.add(Box.createVerticalStrut(15));
        panel.add(inscrireBouton);

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

    private void creerCompte()
    {
        String nom = nomField.getText().trim();
        String prenom = prenomField.getText().trim();
        String email = emailField.getText().trim();
        String mdp = new String(motDePasseField.getPassword()).trim();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || mdp.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        controleur.inscrire(nom, prenom, email, mdp);
        JOptionPane.showMessageDialog(this, "Inscription réussie !");
        dispose();
    }
}
