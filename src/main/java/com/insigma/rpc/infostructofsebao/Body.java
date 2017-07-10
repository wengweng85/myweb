package com.insigma.rpc.infostructofsebao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * xml转换类,用于社保查询接口
 * @author wengsh
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Body  implements java.io.Serializable {
	//返回信息体
	private Responsedata responsedata;

	public Responsedata getResponsedata() {
		return responsedata;
	}

	public void setResponsedata(Responsedata responsedata) {
		this.responsedata = responsedata;
	}
	
	
}
