package com.insigma.rpc.xml;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;
/**
 * 
 * @author qiand
 * 通过注解转换Data 和 DataTime
 * 
 */
public class JaxbDateAdapter extends XmlAdapter<String, Date> {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	
	@Override
	public String marshal(Date date) throws Exception {
		return sdf.format(date);
	}

	@Override
	public Date unmarshal(String dateStr) throws Exception {
		return sdf.parse(dateStr);
	}

}
