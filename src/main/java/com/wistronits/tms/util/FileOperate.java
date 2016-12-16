package com.wistronits.tms.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.compound.Lucene43CompoundWordTokenFilterBase;
import org.apache.poi.hssf.converter.ExcelToFoConverter;
import org.apache.poi.hssf.converter.ExcelToFoUtils;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class FileOperate {
/*	public static void downloadFile(final HttpServletResponse response,String resourcePath){
		InputStream is = null;
		ServletOutputStream out = null;
		try {
			is=new FileInputStream(new File(resourcePath));
			String filename=resourcePath.substring(resourcePath.lastIndexOf("\\")+1);
			//ExcelToFoUtils.loadXls(xlsFile)
			//response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");
			out = response.getOutputStream();
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = is.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
			response.flushBuffer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/

	public static ResponseEntity<byte[]> download(String resourcePath) throws IOException {
		File file=new File(resourcePath);
		String fileName = resourcePath.substring(resourcePath.lastIndexOf("\\")+1);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); 		
		headers.setContentDispositionFormData("attachment", fileName); 
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	


	}

}
