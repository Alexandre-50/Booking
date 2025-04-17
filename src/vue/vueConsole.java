package vue;

import controleur.UtilisateurControleur;
import modele.Utilisateur;

import java.util.Scanner;

public class vueConsole
{
    private final UtilisateurControleur controleur;
    private final Scanner scanner = new Scanner(System.in);

    public vueConsole(UtilisateurControleur controleur)
    {
        this.controleur = controleur;
    }

    public void lancerMenu()
    {
        System.out.println("==== Menu Utilisateur ====");
        System.out.println("1. Inscription");
        System.out.println("2. Connexion");
        System.out.print("Choix : ");
        int choix = Integer.parseInt(scanner.nextLine());

        switch (choix)
        {
            case 1 :
            inscription();
            break;

            case 2 :
            connexion();
            break;

            default : System.out.println("Choix invalide.");
        }
    }

    private void inscription()
    {
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String mdp = scanner.nextLine();

        controleur.inscrire(nom, prenom, email, mdp);
        System.out.println("Inscription réussie !");
    }

    private void connexion()
    {
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String mdp = scanner.nextLine();

        Utilisateur utilisateur = controleur.seConnecter(email, mdp);
        if (utilisateur != null) {
            System.out.println("Bienvenue " + utilisateur.getPrenom() + " !");
        } else {
            System.out.println("Email ou mot de passe incorrect.");
        }
    }
}
