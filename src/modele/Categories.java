package modele;

public class Categories
{
    private int idCategorie;
    private String nomCategorie;

    public Categories(int id,String nom)
    {
        this.idCategorie = id;
        this.nomCategorie = nom;
    }

    public int getId()
    {
        return idCategorie;
    }

    public void setId(int id)
    {
        this.idCategorie = id;
    }

    public String getNom()
    {
        return nomCategorie;
    }

    public void setNom(String nom)
    {
        this.nomCategorie = nom;
    }
}
