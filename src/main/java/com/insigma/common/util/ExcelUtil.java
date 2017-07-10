package com.insigma.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
  
/** 
 * excel读取 
 *  
 */  
  
public class ExcelUtil  
{  
    /** 总行数 */  
    private int totalRows = 0;  
    /** 总列数 */  
    private int totalCells = 0;  
    /** 错误信息 */  
    private String errorInfo;  

    /***/
    private static HSSFCellStyle style = null ; 
    
    /** 构造方法 */  
    public ExcelUtil()  
    {  
    }  
  
    /** 
     *  
     * @描述：得到总行数 
     * @参数：@return 
     *  
     * @返回值：int 
     */  
  
    public int getTotalRows()  
    {  
        return totalRows;  
  
    }  
  
    /** 
     *  
     * @描述：得到总列数 
     * @参数：@return 
     * @返回值：int 
     */  
  
    public int getTotalCells(){  
        return totalCells;  
    }  
  
    /** 
     *  
     * @描述：得到错误信息 
     * @参数：@return 
     * @返回值：String 
     */  
  
    public String getErrorInfo()  
    {  
        return errorInfo;  
    }  
  
    /** 
     *  
     * @描述：验证excel文件 
     * @参数：@param filePath　文件完整路径 
     * @参数：@return 
     * @返回值：boolean 
     */  
  
    public boolean validateExcel(String filePath){  
        /** 检查文件名是否为空或者是否是Excel格式的文件 */  
        if (filePath == null || !(WDWUtil.isExcel2003(filePath) || WDWUtil.isExcel2007(filePath)))  
        {  
            errorInfo = "文件名不是excel格式";  
            return false;  
        }  
  
        /** 检查文件是否存在 */  
  
        /*File file = new File(filePath);  
        if (file == null || !file.exists())  
        {  
            errorInfo = "文件不存在";  
            return false;  
        }*/  
        return true;  
  
    }  
  
    /** 
     *  
     * @描述：根据文件名读取excel文件 
     * @参数：@param 文件流 
     * @参数：@param filePath 文件名 
     * @参数：@return 
     * @返回值：List 
     */  
  
    public List<List<String>> read(InputStream is, String filePath){  
        List<List<String>> dataLst = new ArrayList<List<String>>();  
        try  
        {  
            /** 验证文件是否合法 */  
            if (!validateExcel(filePath))  
            {  
                System.out.println(errorInfo);  
                return null;  
            }  
  
            /** 判断文件的类型，是2003还是2007 */  
 
            boolean isExcel2003 = true;  
            if (WDWUtil.isExcel2007(filePath))  
            {  
                isExcel2003 = false;  
            }  
            /** 调用本类提供的根据流读取的方法 */  
            dataLst = read(is, isExcel2003);  
            is.close();  
        }  
        catch (Exception ex)  
        {  
            ex.printStackTrace();  
        }  
        finally  
        {  
            if (is != null)  
            {  
                try  
                {  
                    is.close();  
                }  
                catch (IOException e)  
                {  
                    is = null;  
                    e.printStackTrace();  
                }  
            }  
        }  
  
        /** 返回最后读取的结果 */  
        return dataLst;  
  
    }  
  
    /** 
     *  
     * @描述：根据流读取Excel文件 
     * @参数：@param inputStream 
     * @参数：@param isExcel2003 
     * @参数：@return 
     * @返回值：List 
     */  
  
