package batchHttp;

import batchHttp.reader.ConvertApiToCSV;
import batchHttp.reader.ConvertToCSV;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by remy on 03/07/15.
 */
public class BatchLogHttp {
    public static void main(String[] args) throws Exception {
        System.out.print("\n");
        ConvertApiToCSV.run();
        System.out.print("\n");
        ConvertToCSV.run();
        System.out.print("\n");

        ClassPathXmlApplicationContext cpt = new ClassPathXmlApplicationContext("logHttpContext.xml");
        cpt.start();

        JobLauncher jobLauncher = (JobLauncher) cpt.getBean("jobLauncher");
        Job job = (Job) cpt.getBean("jobHttp");
        JobParameters parameter = new JobParametersBuilder().addDate("date", new Date()).toJobParameters();
        jobLauncher.run(job, parameter);
    }
}
