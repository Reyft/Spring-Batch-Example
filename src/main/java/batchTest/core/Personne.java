package batchTest.core;

/**
 * Created by remy on 02/07/15.
 */
public class Personne {

    private int id;
    private String nom;
    private String prenom;
    private String civilite;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString(){
        return "["+this.getId()+": "+this.getCivilite()+", "+this.getNom()+" "+this.getPrenom()+"].";
    }
}
