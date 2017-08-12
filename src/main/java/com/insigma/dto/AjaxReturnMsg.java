package com.insigma.dto;

/**
 * ajax信息返回
 * @author wengsh
 * @date 2012-9-27
 *
 */
public class AjaxReturnMsg<T> implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private boolean success;//是否成功
	private String message;//如果发生错误之错误信息,成功也可以有提示信息
	private T obj;//对象
	private Long total;//分页面对象之总数
	
	
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public T getObj() {
		return obj;
	}
	public void setObj(T obj) {
		this.obj = obj;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
