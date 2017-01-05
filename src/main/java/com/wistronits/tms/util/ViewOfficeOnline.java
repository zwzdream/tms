package com.wistronits.tms.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.junit.Test;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
/**
 * jodconverter2.2.1只支持office2003版本及以下，2.2.2支持office2007及以上版本，但是不能用maven配置
 * @author WH160709P
 *
 */
public class ViewOfficeOnline {
	private static File resFile;
	private static File swfFile;
	private static File pdfFile;
	
	public static void setFileString(String fileString,String fileName){
	        resFile = new File(fileString);  
	        pdfFile = new File(fileName + ".pdf");  
	        swfFile = new File(fileName + ".swf");  
	}
	public static void setFileString(String fileName){
		pdfFile = new File(fileName + ".pdf");  
		swfFile = new File(fileName + ".swf");  
	}



	/**
	 * 转为PDF
	 * @param resourcePath
	 */
	public static void office2pdf(){
		String OpenOffice_Home="C:/Program Files (x86)/OpenOffice 4/";//OpenOffice的安装目录
		Process pro=null;
		try {
		    //启动OpenOffice的服务
		    String command=OpenOffice_Home+"program/soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\"";
			pro = Runtime.getRuntime().exec(command);
			//connect to an OpenOffice instance running on port 8100
			OpenOfficeConnection connection=new SocketOpenOfficeConnection("127.0.0.1", 8100);
			connection.connect();
			//convert
			DocumentConverter converter=new OpenOfficeDocumentConverter(connection);
			if(resFile.exists()){
				if(!pdfFile.exists()){//convert
					converter.convert(resFile,pdfFile); 
					 System.out.println("****office文件已经成功转换为pdf文件****"); 
				}else {
					 System.out.println("****office文件已经转换为pdf文件，不需要再进行转化****");  
				}
			}else{
				System.out.println("office文件不存在，请仔细核对！");
			}
			//close the connection
			 connection.disconnect();
			 //close process of OpenOffice service
			 pro.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("****office文件转换为pdf文件失败****");
		}   finally{	if(pro.isAlive()){
			 pro.destroy();
		}
		}
		
	}
	/**
	 * 
	 * @return success:0
	 */
	
	public static void pdf2swf(){
		String swfTools_Home="D:/Program Files/swfTools/";//swfTools的安装目录
		Process pro=null;
		try {
		String command=swfTools_Home+"pdf2swf.exe "+pdfFile.getPath()+" -o"+swfFile.getPath() + " -T 9 -f"+" -G -s poly2bitmap";  
			if(pdfFile.exists()){
				if(!swfFile.exists()){//convert
					pro = Runtime.getRuntime().exec(command,null,new File("d:/saveFiles"));//调用swfTools
					BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(pro.getInputStream()));
					while(bufferedReader.readLine()!=null);
					try {  
			            pro.waitFor();  
			        } catch (InterruptedException e) {  
			            // TODO Auto-generated catch block  
			            e.printStackTrace();  
			        } 
					pro.destroy();
					 System.out.println("****pdf文件已经成功转换为swf文件****");  
				}else {
					 System.out.println("****pdf文件已经转换为swf文件，不需要再进行转化****");  
				}
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("****pdf文件转换为swf文件失败****");
		} finally{	
		/*	if(pdfFile.exists()){
			if(pdfFile.delete()){
				System.out.println("删除生成的临时pdf文件");
			}
			}*/
		}
	}
	
	
	public static String toTransferString(String filePath){
		return filePath.replaceAll("\\\\", "/");
	}
	
	@Test
	public void officetoswf(){
		/**
		 * 03JavaScript.ppt可以转为03JavaScript.pef，但是02CSS.ppt不行，猜测02CSS.ppt是office2007及以上版本
		 *
		 */
		//ViewOfficeOnline.setFileString("D:/saveFiles/03JavaScript.ppt","D:/saveFiles/03JavaScript");
		//ViewOfficeOnline.setFileString("D:/saveFiles/git.doc","D:/saveFiles/git");
		//ViewOfficeOnline.setFileString("D:/saveFiles/Daily.xls","D:/saveFiles/Daily");
		ViewOfficeOnline.setFileString("D:/saveFiles/tms.txt","D:/saveFiles/tms");
       office2pdf();
       pdf2swf();
	}
	
	@Test
	public void pdftoswf(){
		ViewOfficeOnline.setFileString("D:/saveFiles/03JavaScript");
		pdf2swf();
	}

}
