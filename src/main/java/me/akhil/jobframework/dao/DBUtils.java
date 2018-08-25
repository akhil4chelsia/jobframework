package me.akhil.jobframework.dao;

import me.akhil.jobframework.Job;

public class DBUtils {

	public static JobDAO getJobDAO(Job job) {
		JobDAO dao = new JobDAO();
		dao.setJobId(job.getJobId());
		dao.setJobStatus(job.getJobStatus());
		dao.setJobParameters(job.getJobParameters());
		return dao;
	}
}
