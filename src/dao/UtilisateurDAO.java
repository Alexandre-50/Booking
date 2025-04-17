package dao;

import modele.Utilisateur;

public interface UtilisateurDAO
{
    void ajouter(Utilisateur utilisateur);
    Utilisateur connecter(String email, String motDePasse);
}