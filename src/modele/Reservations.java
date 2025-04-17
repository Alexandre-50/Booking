package modele;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservations
{
    private int idReservation;
    private int idUtilisateur;
    private int idLogement;
    private LocalDate dateArrivee;
    private LocalDate dateDepart;
    private int nbAdultes;
    private int nbEnfants;
    private int nbChambres;
    private Integer idReduction;
    private double prixPaye;
    private LocalDateTime dateReservation;

    public Reservations(int id, int idUtilisateur, int idLogement, LocalDate dateArrivee, LocalDate dateDepart, int nbAdultes, int nbEnfants, int nbChambres, Integer idReduction, double prixPaye, LocalDateTime dateReservation)
    {
        this.idReservation = id;
        this.idUtilisateur = idUtilisateur;
        this.idLogement = idLogement;
        this.dateArrivee = dateArrivee;
        this.dateDepart = dateDepart;
        this.nbAdultes = nbAdultes;
        this.nbEnfants = nbEnfants;
        this.nbChambres = nbChambres;
        this.idReduction = idReduction;
        this.prixPaye = prixPaye;
        this.dateReservation = dateReservation;
    }

    public int getIdReservation()
    {
        return idReservation;
    }
    public void setIdReservation(int id)
    {
        this.idReservation = id;
    }

    public int getIdUtilisateur()
    {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur)
    {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdLogement()
    {
        return idLogement;
    }

    public void setIdLogement(int idLogement)
    {
        this.idLogement = idLogement;
    }

    public LocalDate getDateArrivee()
    {
        return dateArrivee;
    }

    public void setDateArrivee(LocalDate dateArrivee)
    {
        this.dateArrivee = dateArrivee;
    }

    public LocalDate getDateDepart()
    {
        return dateDepart;
    }

    public void setDateDepart(LocalDate dateDepart)
    {
        this.dateDepart = dateDepart;
    }

    public int getNbAdultes()
    {
        return nbAdultes;
    }

    public void setNbAdultes(int nbAdultes)
    {
        this.nbAdultes = nbAdultes;
    }

    public int getNbEnfants()
    {
        return nbEnfants;
    }

    public void setNbEnfants(int nbEnfants)
    {
        this.nbEnfants = nbEnfants;
    }

    public int getNbChambres()
    {
        return nbChambres;
    }

    public void setNbChambres(int nbChambres)
    {
        this.nbChambres = nbChambres;
    }

    public Integer getIdReduction()
    {
        return idReduction;
    }

    public void setIdReduction(Integer idReduction)
    {
        this.idReduction = idReduction;
    }

    public double getPrixPaye()
    {
        return prixPaye;
    }

    public void setPrixPaye(double prixPaye)
    {
        this.prixPaye = prixPaye;
    }

    public LocalDateTime getDateReservation()
    {
        return dateReservation;
    }

    public void setDateReservation(LocalDateTime dateReservation)
    {
        this.dateReservation = dateReservation;
    }
}