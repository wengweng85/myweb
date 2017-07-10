package com.insigma.common.freemarker;

import com.insigma.common.util.FileUtil;
import com.insigma.resolver.AppException;
import freemarker.template.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.io.*;
import java.util.Map;

/**
 * Created by wengsh on 2015-06-14.
 */
public class FreeMarkerUtil {

    Log log= LogFactory.getLog(FreeMarkerUtil.class);
    private Configuration freemarker_cfg = null;



    /**
     * 获取freemarker的配置. freemarker本身支持classpath,目录和从ServletContext获取.
     */
    protected Configuration getFreeMarkerCFG() {
        if (null == freemarker_cfg) {
            // Initialize the FreeMarker configuration;
            // - Create a configuration instance
            freemarker_cfg = new Configuration();
            // - FreeMarker支持多种模板装载方式,可以查看API文档,都很简单:路径,根据Servlet上下文,classpath等等
            //htmlskin是放在classpath下的一个目录
            freemarker_cfg.setClassForTemplateLoading(this.getClass(), "/ftl");
            freemarker_cfg.setDefaultEncoding("UTF-8");
            // 设置对象的包装器
            freemarker_cfg.setObjectWrapper(new DefaultObjectWrapper());
            // 设置异常处理器//这样的话就可以${a.b.c.d}即使没有属性也不会出错
            freemarker_cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        }

        return freemarker_cfg;
    }

    /**
     * 生成静态文件.
     *
     * @param sRootDir 从配置中取得要静态文件存放的根路径:需要改为自己的属性类调用
     * @param templateFileName 模板文件名,相对htmlskin路径,例如"/tpxw/view.ftl"
     * @param propMap 用于处理模板的属性Object映射
     * @param htmlfilename 旧的文件名,例如 "1.htm"
     * @param newhtmlFileName 新的文件名,例如 "1.htm"
     */
    @SuppressWarnings("rawtypes")
	public void geneHtmlFile(String sRootDir,String templateFileName,Map propMap,String htmlfilename, String newhtmlFileName )  throws AppException{
        try {
            Template t = getFreeMarkerCFG().getTemplate(templateFileName);
            t.setEncoding("UTF-8");
            //如果根路径存在,则递归创建子目录
            FileUtil.createFolder(sRootDir);
            //html文件
            File indexfile = new File(sRootDir + File.separator + htmlfilename);
            //如果已经存在index.html,则重新生成一份新的，如果生成过程中没有出现错误，则覆盖掉
            if(indexfile.exists()){
                //新的html文件
                File newindexfile = new File(sRootDir + File.separator + newhtmlFileName);
                Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newindexfile),"utf-8"));
                t.process(propMap, out);
                //如果新的文件成功生成，则将老的删除掉并将名字重命名为index.html后再删除之
                if(newindexfile.exists()){
                    //System.out.println("新的文件成功生成，则将老的删除掉并将名字重命名为index.html后再删除之");
                    FileUtil.copyFile(newindexfile,indexfile);
                }
            }
            //如果没有生成过,则新生成一个index.html
            else{
                Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(indexfile),"utf-8"));
                t.process(propMap, out);
            }
        } catch (TemplateException e) {
            log.error("Error while processing FreeMarker template " + templateFileName,e);
            throw new AppException(e);
        } catch (IOException e) {
            log.error("Error while generate Static Html File " + newhtmlFileName,e);
            throw new AppException(e);
        } catch (Exception e){
            log.error("Error while generate Static Html File " ,e);
            throw new AppException(e);
        }
    }



    /**
     * 创建多级目录
     *
     * @param aParentDir String
     * @param aSubDir  以 / 开头
     * @return boolean 是否成功
     */
    public static boolean creatDirs(String aParentDir, String aSubDir) {
        File aFile = new File(aParentDir);
        if (aFile.exists()) {
            File aSubFile = new File(aParentDir + aSubDir);
            if (!aSubFile.exists()) {
                return aSubFile.mkdirs();
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }




}
