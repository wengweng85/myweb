package com.insigma.common.util;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


/**
 * sql语句拼装工具类
 * @author wengsh
 *
 */
public class SQLAssemblyUtil {
	/** 
     * 增加记录 
     * @param tableName 
     * @param values 
     * @return byte[] sql语句
     * @throws SQLException  
     */  
    public static byte[] assemblyInsertSql(String tableName, HashMap<String, String> values) throws SQLException {  
            StringBuilder sql = new StringBuilder();  
            StringBuilder column = new StringBuilder();  
            StringBuilder data = new StringBuilder();  
            if ((values != null) && !values.isEmpty()) {  
                Set<String> input = values.keySet();  
                Iterator<String> it = input.iterator();  
                for (; it.hasNext();) {  
                    String s = it.next();  
                    column.append(s);  
                    String value=values.get(s);
                    if(value.toLowerCase().startsWith("sysdate")||value.toLowerCase().startsWith("seq")||value.toLowerCase().startsWith("sq")){
                    	data.append(value); 
                    }else{
                    	data.append("'");  
                    	data.append(value); 
                        data.append("'");
                    }
                    if (it.hasNext()) {  
                        column.append(",");  
                        data.append(",");  
                    }  
                }  
            } else {  
                throw new SQLException("输入的值不能为空");  
            }  
            sql.append("INSERT INTO ");  
            sql.append(tableName);  
            sql.append(" (");  
            sql.append(column);  
            sql.append(") VALUES ( ");  
            sql.append(data);  
            sql.append(")");  
            System.out.println(sql);
            return sql.toString().getBytes();
        }

}
