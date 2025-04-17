package modele;

import java.sql.Date;

public class Utilisateur
{
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private boolean ancienClient;
    private Date dateInscription;

    public Utilisateur() {}

    public Utilisateur(String nom, String prenom, String email, String motDePasse, boolean ancienClient)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.ancienClient = ancienClient;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getMotDePasse()
    {
        return motDePasse;

    }
    public void setMotDePasse(String motDePasse)
    {
        this.motDePasse = motDePasse;
    }

    public boolean isAncienClient()
    {
        return ancienClient;
    }

    public void setAncienClient(boolean ancienClient)
    {
        this.ancienClient = ancienClient;
    }

    public Date getDateInscription()
    {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription)
    {
        this.dateInscription = dateInscription;
    }
}
