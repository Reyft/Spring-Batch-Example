package batchHttp.reader;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by remy on 06/07/15.
 */
public class ConvertToCSV{
    private void lireFichier(BufferedWriter bw, String chemin) throws IOException{
        //lecture du fichier texte
        InputStream ips = new FileInputStream(chemin);
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        String ligne;
        while ((ligne = br.readLine())!=null){
           ecrire(bw ,essaieExpr(ligne));
        }
    }

    private void ecrire(BufferedWriter output, String texte) throws IOException{
        output.write(texte); //on peut utiliser plusieurs fois methode write
    }

    private String essaieExpr(String chaine) {
        ArrayList<String> gui = new ArrayList<String>();
        String res = "";
        String[] req = {" okpo"};
        String rep = "";
        String cond1 = "(www.optionway.com)";
        String cond3 = "\"[^\"]*\""; // Chaine entre " "
        Matcher m1 = Pattern.compile(cond1).matcher(chaine);
        if (m1.find()) {
            Matcher m2 = Pattern.compile(cond3).matcher(chaine);
            String save;
            rep = chaine;
            int j = 0;
            while (m2.find() && j < 3) {
                save = m2.group();
                if(save.length() > 1){
                    gui.add(save);
                    j++;
                }
                rep = rep.replace(save, "");
            }
            req = gui.get(0).split(" ");
            gui.set(0,"");
            for (int i = 1; i<req.length-1; i++){
                gui.set(0, gui.get(0)+req[i]+" ");
            }
            gui.set(0,gui.get(0)+req[req.length-1]);
        }
        String[] rest = rep.split(" ");
        if (rest.length >= 7){
            String date = rest[4].replaceAll("/", "-").replaceAll("\\[","");
            int sup = date.indexOf(":");
            if (sup >= 0){
                date = date.substring(0,sup)+" "+date.substring(sup +1);
            }
            if(rest[8].equals("-")){
                rest[8] = "0";
            }
            res = rest[0]+","+rest[1]+","+date+" "+rest[5].replaceAll("]","")+","+req[0].replace("\"","")+","+gui.get(0).replace("\"","")+","+rest[7]+","+rest[8]+","+gui.get(2).replaceAll("\"", "").replaceAll(",",":")+"\n";
        }
        return res;
    }

    public static void run(){
        try {
            FileWriter fw = new FileWriter(new File("target/classes/logHttp.csv"));
            BufferedWriter bw = new BufferedWriter(fw);
            ConvertToCSV ctcsv = new ConvertToCSV();
            ctcsv.lireFichier(bw, "loghttp.txt");
            bw.flush();
            bw.close();
            fw.close();
            System.out.println("******************************************\n" +
                    "***           File Generated           ***\n" +
                    "******************************************");
        } catch (IOException e){
            System.out.println("******************************************\n"
                    +e.toString()+
                    "\n******************************************");
        }
    }
}
