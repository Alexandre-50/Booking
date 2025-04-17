package modele;

public class Site
{
    private int idSite;
    private String nomSite;
    private String descriptionSite;
    private int idCategorie;
    private String adresse;
    private String ville;
    private int nbEtoiles;
    private double prixParNuit;
    private String photo;
    private boolean petitDejeuner;
    private boolean parking;
    private boolean piscine;
    private boolean transportProche;

    public Site() {}

    public Site(int idSite, String nom, String description, int idCategorie, String adresse, String ville, int nbEtoiles, double prixParNuit, String photo, boolean petitDejeuner, boolean parking, boolean piscine, boolean transportProche)
    {
        this.idSite = idSite;
        this.nomSite = nom;
        this.descriptionSite = description;
        this.idCategorie = idCategorie;
        this.adresse = adresse;
        this.ville = ville;
        this.nbEtoiles = nbEtoiles;
        this.prixParNuit = prixParNuit;
        this.photo = photo;
        this.petitDejeuner = petitDejeuner;
        this.parking = parking;
        this.piscine = piscine;
        this.transportProche = transportProche;
    }
    
    public int getIdSite()
    {
        return idSite;
    }

    public void setIdSite(int idSite)
    {
        this.idSite = idSite;
    }

    public String getNom()
    {
        return nomSite;
    }

    public void setNom(String nom)
    {
        this.nomSite = nom;
    }

    public String getDescription()
    {
        return descriptionSite;
    }

    public void setDescription(String description)
    {
        this.descriptionSite = description;
    }

    public int getIdCategorie()
    {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie)
    {
        this.idCategorie = idCategorie;
    }

    public String getAdresse()
    {
        return adresse;
    }

    public void setAdresse(String adresse)
    {
        this.adresse = adresse;
    }

    public String getVille()
    {
        return ville;
    }

    public void setVille(String ville)
    {
        this.ville = ville;
    }

    public int getNbEtoiles()
    {
        return nbEtoiles;
    }

    public void setNbEtoiles(int nbEtoiles)
    {
        this.nbEtoiles = nbEtoiles;
    }

    public double getPrixParNuit()
    {
        return prixParNuit;
    }

    public void setPrixParNuit(double prixParNuit)
    {
        this.prixParNuit = prixParNuit;
    }

    public String getPhoto()
    {
        return photo;
    }

    public void setPhoto(String photo)
    {
        this.photo = photo;
    }

    public boolean isPetitDejeuner()
    {
        return petitDejeuner;
    }

    public void setPetitDejeuner(boolean petitDejeuner)
    {
        this.petitDejeuner = petitDejeuner;
    }

    public boolean isParking()
    {
        return parking;
    }

    public void setParking(boolean parking)
    {
        this.parking = parking;
    }

    public boolean isPiscine()
    {
        return piscine;
    }

    public void setPiscine(boolean piscine)
    {
        this.piscine = piscine;
    }

    public boolean isTransportProche()
    {
        return transportProche;
    }

    public void setTransportProche(boolean transportProche)
    {
        this.transportProche = transportProche;
    }
}

