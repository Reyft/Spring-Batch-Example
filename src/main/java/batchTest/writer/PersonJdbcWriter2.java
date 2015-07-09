package batchTest.writer;

import batchTest.core.Personne;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by remy on 03/07/15.
 */
@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
public class PersonJdbcWriter2 implements ItemWriter<Personne> {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    private static final String REQUEST_INSERT_PERSONNE = "insert into madame (nom,prenom,privateId,createdDate, updatedDate) values (?,?,?,?,?)";
    private static final String REQUEST_UPDATE_PERSONNE = "update madame set nom=?, prenom=?, occurence=?, updatedDate=? where privateId=?";
    private static final String REQUEST_OCCURENCE = "select occurence from madame where privateId=?";

    public void write(List<? extends Personne> items) throws Exception {
        for (Personne personne : items){

            // Update person if he already exist
            try{
                int occurence = jdbcTemplate.queryForObject(REQUEST_OCCURENCE, new Object[] {personne.getId()}, Integer.class);
                final Object objectUpdate [] = {personne.getNom(), personne.getPrenom(), (occurence+1), new Date(), personne.getId()};
                int nbLigne = jdbcTemplate.update(REQUEST_UPDATE_PERSONNE, objectUpdate);
            }
            // Else create a person
            catch(org.springframework.dao.EmptyResultDataAccessException e){
                Date d = new Date();
                final Object objectInsert [] = {personne.getNom(),personne.getPrenom(), personne.getId(), d, d};
                jdbcTemplate.update(REQUEST_INSERT_PERSONNE, objectInsert);
            }
        }
    }
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}