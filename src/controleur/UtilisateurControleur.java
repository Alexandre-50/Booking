package controleur;

import dao.UtilisateurDAO;
import modele.Utilisateur;

public class UtilisateurControleur
{
    private UtilisateurDAO utilisateurDAO;

    public UtilisateurControleur(UtilisateurDAO utilisateurDAO)
    {
        this.utilisateurDAO = utilisateurDAO;
    }

    public void inscrire(String nom, String prenom, String email, String motDePasse)
    {
        Utilisateur utilisateur = new Utilisateur(nom, prenom, email, motDePasse, false);
        utilisateurDAO.ajouter(utilisateur);
    }

    public Utilisateur seConnecter(String email, String motDePasse)
    {
        return utilisateurDAO.connecter(email, motDePasse);
    }
}
