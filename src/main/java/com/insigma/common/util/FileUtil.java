package com.insigma.common.util;

import org.apache.commons.lang.StringUtils;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
Java IO 的一般使用原则 ： 

一、按数据来源（去向）分类：

1 、是文件： FileInputStream, FileOutputStream, ( 字节流 )FileReader, FileWriter( 字符 )

2 、是 byte[] ： ByteArrayInputStream, ByteArrayOutputStream( 字节流 )

3 、是 Char[]: CharArrayReader, CharArrayWriter( 字符流 )

4 、是 String: StringBufferInputStream, StringBufferOuputStream ( 字节流 )StringReader, StringWriter( 字符流 )

5 、网络数据流： InputStream, OutputStream,( 字节流 ) Reader, Writer( 字符流 )

二、按是否格式化输出分：

1 、要格式化输出： PrintStream, PrintWriter

三、按是否要缓冲分：

1 、要缓冲： BufferedInputStream, BufferedOutputStream,( 字节流 ) BufferedReader, BufferedWriter( 字符流 )

四、按数据格式分：

1 、二进制格式（只要不能确定是纯文本的） : InputStream, OutputStream 及其所有带 Stream 结束的子类

2 、纯文本格式（含纯英文与汉字或其他编码方式）； Reader, Writer 及其所有带 Reader, Writer 的子类

五、按输入输出分：

1 、输入： Reader, InputStream 类型的子类

2 、输出： Writer, OutputStream 类型的子类

六、特殊需要：

1 、从 Stream 到 Reader,Writer 的转换类： InputStreamReader, OutputStreamWriter

2 、对象输入输出： ObjectInputStream, ObjectOutputStream

3 、进程间通信： PipeInputStream, PipeOutputStream, PipeReader, PipeWriter

4 、合并输入： SequenceInputStream

5 、更特殊的需要： PushbackInputStream, PushbackReader, LineNumberInputStream, LineNumberReader

*/

/**
 * 文件处理类
 * @author kezp
 *
 */
public class FileUtil {
	/**
	 * 以B为单位
	 */
	private static String sign_B = "B";
	/**
	 * 以K为单位
	 */
	private static String sign_K = "K";
	
	/**
	 * 以M为单位
	 */
	private static String sign_M = "M";
	
	/**
	 * 以G为单位
	 */
	private static String sign_G = "G";
	
	private static int count=0;
	
	@SuppressWarnings("rawtypes")
	private static HashMap hm = new HashMap();
	/**
	 * 创建文件夹
	 * @param dir
	 * @throws Exception
	 */
	public static void createFolder(String dir){
		File file = new File(dir);
		if (!file.exists()) {  //如果不存在，则创建
			file.mkdirs();
		}
	}
	
	
	/**
	 * 创建文件，全新覆盖
	 * @param fullname
	 * @param fileContent
	 * @throws Exception
	 */
	public static void createFile(String fullname ,String fileContent) throws Exception {
		createFile(fullname ,fileContent,false);
	}
	

	/**
	 * 创建文件
	 * @param fullname
	 * @param fileContent
	 * @throws Exception
	 */
	public static void createFile(String fullname ,String fileContent,boolean append) throws Exception {
		File file = new File(fullname);
		if (!file.exists()){
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file,append);   
		fw.append(fileContent);
		fw.close();
	}
	
	
	/**
	 * 删除文件
	 * @param fullname
	 * @throws Exception
	 */
	public static void delFile(String fullname) throws Exception {
		File file = new File(fullname);
		file.delete();
	}
	/**
	 * 删除文件
	 * @param fullname
	 * @throws Exception
	 */
	public static void delFileOnExist(String fullname) throws Exception {
		File file = new File(fullname);
		if(file.exists()){
			file.delete();
		}
	}
	
	/**
	 * 文件重命名
	 * @param oldname
	 * @param newName
	 * @throws Exception
	 */
	public static void reNameFile(String oldname,String newName) throws Exception {
		File oldfile = new File(oldname);
		File newfile = new File(newName);
		oldfile.renameTo(newfile);
	}
	

