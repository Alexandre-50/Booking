package modele;

import java.sql.Date;

public class Reduction {
    private int idReduction;
    private String description;
    private double pourcentage;
    private Date dateDebut;
    private Date dateFin;

    public Reduction() {}

    public Reduction(int idReduction, String description, double pourcentage, Date dateDebut, Date dateFin) {
        this.idReduction = idReduction;
        this.description = description;
        this.pourcentage = pourcentage;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public int getIdReduction() {
        return idReduction;
    }

    public void setIdReduction(int idReduction) {
        this.idReduction = idReduction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
}
