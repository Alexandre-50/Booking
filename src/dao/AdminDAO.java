package dao;

import modele.Administrateur;



public interface AdminDAO {
    void ajouter(Administrateur administrateur);

    Administrateur connecter(String email, String motDePasse);
    boolean estAdministrateur(String email);
}
