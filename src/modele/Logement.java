package modele;

public class Logement
{
    private int idLogement;
    private int idSite;
    private String description;
    private double prixParNuit;
    private boolean wifi;
    private boolean climatisation;
    private boolean fumeur;
    private boolean petitDejeuner;
    private boolean vueMer;
    private boolean minibar;

    public Logement(int id, int idSite, String description, double prixParNuit, boolean wifi, boolean climatisation, boolean fumeur, boolean petitDejeuner, boolean vueMer, boolean minibar)
    {
        this.idLogement = id;
        this.idSite = idSite;
        this.description = description;
        this.prixParNuit = prixParNuit;
        this.wifi = wifi;
        this.climatisation = climatisation;
        this.fumeur = fumeur;
        this.petitDejeuner = petitDejeuner;
        this.vueMer = vueMer;
        this.minibar = minibar;
    }

    public int getIdLogement()
    {
        return idLogement;
    }

    public void setIdLogement(int id)
    {
        this.idLogement = id;
    }

    public int getIdSite()
    {
        return idSite;
    }

    public void setIdSite(int idSite)
    {
        this.idSite = idSite;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public double getPrixParNuit()
    {
        return prixParNuit;
    }

    public void setPrixParNuit(double prixParNuit)
    {
        this.prixParNuit = prixParNuit;
    }

    public boolean isWifi()
    {
        return wifi;
    }

    public void setWifi(boolean wifi)
    {
        this.wifi = wifi;
    }

    public boolean isClimatisation()
    {
        return climatisation;
    }

    public void setClimatisation(boolean climatisation)
    {
        this.climatisation = climatisation;
    }

    public boolean estFumeur()
    {
        return fumeur;
    }

    public void setFumeur(boolean fumeur)
    {
        this.fumeur = fumeur;
    }

    public boolean isPetitDejeuner()
    {
        return petitDejeuner;
    }

    public void setPetitDejeuner(boolean petitDejeuner)
    {
        this.petitDejeuner = petitDejeuner;
    }

    public boolean isVueMer()
    {
        return vueMer;
    }

    public void setVueMer(boolean vueMer)
    {
        this.vueMer = vueMer;
    }

    public boolean isMinibar()
    {
        return minibar;
    }

    public void setMinibar(boolean minibar)
    {
        this.minibar = minibar;
    }
}
