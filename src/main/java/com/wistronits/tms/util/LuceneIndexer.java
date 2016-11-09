package com.wistronits.tms.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
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
	
	public static String UPLOAD_FOLDER_PATH = "D:/uploadFiles";
	public static String LUCENE_INDEX_FOLDER_PATH = "D:/luceneIndex";
	
	@SuppressWarnings("deprecation")
	public static void createIndexer(File srcFile, int id){
		Directory directory = null;
		IndexWriter writer = null;
		Document doc = null;
		try {
			File indexDir = new File(LUCENE_INDEX_FOLDER_PATH);
			if(!indexDir.exists() && !indexDir.isDirectory()){
				indexDir.mkdir();
			}
			
			directory = FSDirectory.open(indexDir.toPath(),NoLockFactory.INSTANCE);
			
			IndexWriterConfig writerConfig = new IndexWriterConfig(new StandardAnalyzer());
			writer = new IndexWriter(directory,writerConfig);
			
			if(srcFile.isFile()){
				System.out.println("id:"+id);
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
				doc.add(new Field("content",content,Field.Store.NO,Field.Index.ANALYZED));
				doc.add(new Field("id",String.valueOf(id),Field.Store.YES,Field.Index.NO));
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
	
	public static Map<String, Object> seacher(String workKey, int page, int pageSize){
		Directory directory = null; 
		IndexReader reader = null;
		List<Integer> list = new ArrayList<Integer>();
		Map<String, Object> result = new HashMap<String,Object>();
		try {
			File indexDir = new File(LUCENE_INDEX_FOLDER_PATH);
			if(!indexDir.exists() && !indexDir.isDirectory()){
				indexDir.mkdir();
			}
			
			directory = FSDirectory.open(indexDir.toPath());
			reader = DirectoryReader.open(directory);
			IndexSearcher seacher = new IndexSearcher(reader);
			

//			Term term = new Term("content", "lucene");  
//			Query query = new TermQuery(term);
			
			QueryParser parser = new QueryParser("content", new StandardAnalyzer());
			Query query = parser.parse(workKey);
			
			TopDocs tds = getDocsByPage(page, pageSize, seacher, query);
			ScoreDoc[] docs = tds.scoreDocs;

//			System.out.println("all match files count:"+getAllMatchDocCount(seacher));
			System.out.println("matched files count:"+tds.totalHits);
			System.out.println("matched files count in page:"+docs.length);
			for (ScoreDoc scoreDoc : docs) {
				Document doc = seacher.doc(scoreDoc.doc);
				System.out.println("id:" + doc.get("id"));
				list.add(Integer.parseInt(doc.get("id")));
			}
			result.put("count", tds.totalHits);
			result.put("list", list);
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
		return result;
	}
	
	/**
	 * search by page
	 * @param searcher
	 * @return count
	 * @throws IOException 
	 */
	public static TopDocs getDocsByPage(int page,int pageSize,IndexSearcher searcher,Query query) throws IOException{
		TopDocs ret = null;
		ScoreDoc before = null;
		if(page!=1){
			TopDocs docsBefore = searcher.search(query, (page-1)*pageSize);
			ScoreDoc[] scoreDocs = docsBefore.scoreDocs;
			if(scoreDocs.length > 0){
				before = scoreDocs[scoreDocs.length - 1];
			}
		}
		ret = searcher.searchAfter(before, query, pageSize);
		return ret;
	}
	
	/**
	 * All match Doc count
	 * @param searcher
	 * @return count
	 */
	public static int getAllMatchDocCount(IndexSearcher seacher){
		return seacher.getIndexReader().maxDoc();
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
		 p.getPDDocument().close();
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