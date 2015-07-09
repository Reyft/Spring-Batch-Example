import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by remy on 03/07/15.
 */
public class LireEspace {
    private ArrayList list = new ArrayList<Integer>();

        public void lireFichier(String chemin){

            //lecture du fichier texte
            try{
                InputStream ips = new FileInputStream(chemin);
                InputStreamReader ipsr = new InputStreamReader(ips);
                BufferedReader br = new BufferedReader(ipsr);
                String ligne;
                while ((ligne=br.readLine())!=null){
                    int espace = this.regexOccur(ligne, " ");
                    if (!list.contains(espace)){
                        list.add(espace);
                    }
                }
                br.close();
            }
            catch (Exception e) {
                System.out.println(e.toString());
            }
        }

    public ArrayList getList() {
        Collections.sort(list);
        return list;
    }

    public int regexOccur(String text, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(text);
        int occur = 0;
        while(matcher.find()) {
            occur ++;
        }
        return occur;
    }

    public static void main(String [] args){
        LireEspace lf = new LireEspace();
        lf.lireFichier("loghttp.txt");
        System.out.println("Différence de nombre d'espace : "+lf.getList());
    }
}