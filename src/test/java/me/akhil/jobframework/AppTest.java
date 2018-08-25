package me.akhil.jobframework;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import me.akhil.jobframework.dao.DBUtils;
import me.akhil.jobframework.dao.JobDAO;
import me.akhil.jobframework.dao.JobRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

	@Autowired 
	JobUtil jobutil;
	
	@Autowired
	private JobRepository repository;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testJob() {
		Job hello = jobutil.createJob(HelloWorldJob.class);
		Assert.assertEquals(JobStatus.PENDING, hello.getJobStatus());
		
		repository.save(DBUtils.getJobDAO(hello));
		
		for (JobDAO job : repository.findAll()) {
			System.out.println(job.toString());
		}
		
		String jobId = jobutil.submitJob(hello);
		while(jobutil.getJobStatus(jobId)!=JobStatus.COMPLETED){
			
		}		
		Assert.assertEquals(JobStatus.COMPLETED, jobutil.getJobStatus(jobId));
	}
	
	@Test
	public void testRepository() {
		JobDAO dao = new JobDAO();
		dao.setJobId(UUID.randomUUID().toString());
		dao.setJobStatus(JobStatus.PENDING);
		repository.save(dao);
		
		for (JobDAO job : repository.findAll()) {
			System.out.println(job.toString());
		}			
		
	}

}
@Component
class HelloWorldJob extends Job{

	@Override
	public void process() throws Exception {
		System.out.println("processing hello world job...");
	}

	@Override
	public void cleanUp() {
		System.out.println("clean up process started...");
	}
	
}