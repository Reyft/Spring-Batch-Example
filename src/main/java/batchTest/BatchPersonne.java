package batchTest;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by remy on 02/07/15.
 */
public class BatchPersonne {

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext cpt = new ClassPathXmlApplicationContext("batchpeopleContext.xml");
        cpt.start();

        JobLauncher jobLauncher = (JobLauncher) cpt.getBean("jobLauncher");
        Job job = (Job) cpt.getBean("importPersonnes");
        JobParameters parameter = new JobParametersBuilder().addDate("date", new Date())
                .addString("input.file", "./resources/Person.csv").toJobParameters();
        jobLauncher.run(job, parameter);
    }
}