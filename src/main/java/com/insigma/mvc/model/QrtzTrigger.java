package com.insigma.mvc.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * QrtzTrigger
 * @author wengsh
 *
 */
public class QrtzTrigger  extends PageInfo implements java.io.Serializable {

	private String job_name;
	private String trigger_name;
	private Long next_fire_time;
	private Long pre_fire_time;
	private String trigger_state;
	private String trigger_type;
	private Long start_time;
	private Long end_time;
	
	@NotEmpty(message="{description.empty}")
	private String description;
	@NotEmpty(message="{job_class_name.empty}")
	private String job_class_name;
	@NotEmpty(message="{cron_expression.empty}")
	private String cron_expression;
	
	
	
	public Long getNext_fire_time() {
		return next_fire_time;
	}
	public void setNext_fire_time(Long next_fire_time) {
		this.next_fire_time = next_fire_time;
	}
	public Long getPre_fire_time() {
		return pre_fire_time;
	}
	public void setPre_fire_time(Long pre_fire_time) {
		this.pre_fire_time = pre_fire_time;
	}
	public Long getStart_time() {
		return start_time;
	}
	public void setStart_time(Long start_time) {
		this.start_time = start_time;
	}
	public Long getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Long end_time) {
		this.end_time = end_time;
	}
	
	public String getJob_class_name() {
		return job_class_name;
	}
	public void setJob_class_name(String job_class_name) {
		this.job_class_name = job_class_name;
	}
	
	public String getCron_expression() {
		return cron_expression;
	}
	public void setCron_expression(String cron_expression) {
		this.cron_expression = cron_expression;
	}
	public String getJob_name() {
		return job_name;
	}
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}
	public String getTrigger_name() {
		return trigger_name;
	}
	public void setTrigger_name(String trigger_name) {
		this.trigger_name = trigger_name;
	}
	
	public String getTrigger_state() {
		return trigger_state;
	}
	public void setTrigger_state(String trigger_state) {
		this.trigger_state = trigger_state;
	}
	public String getTrigger_type() {
		return trigger_type;
	}
	public void setTrigger_type(String trigger_type) {
		this.trigger_type = trigger_type;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}