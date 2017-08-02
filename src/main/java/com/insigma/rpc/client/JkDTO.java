package com.insigma.rpc.client;

import java.util.Date;

/**
 * 个人基本信息接口dto
 * @author wengsh
 *
 */
public class JkDTO {
	    private String bac010;//目标系统个人ID
	    private String aac002;//证件号码
	    private String aac003;//姓名
		private String aac001;//个人ID 
	    private Date  aac006 ;//出生日期
	    private String  aac004	;//性别
	    private String  aac005	;//民族	
		public String getBac010() {
			return bac010;
		}
		public void setBac010(String bac010) {
			this.bac010 = bac010;
		}
		public String getAac002() {
			return aac002;
		}
		public void setAac002(String aac002) {
			this.aac002 = aac002;
		}
		public String getAac003() {
			return aac003;
		}
		public void setAac003(String aac003) {
			this.aac003 = aac003;
		}
		public String getAac001() {
			return aac001;
		}
		public void setAac001(String aac001) {
			this.aac001 = aac001;
		}
		public Date getAac006() {
			return aac006;
		}
		public void setAac006(Date aac006) {
			this.aac006 = aac006;
		}
		public String getAac004() {
			return aac004;
		}
		public void setAac004(String aac004) {
			this.aac004 = aac004;
		}
		public String getAac005() {
			return aac005;
		}
		public void setAac005(String aac005) {
			this.aac005 = aac005;
		}
	 
		
		
	    
	    
	    
}