    public List<List<String>> read(InputStream inputStream, boolean isExcel2003){  
        List<List<String>> dataLst = null;  
        try  
        {  
            /** 根据版本选择创建Workbook的方式 */  
            Workbook wb = null;  
            if (isExcel2003){  
                wb = new HSSFWorkbook(inputStream);  
            }  
            else  
            {  
                wb = new XSSFWorkbook(inputStream);  
            }  
            dataLst = read(wb);  
  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
        return dataLst;  
  
    }  
  
    /** 
     *  
     * @描述：读取数据 
     * @参数：@param Workbook 
     * @参数：@return 
     * @返回值：List<List<String>> 
     */  
  
    private List<List<String>> read(Workbook wb){  
        List<List<String>> dataLst = new ArrayList<List<String>>();  
        /** 得到第一个shell */  
        Sheet sheet = wb.getSheetAt(0);  
        /** 得到Excel的行数 */  
        this.totalRows = sheet.getPhysicalNumberOfRows();  
        /** 得到Excel的列数 */  
        if (this.totalRows >= 1 && sheet.getRow(0) != null)  
        {  
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();  
        }  
        /** 循环Excel的行 */  
  
        for (int r = 0; r <= this.totalRows; r++){  
            Row row = sheet.getRow(r);  
            if (row == null)  
            {  
                continue;  
            }  
            List<String> rowLst = new ArrayList<String>();  
  
            /** 循环Excel的列 */  
  
            for (int c = 0; c < this.getTotalCells(); c++)  
            {  
                Cell cell = row.getCell(c);  
                String cellValue = "";  
                if (null != cell)  
                {  
                    // 以下是判断数据的类型  
                    switch (cell.getCellType())  
                    {  
                    case HSSFCell.CELL_TYPE_NUMERIC: // 数字  
                    	DecimalFormat df = new DecimalFormat("0");//防止数字变成科学计数法
                    	cellValue = df.format(cell.getNumericCellValue()); 
                        break;  
                    case HSSFCell.CELL_TYPE_STRING: // 字符串  
                        cellValue = cell.getStringCellValue();  
                        break;  
                    case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean  
                        cellValue = cell.getBooleanCellValue() + "";  
                        break;  
                    case HSSFCell.CELL_TYPE_FORMULA: // 公式  
                        cellValue = cell.getCellFormula() + "";  
                        break;  
                    case HSSFCell.CELL_TYPE_BLANK: // 空值  
                        cellValue = "";  
                        break;  
                    case HSSFCell.CELL_TYPE_ERROR: // 故障  
                        cellValue = "非法字符";  
                        break;  
                    default:  
                        cellValue = "未知类型";  
                        break;  
                    }  
                }  
                rowLst.add(cellValue);  
            }  
  
            /** 保存第r行的第c列 */  
            dataLst.add(rowLst);  
        }  
        return dataLst;  
    }  
    
    /** 
     * 通过给定的Sql导出Excel文件到Response输出流，需要指定Connection 
     * @param response HttpServletResponse Response 
     * @param conn Connection 指定的数据库连接 
     * @param sql String 查询的Sql语句 
     * @param sheetName String 导出的Excel Sheet名称 
     * @throws SQLException 
     */  
    @SuppressWarnings("deprecation")
	public static void export( HttpServletResponse response , Connection conn ,  String sql , String sheetName  )  throws SQLException {  
        PreparedStatement ps = null ;  
        ResultSet rs = null ;  
        ps = conn.prepareStatement( sql ) ;  
        rs = ps.executeQuery() ;  
  
        ResultSetMetaData rsmd = rs.getMetaData() ;  
        
        String [] cNames = getColumnNames( rsmd ) ;  
  
        HSSFWorkbook wb = new HSSFWorkbook() ;  
        style = wb.createCellStyle() ;  
        style.setAlignment( HSSFCellStyle.ALIGN_CENTER ) ;  
  
        HSSFSheet sheet = wb.createSheet(sheetName);
        int rowCnt = 0 ;  
        createColumnRow(rowCnt,sheet,cNames); 
        while ( rs.next() ) {  
            HSSFRow row = sheet.createRow( rowCnt + 1 ) ;  
            for ( int i = 0 ; i < cNames.length ; i++ ) {  
  
                HSSFCell cell = row.createCell( ( short ) i ) ;  
                String val = rs.getString( cNames[ i ] ) ;  
                if ( null == val ) {  
                    val = "" ;  
                }  
                //cell.setCellValue( val.toUpperCase() ) ;  
                cell.setCellValue( val ) ;  
            }  
            rowCnt++ ;  
        }  
        try {  
            OutputStream os = response.getOutputStream() ;  
            response.reset() ;  
            response.setContentType( "application/vnd.ms-excel" ) ;  
            response.setHeader( "Content-disposition" , "attachment; filename=" + new String(getFileName(sheetName).getBytes("GBK"),"iso-8859-1")) ;  
            wb.write( os ) ;  
            if ( conn != null ) {  
                conn.close() ;  
            }  
        }  
        catch ( IOException ex ) {  
            ex.printStackTrace();
        }  
    }  
  
    /** 
     * 创建excel标题列 
     * @param rsmd ResultSetMetaData 
     * @return String[] 
     */  
    @SuppressWarnings("deprecation")
	private static void createColumnRow(int rowCnt, HSSFSheet sheet,String [] cNames ) {  
    	 HSSFRow row = sheet.createRow( rowCnt ) ;  
         for ( int i = 0 ; i < cNames.length ; i++ ) {  
             HSSFCell cell = row.createCell( ( short ) i ) ;  
             cell.setCellValue( cNames[i]) ;  
         }  
         rowCnt++;
    }  
  
    /** 
     * 获得源数据中的列名称 
     * @param rsmd ResultSetMetaData 
     * @return String[] 
     */  
    private static String[] getColumnNames( ResultSetMetaData rsmd ) {  
        try {  
            StringBuffer result = new StringBuffer("") ;  
            for ( int i = 1 ; i <= rsmd.getColumnCount() ; i++ ) {  
                result.append(rsmd.getColumnLabel( i )).append(",");  
            }  
            if ( result.length()>0 ) {  
                return result.substring( 0 , result.length() - 1 ).toString().split( "," ) ;  
            }  
        }  
        catch ( Exception e ) {  
            return null ;  
        }  
        return null ;  
    }  
  
    /** 
     * 获得导出的文件全名 
     * @param tableName String 
     * @return String 
     */  
    private static String getFileName( String tableName ) {  
        return tableName + new Date().getTime() + ".xls" ;  
    }  
    
    
    /** 
     * 通过给定的List<HashMap>导出Excel文件到Response输出流， 
     * @param response HttpServletResponse Response 
     * @param conn Connection 指定的数据库连接 
     * @param sql String 查询的Sql语句 
     * @param sheetName String 导出的Excel Sheet名称 
     * @throws SQLException 
     */
	@SuppressWarnings("deprecation")
	public static void exportByList( HttpServletResponse response , List<LinkedHashMap<String, Object>> list , String sheetName  )  throws SQLException { 
    	String [] cNames = new String[0];
    	if(list.size()>0){
    		LinkedHashMap<String, Object> map = list.get(0);
    		Set<String> keys = map.keySet();
    		cNames = new String[keys.size()];
    		StringBuffer result = new StringBuffer("") ;  
    		Iterator<String> it = keys.iterator();
    		while (it.hasNext()){
				result.append(it.next()).append(",");  
    		}
    		if ( result.length()>0 ) {  
    			cNames = result.substring( 0 , result.length() - 1 ).toString().split( "," ) ;  
    		}
    	}
  
        HSSFWorkbook wb = new HSSFWorkbook() ;  
        style = wb.createCellStyle() ;  
        style.setAlignment( HSSFCellStyle.ALIGN_CENTER ) ;  
  
        HSSFSheet sheet = wb.createSheet(sheetName);
        int rowCnt = 0 ;  
        createColumnRow(rowCnt,sheet,cNames); 
        
        for(LinkedHashMap<String, Object> map : list){
        	HSSFRow row = sheet.createRow( rowCnt + 1 );
        	for ( int i = 0 ; i < cNames.length ; i++ ) {  
                HSSFCell cell = row.createCell( ( short ) i ) ;
                String val = "";
                if(map.get(cNames[i])!=null){
                	val = map.get(cNames[i]).toString();
                }
                cell.setCellValue( val ) ;  
            }  
            rowCnt++ ;  
        }
        try {  
            OutputStream os = response.getOutputStream() ;  
            response.reset() ;  
            response.setContentType( "application/vnd.ms-excel" ) ;  
            response.setHeader( "Content-disposition" , "attachment; filename=" + new String(getFileName(sheetName).getBytes("GBK"),"iso-8859-1")) ;  
            wb.write( os ) ;  
        }  
        catch ( IOException ex ) {  
            ex.printStackTrace();
        }  
    }  
    
  
    /** 
     *  
     * @描述：main测试方法 
     * @参数：@param args 
     * @参数：@throws Exception 
     * @返回值：void 
     */  
    public static void main(String[] args) throws Exception{  
        /*ExcelUtil poi = new ExcelUtil();  
        // List<List<String>> list = poi.read("d:/aaa.xls");  
        File file=new File("c:/book.xlsx");
        List<List<String>> list = poi.read(new FileInputStream(file),file.getName());  
        if (list != null)  
        {  
            for (int i = 0; i < list.size(); i++)  
            {  
                System.out.print("第" + (i) + "行");  
                List<String> cellList = list.get(i);  
                for (int j = 0; j < cellList.size(); j++)  
                {  
                    // System.out.print("    第" + (j + 1) + "列值：");  
                    System.out.print("    " + cellList.get(j));  
                }  
                System.out.println();  
            }  
        }  */
    	Map<String, Object> map = new HashMap<String, Object>();
	    map.put("a", "a");
	    map.put("b", "b");
	    map.put("c", "c");
	    map.put("d", "d");
	    Set<String> keys = map.keySet();
	    String [] str = new String[keys.size()];
	    StringBuffer result = new StringBuffer("") ;  
	    Iterator<String> it = keys.iterator();
	    while (it.hasNext()){
		   System.out.println(it.next());
		   result.append(it.next()).append(",");  
	    }
	    if ( result.length()>0 ) {  
		   str = result.substring( 0 , result.length() - 1 ).toString().split( "," ) ;  
	    }  
	    for(int i=0;i<str.length;i++){
		   System.out.println(str[i]);
	    }
    }  
}  
  
class WDWUtil  
{  
    /** 
     *  
     * @描述：是否是2003的excel，返回true是2003 
     * @参数：@param filePath　文件完整路径 
     * @参数：@return 
     * @返回值：boolean 
     */  
  
    public static boolean isExcel2003(String filePath){  
        return filePath.matches("^.+\\.(?i)(xls)$");  
    }  
  
    /** 
     *  
     * @描述：是否是2007的excel，返回true是2007 
     * @参数：@param filePath　文件完整路径 
     * @参数：@return 
     * @返回值：boolean 
     */  
  
    public static boolean isExcel2007(String filePath){  
        return filePath.matches("^.+\\.(?i)(xlsx)$");  
  
    }  
  
}  
