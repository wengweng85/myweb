package com.insigma.resolver;

/**
 * 自定义系统异常
 * @author wengsh
 * @date 2014-11-25
 *
 */
public class SystemException extends Exception {

    private static final long serialVersionUID = 1L;

    public SystemException() {
        // TODO Auto-generated constructor stub
    }

    public SystemException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public SystemException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);  
        // TODO Auto-generated constructor stub  
    }  
  
} 
