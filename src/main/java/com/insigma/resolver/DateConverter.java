package com.insigma.resolver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 时间格式java.util.date与String格式转换器
 * @author wengsh
 *
 */
public class DateConverter implements Converter<String, Date> {
	@Override
	public Date convert(String source) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		try {
			return dateFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
