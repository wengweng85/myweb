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
public class Request implements java.io.Serializable {
	//请求头
	private Head head;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}
	
}
