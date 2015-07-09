import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by remy on 06/07/15.
 */
public class EcrireLigne {
    private int[] tab =  {10, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28};
    private ArrayList list = new ArrayList<Integer>();
    private String affichage = "";

    public void remplirList(){
        for (int i = 0; i<tab.length; i++){
            list.add(tab[i]);
        }
    }

    public String lireFichier(String chemin){
        // Creation liste
        remplirList();

        //lecture du fichier texte
        try{
            InputStream ips = new FileInputStream(chemin);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne=br.readLine())!=null){
                // Expression regulière
                essaieExpr(ligne);

                int espace = this.regexOccur(ligne, " ");
                if (list.contains(espace)){
                    affichage+=espace+": "+ligne+"\n";
                    list.remove(list.indexOf(espace));
                }
            }
            br.close();
            return affichage;
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return "il n'y a jamais ce nombre d'espace";
        }
    }

    public void getTab() {
        System.out.print("[");
        for (int i = 0; i < tab.length-1; i++){
            System.out.print(tab[i]+",");
        }
        System.out.println(tab[tab.length-1]+"]");
    }

    public ArrayList getList() {
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
        EcrireLigne el = new EcrireLigne();
        el.getTab();
        String res = el.lireFichier("oneLog.txt");
        //System.out.println(res);
        //System.out.println(el.getList());
    }

    public String essaieExpr(String chaine) {
        String cond1 = "(www.optionway.com)";
        String cond2 = "[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}"; //adresse Ip
        String cond3 = "\"[^\"]*\""; // Chaine entre ""
        String cond4 = "(- -) \\[.*] \"(GET|POST|PUT|DELETE) /[^\"]*\" [0-9]{3} ([0-9]+|-) \"-\" \"[^\"]*\"";
        Matcher m1 = Pattern.compile(cond1).matcher(chaine);
        if (m1.find()) {
            System.out.println(chaine);
            Matcher m2 = Pattern.compile(cond3).matcher(chaine);
            while (m2.find()) {
                chaine = chaine.replace(m2.group(), "");
                System.out.print(m2.group() + " ");
            }
        }
        return chaine;
    }

    public void ecrire(String nomFic, String texte) {
        //on va chercher le chemin et le nom du fichier et on me tout ca dans un String
        String adressedufichier = System.getProperty("user.dir") + "/"+ nomFic;
        try {
            FileWriter fw = new FileWriter(adressedufichier, true);
            BufferedWriter output = new BufferedWriter(fw);
            output.write(texte); //on peut utiliser plusieurs fois methode write
            output.flush();
            output.close();
        }
        catch(IOException ioe){
            System.out.print("Erreur : ");
            ioe.printStackTrace();
        }
    }
}