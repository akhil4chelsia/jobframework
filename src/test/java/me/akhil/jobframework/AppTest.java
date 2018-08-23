package me.akhil.jobframework;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

	@Autowired 
	JobUtil jobutil;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testJob() {
		Job hello = jobutil.createJob(HelloWorldJob.class);
		Assert.assertEquals(JobStatus.PENDING, hello.getJobStatus());
		String jobId = jobutil.submitJob(hello);
		while(jobutil.getJobStatus(jobId)!=JobStatus.COMPLETED){
			
		}
		
		Assert.assertEquals(JobStatus.COMPLETED, jobutil.getJobStatus(jobId));
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