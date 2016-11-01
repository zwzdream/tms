package com.wistronits.tms.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.NoLockFactory;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;

public class LuceneIndexer {
	//source file
//	private static String FILE_PATH = "D:\\LuceneFiles\\script.txt";
	//create lucene index to dir
	static{
		File indexDir = new File("D:\\LuceneIndex");
		if(!indexDir.exists() && !indexDir.isDirectory()){
			indexDir.mkdir();
		}
	}
	private static File INDEX_DIR = new File("D:\\LuceneIndex");;
	
//	public static void main(String[] args){
////		createIndexer(new File(FILE_PATH));
//		seacher("Shipping Label");
//	}
	public static void createIndexer(File srcFile){
		Directory directory = null;
		IndexWriter writer = null;
		Document doc = null;
		try {
			directory = FSDirectory.open(INDEX_DIR.toPath(),NoLockFactory.INSTANCE);
			
			IndexWriterConfig writerConfig = new IndexWriterConfig(new StandardAnalyzer());
			writer = new IndexWriter(directory,writerConfig);
			
			if(srcFile.isFile()){
				System.out.println("File "+ srcFile.getCanonicalPath() + "  creating the index....");
				doc = new Document();
				String content = null;
				String filePath = srcFile.getAbsolutePath();
				if (filePath.endsWith(".txt") || filePath.endsWith(".xml")){
					content = FileReaderAll(srcFile.getCanonicalPath(), "utf-8");
				}else if (filePath.endsWith(".doc") || filePath.endsWith(".docx")) {
					content = WordFileReader(srcFile.getCanonicalPath());
				}else if (filePath.endsWith(".xls") || filePath.endsWith(".xlsx")) {
					content = ExcelFileReader(srcFile.getCanonicalPath());
				}else if (filePath.endsWith(".pdf")) {
					content = PdfboxFileReader(srcFile.getCanonicalPath());
				}
				doc.getFields().add(new TextField("content",content,Field.Store.YES));
				doc.getFields().add(new TextField("filename",srcFile.getName(),Field.Store.YES));
				doc.getFields().add(new TextField("path",srcFile.getAbsolutePath(),Field.Store.YES));
				writer.addDocument(doc);
				System.out.println("Create index successfully!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlException e) {
			e.printStackTrace();
		} catch (OpenXML4JException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(writer!=null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static List<String> seacher(String workKey){
		Directory directory = null; 
		IndexReader reader = null;
		List<String> ret = new ArrayList<String>();
		try {
			directory = FSDirectory.open(INDEX_DIR.toPath());
			reader = DirectoryReader.open(directory);
			IndexSearcher seacher = new IndexSearcher(reader);
			
			QueryParser parser = new QueryParser("content", new StandardAnalyzer());
			Query query = parser.parse(workKey);
			
			TopDocs tds = seacher.search(query, 10);
			ScoreDoc[] docs = tds.scoreDocs;
//			if(docs.length==0){
//				System.out.println("No file matched!");
//			}else{
//				System.out.println("matched files count��"+tds.totalHits);  
//				for (ScoreDoc scoreDoc : docs) {
//					Document doc = seacher.doc(scoreDoc.doc);
//					System.out.println("Path:" + doc.get("path"));
//				}
//			}
			System.out.println("matched files count��"+tds.totalHits);
			for (ScoreDoc scoreDoc : docs) {
				Document doc = seacher.doc(scoreDoc.doc);
				System.out.println("Path:" + doc.get("path"));
				ret.add(doc.get("path"));
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}
	/**
	  * Excel extractor
	  * @param fileName
	  * @param path
	  * @return
	  * @throws IOException
	  */
	 public static String ExcelFileReader(String filePath) throws IOException {
		 InputStream path = new FileInputStream(filePath);
		 String content = null;
		 XSSFWorkbook wb = new XSSFWorkbook(path);
		 XSSFExcelExtractor extractor = new XSSFExcelExtractor(wb);
		 extractor.setFormulasNotResults(true);
		 extractor.setIncludeSheetNames(false);
		 content = extractor.getText();
		 return content;
	 }
	 /**
	  * PDF extractor
	  * @param fileName
	  * @param path
	  * @return
	  * @throws Exception
	  */
	 public static String PdfboxFileReader(String filePath) throws Exception {
		 StringBuffer content = new StringBuffer("");
		 FileInputStream fis = new FileInputStream(filePath);
		 PDFParser p = new PDFParser(fis);
		 p.parse();
		 PDFTextStripper ts = new PDFTextStripper();
		 content.append(ts.getText(p.getPDDocument()));
		 fis.close();
		 return content.toString().trim();
	 }

	 /**
	  * word extractor
	  * @param fileName
	  * @param path
	  * @return
	  * @throws IOException
	 * @throws OpenXML4JException 
	 * @throws XmlException 
	  */
	 public static String WordFileReader(String filePath) throws IOException, XmlException, OpenXML4JException {
		 String bodyText = null;
		 if(filePath.endsWith(".doc")){
			 InputStream is = new FileInputStream(new File(filePath));
			 WordExtractor ex = new WordExtractor(is);
			 bodyText = ex.getText();
			 is.close();
		 }else{
			 bodyText = new XWPFWordExtractor(POIXMLDocument.openPackage(filePath)).getText();
		 }
		 return bodyText;
	 }
	 /**
	  * TXT extractor
	  * 
	  * @param fileName 
	  * @param charSet utf-8
	  * @return
	  * @throws IOException
	  */
	 public static String FileReaderAll(String filePath, String charSet)throws IOException {
		 BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), charSet));
		 String line = new String();
		 String temp = new String();
		 while ((line = reader.readLine()) != null) {
			 temp += line;
		 }
		 reader.close();
		 return temp;
	 }
}
