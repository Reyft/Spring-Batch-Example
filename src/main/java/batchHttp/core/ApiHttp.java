package batchHttp.core;

import java.util.Date;

/**
 * Created by remy on 07/07/15.
 */
public class ApiHttp {
    private String domain;
    private String ipClient;
    private Date date;
    private String requete;
    private String url;
    private int status;
    private int weigth;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRequete(String req) {
        this.requete = req;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getWeigth() {
        return weigth;
    }

    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }

    @Override
    public String toString() {
        return "ApiHttp{" +
                "domain='" + domain + '\'' +
                ", ipClient='" + ipClient + '\'' +
                ", date=" + date +
                ", requete='" + requete + '\'' +
                ", url='" + url + '\'' +
                ", status=" + status +
                ", weigth=" + weigth +
                '}';
    }
}
