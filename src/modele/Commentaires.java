package modele;

import java.time.LocalDateTime;

public class Commentaires
{
    private int idCommentaire;
    private int idUtilisateur;
    private int idSite;
    private int note;
    private String commentaire;
    private LocalDateTime dateCommentaire;

    public Commentaires(int id, int idUtilisateur, int idSite, int note, String commentaire, LocalDateTime dateCommentaire) {
        this.idCommentaire = id;
        this.idUtilisateur = idUtilisateur;
        this.idSite = idSite;
        this.note = note;
        this.commentaire = commentaire;
        this.dateCommentaire = dateCommentaire;
    }

    public int getIdCommentaire()
    {
        return idCommentaire;
    }
    public void setIdCommentaire(int id) { this.idCommentaire = id; }
    public int getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(int idUtilisateur) { this.idUtilisateur = idUtilisateur; }
    public int getIdSite() { return idSite; }
    public void setIdSite(int idSite) { this.idSite = idSite; }
    public int getNote() { return note; }
    public void setNote(int note) { this.note = note; }
    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }
    public LocalDateTime getDateCommentaire() { return dateCommentaire; }
    public void setDateCommentaire(LocalDateTime dateCommentaire) { this.dateCommentaire = dateCommentaire; }
}
