package modele;

import java.time.LocalDate;

public class Disponibilites
{
    private int idDisponibilite;
    private int idLogement;
    private LocalDate dateDisponible;
    private boolean estDisponible;

    public Disponibilites(int id, int idLogement, LocalDate dateDisponible, boolean estDisponible)
    {
        this.idDisponibilite = id;
        this.idLogement = idLogement;
        this.dateDisponible = dateDisponible;
        this.estDisponible = estDisponible;
    }

    public int getId()
    {
        return idDisponibilite;
    }

    public void setId(int id)
    {
        this.idDisponibilite = id;
    }

    public int getIdLogement()
    {
        return idLogement;
    }

    public void setIdLogement(int idLogement)
    {
        this.idLogement = idLogement;
    }

    public LocalDate getDateDisponible()
    {
        return dateDisponible;
    }

    public void setDateDisponible(LocalDate dateDisponible)
    {
        this.dateDisponible = dateDisponible;
    }

    public boolean isEstDisponible()
    {
        return estDisponible;
    }
    public void setEstDisponible(boolean estDisponible)
    {
        this.estDisponible = estDisponible;
    }
}
