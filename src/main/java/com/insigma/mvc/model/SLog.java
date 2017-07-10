package com.insigma.mvc.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="s_log")
public class SLog {
	
	@Id
	@GenericGenerator(name = "tg_s_log", strategy = "uuid")
    @GeneratedValue(strategy=GenerationType.TABLE,generator="tg_s_log")
    private String logid;

	@Temporal(TemporalType.DATE)
    private Date logtime;

    private String content;

    public String getLogid() {
        return logid;
    }

    public void setLogid(String logid) {
        this.logid = logid == null ? null : logid.trim();
    }

   
    

  

	public Date getLogtime() {
		return logtime;
	}

	public void setLogtime(Date logtime) {
		this.logtime = logtime;
	}

	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}