package com.insigma.mvc.service.log;

import com.insigma.mvc.model.SLog;
import com.insigma.resolver.AppException;


/**
 * 日志服务
 * @author wengsh
 *
 */
public interface LogService  {
	
	public String saveLogInfo(SLog slog);

}
