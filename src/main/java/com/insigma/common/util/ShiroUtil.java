package com.insigma.common.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Test;

public class ShiroUtil {
    @Test
    public void test(){
        String str1 = "hello";
        String salt = "123";
        String md5Str1 = new Md5Hash(str1, salt).toString();
        System.out.println("MD5ֵ1��" + md5Str1);
        //�����԰�MD5���ܺ��ֵ��ת��Base64��16���Ƶı�����ʽ
        String md5Str2 = new Md5Hash(str1, salt).toBase64();
        String md5Str3 = new Md5Hash(str1, salt).toHex();
        System.out.println("MD5ֵ2��" + md5Str2);
        System.out.println("MD5ֵ3��" + md5Str3);
        //������ָ�����ܴ�������ɢ�м���3��
        String md5Str4 = new Md5Hash(str1, salt, 3).toString();
        System.out.println("MD5ֵ4��" + md5Str4);
        
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        //ʹ��SHA256�����㷨
        String shaStr1 = new Sha256Hash(str1,salt).toString();
        String shaStr2 = new Sha256Hash(str1, salt).toBase64();
        String shaStr3 = new Sha256Hash(str1, salt).toHex();
        String shaStr4 = new Sha256Hash(str1, salt,3).toString();
        System.out.println("SHA256ֵ1��" + shaStr1);
        System.out.println("SHA256ֵ2��" + shaStr2);
        System.out.println("SHA256ֵ3��" + shaStr3);
        System.out.println("SHA256ֵ4��" + shaStr4);
    }
}