	/**
	 * 文件拷贝
	 * @param sourceFile
	 * @param targetFile
	 * @throws Exception
	 */
	public static void copyFile(File sourceFile,File targetFile) throws Exception {
 
        FileInputStream input = new FileInputStream(sourceFile);  
        BufferedInputStream inBuff=new BufferedInputStream(input);  
  
        FileOutputStream output = new FileOutputStream(targetFile);  
        BufferedOutputStream outBuff=new BufferedOutputStream(output);  
		          
        //缓冲数组   
        byte[] b = new byte[1024 * 5];  
        int len;  
        while ((len =inBuff.read(b)) != -1) {  
            outBuff.write(b, 0, len);  
        }  
        // 刷新此缓冲的输出流   
        outBuff.flush();  
          
        //关闭流   
        inBuff.close();  
        outBuff.close();  
        output.close();  
        input.close();  
	}
	
	/**
	 * 文件拷贝
	 * @param sourceFile
	 * @param targetFile
	 * @throws Exception
	 */
	public static void copyFile(String sourceFile,String targetFile) throws Exception {
		copyFile(new File(sourceFile),new File(targetFile));
	}
	
	
     
	/**
	 * 整个文件夹拷贝
	 * @param sourceDir
	 * @param targetDir
	 * @throws Exception
	 */
    public static void copyDirectiory(String sourceDir, String targetDir)  throws Exception {  
        // 新建目标目录   
        (new File(targetDir)).mkdirs();  
        // 获取源文件夹当前下的文件或目录   
        File[] file = (new File(sourceDir)).listFiles();  
        for (int i = 0; i < file.length; i++) {  
            if (file[i].isFile()) {  
                // 源文件   
                File sourceFile=file[i];  
                // 目标文件   
                File targetFile=new File(new File(targetDir).getAbsolutePath()+File.separator+file[i].getName());  
                copyFile(sourceFile,targetFile);  
            }  
            if (file[i].isDirectory()) {  
                // 准备复制的源文件夹   
                String dir1=sourceDir + "/" + file[i].getName();  
                // 准备复制的目标文件夹   
                String dir2=targetDir + "/"+ file[i].getName();  
                copyDirectiory(dir1, dir2);  
            }  
        }  
    }  

    
    /**
     * 删除整个文件夹里的内容
     * @param path
     * @throws Exception
     */
	public static void delAllFile(String path) throws Exception{ 
		File file = new File(path); 
		if (!file.exists()) { 
			return; 
		} 
		if (!file.isDirectory()) { 
			return; 
		} 
		String[] tempList = file.list(); 
		File temp = null; 
		for (int i = 0; i < tempList.length; i++) { 
			if (path.endsWith(File.separator)) { 
				temp = new File(path + tempList[i]); 
			} else { 
				temp = new File(path + File.separator + tempList[i]); 
			} 
			if (temp.isFile()) { 
				temp.delete(); 
			} 
			if (temp.isDirectory()) { 
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件 
				File folderPath = new File(path + "/" + tempList[i]);  // 再删除空文件夹
				folderPath.delete(); 
			} 
		}
		file.delete();  //删除自身文件夹
	} 
	
	

