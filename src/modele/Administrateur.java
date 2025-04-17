package modele;

public class Administrateur
{
    private int idAdmin;
    private String nomAdmin;
    private String prenomAdmin;
    private String emailAdmin;
    private String mdpAdmin;

    public Administrateur(int idAdmin, String nomAdmin, String prenomAdmin, String emailAdmin, String mdpAdmin)
    {
        this.idAdmin = idAdmin;
        this.nomAdmin = nomAdmin;
        this.prenomAdmin = prenomAdmin;
        this.emailAdmin = emailAdmin;
        this.mdpAdmin = mdpAdmin;
    }

    public int getId()
    {
        return idAdmin;
    }

    public void setId(int id)
    {
        this.idAdmin = id;
    }

    public String getNom()
    {
        return nomAdmin;
    }

    public void setNom(String nom)
    {
        this.nomAdmin = nom;
    }

    public String getPrenom()
    {
        return prenomAdmin;
    }

    public void setPrenom(String prenom)
    {
        this.prenomAdmin = prenom;
    }

    public String getEmail()
    {
        return emailAdmin;
    }

    public void setEmail(String email)
    {
        this.emailAdmin = email;
    }

    public String getMotDePasse()
    {
        return mdpAdmin;
    }

    public void setMotDePasse(String motDePasse)
    {
        this.mdpAdmin = motDePasse;
    }
}
