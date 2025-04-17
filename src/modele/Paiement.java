package modele;

import java.time.LocalDateTime;

public class Paiement
{
    private int idPaiement;
    private int idReservation;
    private double montantTotal;
    private double reductionAppliquee;
    private String methodePaiement;
    private String statutPaiement;
    private LocalDateTime datePaiement;

    public Paiement(int id, int idReservation, double montantTotal, double reductionAppliquee, String methodePaiement, String statutPaiement, LocalDateTime datePaiement)
    {
        this.idPaiement = id;
        this.idReservation = idReservation;
        this.montantTotal = montantTotal;
        this.reductionAppliquee = reductionAppliquee;
        this.methodePaiement = methodePaiement;
        this.statutPaiement = statutPaiement;
        this.datePaiement = datePaiement;
    }

    public int getIdPaiement()
    {
        return idPaiement;
    }

    public void setIdPaiement(int id)
    {
        this.idPaiement = id;
    }

    public int getIdReservation()
    {
        return idReservation;
    }

    public void setIdReservation(int idReservation)
    {
        this.idReservation = idReservation;
    }

    public double getMontantTotal()
    {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal)
    {
        this.montantTotal = montantTotal;
    }

    public double getReductionAppliquee()
    {
        return reductionAppliquee;
    }

    public void setReductionAppliquee(double reductionAppliquee)
    {
        this.reductionAppliquee = reductionAppliquee;
    }

    public String getMethodePaiement()
    {
        return methodePaiement;
    }

    public void setMethodePaiement(String methodePaiement)
    {
        this.methodePaiement = methodePaiement;
    }

    public String getStatutPaiement()
    {
        return statutPaiement;
    }

    public void setStatutPaiement(String statutPaiement)
    {
        this.statutPaiement = statutPaiement;
    }

    public LocalDateTime getDatePaiement()
    {
        return datePaiement;
    }

    public void setDatePaiement(LocalDateTime datePaiement)
    {
        this.datePaiement = datePaiement;
    }
}