	/**
	 * 处理路径问题，如果路径的首尾没有"/",则增加"/"
	 * @param path
	 * @return
	 */
	public static String resolvePath(String path) {
		path = StringUtils.replace(path, "\\", "/");
		int idxLastSlash = path.lastIndexOf("/");
		if (path.length() != (idxLastSlash + 1)) {
			path = path + "/";
		}
		return path;
	}
	
	
	/**
	 * 修改程序。<br>
	 * 内部递归调用，进行子目录的更名
	 * 
	 * @param path
	 *            路径
	 * @param from
	 *            原始的后缀名，包括那个(.点)
	 * @param to
	 *            改名的后缀，也包括那个(.点)
	 */
	public static void reName(String path, String from, String to) {
		File f = new File(path);
		File[] fs = f.listFiles();
		for (int i = 0; i < fs.length; ++i) {
			File f2 = fs[i];
			if (f2.isDirectory()) {
				reName(f2.getPath(), from, to);
			} else {
				String name = f2.getName();
				if (name.endsWith(from)) {
					f2.renameTo(new File(f2.getParent() + "/"
							+ name.substring(0, name.indexOf(from)) + to));
				}
			}
		}
	}

	
	/**
	 * 取文件大小
	 * @param fileName 文件名
	 * @param cal_Sign B K M G
	 * @return
	 */
	public static double getFileSize(String fileName,String cal_Sign){
		
		long sign = 1;
		if (cal_Sign.equals("K")){
			sign = sign*1024;
		} else if (cal_Sign.equals("M")){
			sign = sign*1024*1024;
		}else if (cal_Sign.equals("G")){
			sign = sign*1024*1024*1024;
		}
		File file = new File(fileName);
		
		BigDecimal bd1 = new BigDecimal(new Long(file.length()).toString());
		BigDecimal bd2 = new BigDecimal(new Long(sign).toString());
		return bd1.divide(bd2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 取文件大小
	 * @param fileName 文件名
	 * @param cal_Sign B K M G
	 * @return
	 */
	public static String getFileSize(long size){
		NumberFormat format=NumberFormat.getInstance();//数字格式化类
		format.setMaximumFractionDigits(2);//小数位最多2位
		if(size/1024<1){
			return format.format(size)+sign_B;
		}else if(size/(1024*1024)<1){
			return format.format(size/1024d)+sign_K;
		}else if(size/(1024*1024*1024)<1){
			return format.format(size/(1024d*1024d))+sign_M;
		}
		return format.format(size/(1024d*1024d*1024d))+sign_G;
	}
	
    /**
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     */
    @SuppressWarnings("unused")
	public static String readFileByChars(String fileName) {
        File file = new File(fileName);
        Reader reader = null;
/*        try {
            System.out.println("以字符为单位读取文件内容，一次读一个字节：");
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if (((char) tempchar) != '\r') {
                    System.out.print((char) tempchar);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        try {
            //System.out.println("以字符为单位读取文件内容，一次读多个字节：");
        	StringBuffer sb = new StringBuffer();
            // 一次读多个字符
            char[] tempchars = new char[30];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            // 读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread = reader.read(tempchars)) != -1) {
            	
                // 同样屏蔽掉\r不显示
                if ((charread == tempchars.length) && (tempchars[tempchars.length - 1] != '\r')) {
                	sb.append(tempchars);
                    //System.out.print(tempchars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                            //System.out.print(tempchars[i]);
                            sb.append(tempchars[i]);
                        }
                    }
                }
            }
            return sb.toString();
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            //System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            StringBuffer sb = new StringBuffer();
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString);
            }
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
    
    
    /**
     * 采用BufferedInputStream方式读取文件行数
     * 
     * @param filename
     * @return
     * @throws java.io.IOException
     */
    public static int count(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        byte[] c = new byte[1024];
        int count = 0;
        int readChars = 0;
        while ((readChars = is.read(c)) != -1) {
            for (int i = 0; i < readChars; ++i) {
                if (c[i] == '\n')
                    ++count;
            }
        }
        is.close();
        return count;
    }

    /***
     * 根据路径，返回文件列表
     * @param filePath		文件所在路径
     * @param startname	文件开始名
     * @param endname	文件结尾名
     * @return					文件列表
     * @throws Exception
     */
	@SuppressWarnings("rawtypes")
	public List getFileList(String filePath,String startname,String endname) throws Exception{
		File dir = new File(filePath);
		if (!(dir.isDirectory() && dir.exists())) {
			throw new Exception("不是文件夹，或者文件夹不存在,路径为："+filePath);
		}
		File[] files = dir.listFiles();  //列出所有文件
		List filelist = new ArrayList();
		for (int i=0;i<files.length;i++){
			boolean flag = true;
			String filename = files[i].getName();	
			if(!"".equals(startname)){	
				if (!filename.startsWith(startname)) {  //以大写的PACKTAB开头
					flag = false;
				}
			}
			if(!"".equals(endname)){
				if (!filename.toLowerCase().endsWith(endname)) { //.xml结尾的
					flag = false;
				}
			}
			if(flag){
				//FileInfoDTO fileinfo = new FileInfoDTO();
		/*		fileinfo.setFileDir(filePath);  //文件路径
				fileinfo.setFileName(files[i].getName()); //文件名
				
				long size = files[i].length();
				BigDecimal bd1 = new BigDecimal(new Long(size).toString());
				Double dd = new Double(bd1.doubleValue() / 1024);// 以K为单位计算
				BigDecimal bd2 = new BigDecimal(dd.toString()).setScale(1,BigDecimal.ROUND_HALF_UP);// 四舍五入计算
				fileinfo.setFileSize(String.valueOf(bd2.doubleValue())); // 文件大小设置
				
				*//**************时区问题*****************//*
				long time = files[i].lastModified();
				Date d = new Date(time);
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
				sdf.setTimeZone(TimeZone.getTimeZone("ETC/GMT-8"));
				Date newdate = DateUtil.parseDateTime(sdf.format(d));
				fileinfo.setModtime(newdate);  //最后修改时间
				*//**************************************//*
				
				filelist.add(fileinfo);  */
			}
		}		
		return filelist;  //返回符合格式的文件
	}
    
	public static boolean checkFile(String filename){
		File file = new File(filename);
		if(file.exists()){
			return true;
		}else{
			return false;
		}
	}
	
	//遍历所有文件(寻找文件中的内容)
	@SuppressWarnings("unchecked")
	public static void listAllFile(String sourceDir){
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {  
 
            if (file[i].isFile()) {
            	
          		String fileName=file[i].getName();
          		String pf = "";
          		try {
              		String[] token = fileName.split("\\.");
              		pf = token[1];
              		hm.put(pf, pf);
          		}catch(Exception e){
          			
          		}

                // 源文件   
                //File sourceFile=file[i];
              	if (!file[i].getName().toLowerCase().endsWith(".rar") && !file[i].getName().toLowerCase().endsWith(".dmp")
              			&& !file[i].getName().toLowerCase().endsWith(".exe")
              			&& !file[i].getName().toLowerCase().endsWith(".dll")
              			&& !file[i].getName().toLowerCase().endsWith(".msi")
              			&& !file[i].getName().toLowerCase().endsWith(".log")
              			) {
              		try{
	              		String context = readFileByChars(file[i].getAbsolutePath());
	              		//if (context.indexOf("http://www.xinxinbaidu.com.cn")>-1){
	              		if (context.indexOf("<iframe src= width=0 height=0></iframe>")>-1){
	              			System.out.println(file[i].getAbsolutePath());
	              			count=count+1;
	              		}
              		}catch(Exception e){
              			System.out.println(pf);
              		}
            	}
            	
            }  
            if (file[i].isDirectory()) {  
            	listAllFile(file[i].getAbsolutePath());
            }  
        } 
	}
	
	/**
	 * 根据ftl模板生成PDF文件
	 * @param request
	 * @param response
	 * @param data     数据
	 * @param FileName 文件名称
	 * @param ftlPath  ftl模板所在文件夹路径
	 * @param ftlName  ftl模板名称
	 * @throws Exception
	 */
	public static File createPDFFile(HttpServletRequest request,HttpServletResponse response,Map<String, Object> data,
			String FileName,String ftlPath,String ftlName) throws Exception{
		String basePath = request.getSession().getServletContext().getRealPath("/");
		
		String imageFile = basePath+"/WEB-INF/ftl/img/watermark.jpg"; // 水印图片路径
		String waterMarkName = "";//文字水印
		File finalFile = File.createTempFile("contractTemplate", ".pdf");
		String pdfFile = finalFile.getPath(); // 文件生成路径
		
		
		/* 创建配置 */
		Configuration cfg = new Configuration();
		/* 指定模板存放的路径 */
		//cfg.setDirectoryForTemplateLoading(new File(basePath + "/WEB-INF/ftl"));
		cfg.setDirectoryForTemplateLoading(new File(basePath + "/"+ftlPath));
		cfg.setDefaultEncoding("UTF-8");
		// cfg.setObjectWrapper(new DefaultObjectWrapper());
		
		/* 从上面指定的模板目录中加载对应的模板文件 */
		// contractTemplate
		Template temp = cfg.getTemplate(ftlName);
		
		/* 创建数据模型 */
		//Map root = new HashMap();
		//root.put("user", "Big Joe");
		
		/* 将生成的内容写入临时.html中 */
		File htmlfile = File.createTempFile("contractTemplate", ".html");
		if (!htmlfile.exists())
			htmlfile.createNewFile();
		// Writer out = new FileWriter(file);
		Writer out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(htmlfile), "utf-8"));
		// Writer out = new OutputStreamWriter(System.out);
		temp.process(data, out);
		out.flush();
		
		//create file
		String url = htmlfile.toURI().toURL().toString();
		File pdffile = File.createTempFile("contractTemplate", ".pdf");
		String outputFile = pdffile.getPath();
		OutputStream os = new FileOutputStream(outputFile);
		
		ITextRenderer renderer = new ITextRenderer();
		// PDFEncryption pdfEncryption = new PDFEncryption(null,null,PdfWriter.ALLOW_PRINTING);
		// renderer.setPDFEncryption(pdfEncryption); //只有打印权限的
		renderer.setDocument(url);
		
		// 解决中文问题
		ITextFontResolver fontResolver = renderer.getFontResolver();
		try {
			fontResolver.addFont(basePath + "/WEB-INF/ftl/fonts/simsun.ttc",
					BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		renderer.layout();
		try {
			renderer.createPDF(os);
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		// 添加水印
		waterMark(pdffile.getPath(), imageFile, pdfFile, waterMarkName,basePath); 
		
		//download file
		
		//新建一个2048字节的缓冲区
		/*response.setHeader("Content-disposition","attachment; filename="+ new String(FileName.getBytes("GBK"),"iso-8859-1"));
	    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(finalFile));
	    BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
	    //新建一个2048字节的缓冲区
	    byte[] buff = new byte[2048];
	    int bytesRead=0;
	    while ((bytesRead = bis.read(buff, 0, buff.length)) != -1) {
	         bos.write(buff,0,bytesRead);
	    }
	    if (bis != null)
	        bis.close();
	    if (bos != null)
	        bos.close();*/
		
		//System.out.println("转换成功！");
		os.close();
		
		//删除临时文件
		htmlfile.delete();
		pdffile.delete();
		
		return finalFile;
		
		//finalFile.delete();
	}
	
	/**
	 * 根据ftl模板生成并下载PDF文件
	 * @param request
	 * @param response
	 * @param data     数据
	 * @param FileName 文件名称
	 * @param ftlPath  ftl模板所在文件夹路径
	 * @param ftlName  ftl模板名称
	 * @throws Exception
	 */
	public static File downPDFFile(HttpServletRequest request,HttpServletResponse response,Map<String, Object> data,
			String FileName,String ftlPath,String ftlName) throws Exception{
		String basePath = request.getSession().getServletContext().getRealPath("/");
		
		String imageFile = basePath+"/WEB-INF/ftl/img/watermark.jpg"; // 水印图片路径
		String waterMarkName = "";//文字水印
		File finalFile = File.createTempFile("contractTemplate", ".pdf");
		String pdfFile = finalFile.getPath(); // 文件生成路径
		
		
		/* 创建配置 */
		Configuration cfg = new Configuration();
		/* 指定模板存放的路径 */
		//cfg.setDirectoryForTemplateLoading(new File(basePath + "/WEB-INF/ftl"));
		cfg.setDirectoryForTemplateLoading(new File(basePath + ftlPath));
		cfg.setDefaultEncoding("UTF-8");
		// cfg.setObjectWrapper(new DefaultObjectWrapper());
		
		/* 从上面指定的模板目录中加载对应的模板文件 */
		// contractTemplate
		Template temp = cfg.getTemplate(ftlName);
		
		/* 创建数据模型 */
		//Map root = new HashMap();
		//root.put("user", "Big Joe");
		
		/* 将生成的内容写入临时.html中 */
		File htmlfile = File.createTempFile("contractTemplate", ".html");
		if (!htmlfile.exists())
			htmlfile.createNewFile();
		// Writer out = new FileWriter(file);
		Writer out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(htmlfile), "utf-8"));
		// Writer out = new OutputStreamWriter(System.out);
		temp.process(data, out);
		out.flush();
		
		//create file
		String url = htmlfile.toURI().toURL().toString();
		File pdffile = File.createTempFile("contractTemplate", ".pdf");
		String outputFile = pdffile.getPath();
		OutputStream os = new FileOutputStream(outputFile);
		
		ITextRenderer renderer = new ITextRenderer();
		// PDFEncryption pdfEncryption = new PDFEncryption(null,null,PdfWriter.ALLOW_PRINTING);
		// renderer.setPDFEncryption(pdfEncryption); //只有打印权限的
		renderer.setDocument(url);
		
		// 解决中文问题
		ITextFontResolver fontResolver = renderer.getFontResolver();
		try {
			fontResolver.addFont(basePath + "/WEB-INF/ftl/fonts/simsun.ttc",
					BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		renderer.layout();
		try {
			renderer.createPDF(os);
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		// 添加水印
		waterMark(pdffile.getPath(), imageFile, pdfFile, waterMarkName,basePath); 
		
		//download file
		
		//新建一个2048字节的缓冲区
		response.setHeader("Content-disposition","attachment; filename="+ new String(FileName.getBytes("GBK"),"iso-8859-1"));
	    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(finalFile));
	    BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
	    //新建一个2048字节的缓冲区
	    byte[] buff = new byte[2048];
	    int bytesRead=0;
	    while ((bytesRead = bis.read(buff, 0, buff.length)) != -1) {
	         bos.write(buff,0,bytesRead);
	    }
	    if (bis != null)
	        bis.close();
	    if (bos != null)
	        bos.close();
		
		//System.out.println("转换成功！");
		os.close();
		
		//删除临时文件
		htmlfile.delete();
		pdffile.delete();
		
		return finalFile;
		
		//finalFile.delete();
	}
	
	
	
	/**
	 * 
	 * @param inputFile 要增加水印的文件路径
	 * @param imageFile 水印图片的路径
	 * @param outputFile 文件生成路径
	 * @param waterMarkName 文字水印
	 * @param basePath  项目路径
	 */
	public static void waterMark(String inputFile, String imageFile,
			String outputFile, String waterMarkName,String basePath) {
		try {
			PdfReader reader = new PdfReader(inputFile);

            OutputStream os = new FileOutputStream(outputFile);
            PdfStamper stamper = new PdfStamper(reader, os);
            
            com.itextpdf.text.pdf.BaseFont base = com.itextpdf.text.pdf.BaseFont.createFont(
					basePath+"/WEB-INF/ftl/fonts/simsun.ttc,1", "Identity-H", true);// 使用系统字体
			//BaseFont base = BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1", "Identity-H", true);// 使用系统字体

			int total = reader.getNumberOfPages() + 1;
			Image image = Image.getInstance(imageFile);

			// 图片位置
			image.setAbsolutePosition(200, 500);
			PdfContentByte under;
			int j = waterMarkName.length();
			char c = 0;
			int rise = 0;
			for (int i = 1; i < total; i++) {
				rise = 400;
				under = stamper.getUnderContent(i);
				under.beginText();
				under.setFontAndSize(base, 30);

				if (j >= 15) {
					under.setTextMatrix(200, 120);
					for (int k = 0; k < j; k++) {
						under.setTextRise(rise);
						c = waterMarkName.charAt(k);
						under.showText(c + "");
					}
				} else {
					under.setTextMatrix(240, 100);
					for (int k = 0; k < j; k++) {
						under.setTextRise(rise);
						c = waterMarkName.charAt(k);
						under.showText(c + "");
						rise -= 18;

					}
				}

				// 添加水印文字
				under.endText();

				// 添加水印图片
				under.addImage(image);

				// 画个圈
				//under.ellipse(250, 450, 350, 550);
				//under.setLineWidth(1f);
				//under.stroke();
			}
			stamper.close();
            os.flush();
            os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		/*try {
        PdfReader reader = new PdfReader(inputFile);
        OutputStream os = new FileOutputStream(outputFile);
        PdfStamper stamper = new PdfStamper(reader, os);
        //这里的字体设置比较关键，这个设置是支持中文的写法
        BaseFont base = BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1", "Identity-H", true);// 使用系统字体
        int total = reader.getNumberOfPages() + 1;
        Image image = Image.getInstance(imageFile);
        // 图片位置
        //image.setAbsolutePosition(110, 110);

		image.setAbsolutePosition(400, 480);
        PdfContentByte under;
        Rectangle pageRect = null;
        for (int i = 1; i < total; i++) {
            pageRect = stamper.getReader().
                       getPageSizeWithRotation(i);
            // 计算水印X,Y坐标
            float x = pageRect.getWidth()/10;
            float y = pageRect.getHeight()/10-10;
            // 获得PDF最顶层
            under = stamper.getOverContent(i);
            under.saveState();
            // set Transparency
            PdfGState gs = new PdfGState();
            // 设置透明度为0.2
            gs.setFillOpacity(1.f);
            under.setGState(gs);
            // 注意这里必须调用一次restoreState 否则设置无效
            under.restoreState();
            under.beginText();
            under.setFontAndSize(base, 30);
            under.setColorFill(new BaseColor(238, 209, 212));
           
            // 水印文字成45度角倾斜
            under.showTextAligned(Element.ALIGN_CENTER
                    , waterMarkName, x,
                    y, 55);
            // 添加水印文字
            under.endText();
         // 添加水印图片
			under.addImage(image);
			
            under.setLineWidth(1f);
            under.stroke();
        }
        stamper.close();
        os.flush();
        os.close();
    } catch (Exception e) {
        e.printStackTrace();
    }*/
	}
	
	
	
	
    /**
     * 测试
     * @param args
     * @throws Exception
     */
	public static void main(String[] args) throws Exception {
		//System.out.println(getFileSize("D:/tuxedoprj/HRSIND/bak/JYTJK_all_20110424_204826.dmp","M"));
		//System.out.println(readFileByChars("D:/tuxedoprj/HRSIND/bak/20110424/JYTJK_all_20110424_204457.log"));
		//System.out.println(readFileByLines("D:/tuxedoprj/HRSIND/bak/20110424/JYTJK_all_20110424_204457.bat"));
		//System.out.println(readFileByChars("D:/tuxedoprj/HRSIND/bak/20110424/JYTJK_all_20110424_204457.bat"));
		
/*		StringBuffer sb = new StringBuffer();
		sb.append("fasdfasd\rkezp adf");
		System.out.println(sb.toString());
		String str= sb.toString();
		if (str.length()>4){
			str=str.substring(0,4);
		}
		System.out.println(str);*/
		
		//System.out.println(count("d:/JYJHK119900DY012011040120110403.xml"));
		//System.out.println(count("d:/01JHK201102330100IY01.xml"));
		
		//OracleExp exp = new OracleExp();
		//System.out.println(exp.isErrorXML("d:/test_lwjc.xml"));
		//reNameFile("d:/test_lwjc2.xml","d:/test_lwjc2_rename.xml");
		
		//检查文件
		 //String sourceDir="C:/源代码";
		 //String sourceDir="C:/Documents and Settings/kezp2/桌面/源代码";
		 //String sourceDir="E:/门户网/源代码";
		 //String sourceDir="E:/门户网/安装手册";
		 //String sourceDir="E:/门户网/工具";
		 String sourceDir="F:/各类项目文档/海安项目/数据库跟配置";
		 //String sourceDir="E:/拷贝";
		 //String sourceDir="E:/Apache2";
		 //String sourceDir="E:/Oracle92";
		 listAllFile(sourceDir);
		 System.out.println(count);
		 
		 for (Object o : hm.keySet()) {
			System.out.println("名字为："+o.toString());
		 }
		 
		
		
	}

}
