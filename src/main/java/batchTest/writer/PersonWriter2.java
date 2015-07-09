package batchTest.writer;

import batchTest.core.Personne;
import org.springframework.batch.item.ItemWriter;
import java.util.List;

/**
 * Created by remy on 02/07/15.
 */
public class PersonWriter2 implements ItemWriter<Personne> {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PINK = "\u001B[35m";

    public void write(List<? extends Personne> items) throws Exception {
        for(Personne p : items){
            System.out.println(ANSI_PINK + p + ANSI_RESET);
        }
    }
}
