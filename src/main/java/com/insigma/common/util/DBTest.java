package com.insigma.common.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2014-12-22.
 */
public class DBTest implements Runnable /*extends Thread*/ {
    public long date1=0;
    public static int count = 0;
    public static BasicDataSource basicDataSource=null;
    public static ComboPooledDataSource comboPooledDataSource=null;
    public static void main(String[] args) throws Exception {
        //basicDataSource=DataSourceUtil.dbcpinit();
        //comboPooledDataSource=DataSourceUtil.c3p0init();
        DBTest test = new DBTest();
        test.startup();
    }

    public void startup() {
        for (int i = 0; i <5; i++) {
            Thread thread = new Thread(this);
            thread.start();
        }
    }

    public void run() {
        if(date1==0)
        {
            date1 = System.currentTimeMillis();
            System.out.println(date1);
        }
        for(int i=0 ; i<10 ; i++){
            try {
                Connection conn = basicDataSource.getConnection();//运行完毕!耗时为：5703ms
                //Connection conn = comboPooledDataSource.getConnection();//运行完毕!耗时为：3459ms
                if (conn != null) {
                    Statement statement = conn.createStatement();
                    ResultSet rs = statement.executeQuery("select * from aa26 ");
                    while (rs.next()) {
                        String username = rs.getString(2);
                        System.out.println(username);
                    }
                    rs.close();
                    statement.close();
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        count++;
        if(count==5)
        {
            long date2 = System.currentTimeMillis();
            System.out.println(date2);
            System.out.println("运行完毕!耗时为：" + (date2 - date1) + "ms");
        }
    }
}