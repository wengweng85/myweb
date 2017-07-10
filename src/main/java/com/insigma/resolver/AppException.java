package com.insigma.resolver;

/**
 * 自定义项目异常
 * @author wengsh
 * @date 2014-11-25
 *
 */
public class AppException extends Exception {  
	  
    private static final long serialVersionUID = 1L;  
  
    public AppException() {  
        // TODO Auto-generated constructor stub  
    }  
  
    public AppException(String message) {  
        super(message);  
        // TODO Auto-generated constructor stub  
    }  
  
    public AppException(Throwable cause) {  
        super(cause);  
        // TODO Auto-generated constructor stub  
    }  
  
    public AppException(String message, Throwable cause) {  
        super(message, cause);  
        // TODO Auto-generated constructor stub  
    }  
  
} 
