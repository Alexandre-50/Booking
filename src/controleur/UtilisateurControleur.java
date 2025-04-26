package controleur;

import dao.*;
import modele.Administrateur;
import modele.Utilisateur;

public class UtilisateurControleur
{
    private UtilisateurDAO utilisateurDAO;
    private AdminDAO adminDAO;
    public UtilisateurControleur(DaoFactory daoFactory) {
        this.utilisateurDAO = new UtilisateurDAOImpl(daoFactory);
        this.adminDAO = new AdminDAOImpl(daoFactory); // <-- À AJOUTER
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
    public Administrateur seConnecterAdmin(String email, String motDePasse)
    {
        return adminDAO.connecter(email, motDePasse);
    }
}
