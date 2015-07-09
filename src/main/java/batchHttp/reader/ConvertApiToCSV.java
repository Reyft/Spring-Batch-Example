package batchHttp.reader;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by remy on 06/07/15.
 */
public class ConvertApiToCSV {

    private void lireFichier(String chemin, BufferedWriter bw) throws IOException{
        //lecture du fichier texte
        InputStream ips = new FileInputStream(chemin);
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader br = new BufferedReader(ipsr);
        String ligne;
        while ((ligne = br.readLine())!=null){
           ecrire(bw, essaieExpr(ligne));
        }
        br.close();
        ips.close();
    }

    private void ecrire(BufferedWriter br, String texte) throws IOException{
        //on va chercher le chemin et le nom du fichier et on me tout ca dans un String
        br.write(texte); //on peut utiliser plusieurs fois methode write
    }

    private String essaieExpr(String chaine) {
        String gui = "";
        String save = "";
        String[] split;
        String[] req = {""};
        String res = "";

        String cond1 = "(api.optionway.com)";
        String cond2 = "(\"OPTIONS)";
        String cond3 = "\"[^\"]*\""; // Chaine de caratères entre guillemets
        Matcher m1 = Pattern.compile(cond1).matcher(chaine);
        if (m1.find()) {
            Matcher m2 = Pattern.compile(cond2).matcher(chaine);
            if (!m2.find()) {
                Matcher m3 = Pattern.compile(cond3).matcher(chaine);
                if (m3.find()) {
                    gui = m3.group();
                    save = chaine.replace(gui, "");
                    gui = gui.replaceAll(",", ":");
                    req = gui.split(" ");
                    gui = "";
                    for (int i = 1; i<req.length-1; i++){
                        gui += req[i]+" ";
                    }
                    gui+=req[req.length-1];
                }
            }
        }
        split = save.split(" ");
        if (split.length >= 7) {
            String date = split[4].replaceAll("/", "-").replaceAll("\\[", "");
            int sup = date.indexOf(":");
            if (sup >= 0) {
                date = date.substring(0, sup) + " " + date.substring(sup + 1);
            }
            if (split[8].equals("-")) {
                split[8] = "0";
            }
            res = split[0] + "," + split[1] + "," + date + " " + split[5].replaceAll("]", "") + ","+ req[0].replace("\"","") + "," + gui.replace("\"","") + "," + split[7] + "," + split[8]+"\n";
        }
        return res;
    }

    public static void run(){
        try {
            FileWriter fw = new FileWriter(new File("target/classes/apiHttp.csv"));
            BufferedWriter output = new BufferedWriter(fw);
            ConvertApiToCSV api = new ConvertApiToCSV();
            api.lireFichier("logExample.txt", output);
            output.flush();
            output.close();
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