package me.akhil.jobframework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestData {

	@Autowired
	private JobUtil jobutil;

	public void testJob() {
		for (int i = 0; i < 10; i++) {
			Job hello = jobutil.createJob(TestJob.class);
			String jobId = jobutil.submitJob(hello);			
		}
	}
}
@Component
class TestJob extends Job{

	@Override
	public void process() throws Exception {
		System.out.println("processing TestJob ...");
	}

	@Override
	public void cleanUp() {
		
	}
	
}