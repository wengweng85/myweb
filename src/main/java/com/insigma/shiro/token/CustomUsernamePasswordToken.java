package com.insigma.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created on 2014/11/11.
 */
public class CustomUsernamePasswordToken extends UsernamePasswordToken {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6730381322353623113L;
	
	//用于存储用户输入的校验码
    private String verifycode;
    
    private String isvercode;



    public CustomUsernamePasswordToken(String loginname, char[] password,boolean rememberMe, String host, String verifycode,String isvercode) {
        //调用父类的构造函数
        super(loginname,password,rememberMe,host);
        this.verifycode=verifycode;
        this.isvercode=isvercode;
    }

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

	public String getIsvercode() {
		return isvercode;
	}

	public void setIsvercode(String isvercode) {
		this.isvercode = isvercode;
	}
}
