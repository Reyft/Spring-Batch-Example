package batchHttp.writter;

import batchHttp.core.LogHttp;
import com.hp.hpl.jena.rdf.model.*;
import org.springframework.batch.item.ItemWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by remy on 03/07/15.
 */
public class httpWrite implements ItemWriter<LogHttp> {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";

    public static final String prop = "http://optionway.com/";

    public static Model model = ModelFactory.createDefaultModel();
    public static final Property dom = model.createProperty(prop, "domaine");
    public static final Property ip = model.createProperty(prop, "ipClient");
    public static final Property date = model.createProperty(prop, "date");
    public static final Property req = model.createProperty(prop, "requete");
    public static final Property url = model.createProperty(prop, "url");
    public static final Property sta = model.createProperty(prop, "status");
    public static final Property wei = model.createProperty(prop, "byte");
    public static final Property reso = model.createProperty(prop, "ressource");
    public static int compteur = 0;

    public void write(List<? extends LogHttp> items) throws Exception {
        for(LogHttp p : items) {
            List<Statement> list = new ArrayList<Statement>();
            Resource res = model.createResource("http://www.optionway.com/log/"+compteur);
            list.add(model.createStatement(res, reso, model.createLiteral(p.getRessource())));
            list.add(model.createStatement(res, wei, model.createLiteral(p.getWeight() + "")));
            list.add(model.createStatement(res, sta, model.createLiteral(p.getStatus() + "")));
            list.add(model.createStatement(res, url, model.createLiteral(p.getUrl())));
            list.add(model.createStatement(res, req, model.createLiteral(p.getRequete())));
            list.add(model.createStatement(res, date, model.createLiteral(p.getDate().toString())));
            list.add(model.createStatement(res, ip, model.createLiteral(p.getIpClient())));
            list.add(model.createStatement(res, dom, model.createLiteral(p.getDomaine())));

            model.add(list);
            compteur++;
            //System.out.print(ANSI_CYAN + compteur + ANSI_RESET);
        }
        try {
            model.write(new FileOutputStream(new File("test.rdf")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
