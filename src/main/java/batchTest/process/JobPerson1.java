package batchTest.process;

import batchTest.core.Personne;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by remy on 02/07/15.
 */
public class JobPerson1 implements ItemProcessor<Personne, Personne> {

    public Personne process(final Personne personneInput) throws Exception {

        Personne personneOutput = null;

        if ("M".equals(personneInput.getCivilite())) {
            personneOutput = new Personne();
            personneOutput.setCivilite(personneInput.getCivilite());
            personneOutput.setId(personneInput.getId());
            personneOutput.setNom(personneInput.getNom());
            personneOutput.setPrenom(personneInput.getPrenom());
        }
        return personneOutput;
    }
}