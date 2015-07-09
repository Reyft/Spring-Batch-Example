package batchHttp.core;

import java.util.Date;

/**
 * Created by remy on 06/07/15.
 */
public class LogHttp {
    private String domaine;
    private String ipClient;
    private Date date;
    private String requete;
    private String url;
    private int status;
    private int weight;
    private String ressource;

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getIpClient() {
        return ipClient;
    }

    public void setIpClient(String ipClient) {
        this.ipClient = ipClient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRequete() {
        return requete;
    }

    public void setRequete(String requete) {
        this.requete = requete;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getRessource() {
        return ressource;
    }

    public void setRessource(String ressource) {
        this.ressource = ressource;
    }

    @Override
    public String toString() {
        return "LogHttp{" +
                "domaine='" + domaine + '\'' +
                ", ipClient='" + ipClient + '\'' +
                ", date=" + date +
                ", requete='" + requete + '\'' +
                ", url='" + url + '\'' +
                ", status=" + status +
                ", weight=" + weight +
                ", ressource='" + ressource + '\'' +
                '}';
    }
}
