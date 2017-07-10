package com.insigma.mvc.service.quartzjob;


import org.quartz.SchedulerException;

import com.github.pagehelper.PageInfo;
import com.insigma.mvc.model.QrtzTrigger;


/**
 * µÇÂ¼service½Ó¿Ú
 * @author wengsh
 *
 */
public interface QuartzJobService {
	
	public PageInfo<QrtzTrigger> queryJobList( QrtzTrigger qrtztrigger);
	public void addJob(QrtzTrigger qrtzTrigger) throws SchedulerException;
	public void deleteJob(String jobName) throws SchedulerException;
	public void pauseJob(String jobName) throws SchedulerException;
	public void resumeJob(String jobName) throws SchedulerException;

}
