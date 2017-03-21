package com.superisong.GeneratePdf;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.RectangleReadOnly;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class Domo4BookMarkImageFolor {
	
	public static int chNum = 1;
	
	public static BaseFont bfCN =null;
	public static Font chFont=null;
	
	// 节的字体
	public static Font secFont =null;
	//pdf页号
	public static int pageSize=1;
	//书签个数
	public static int setionNum=0;
	 
    public static FileFilter fileFilter=null;
	 
	static{
			  FontFactory.register("C:\\WINDOWS\\Fonts\\simhei.ttf");
		      FontFactory.register("C:\\WINDOWS\\Fonts\\simkai.ttf");
		      FontFactory.register("C:\\WINDOWS\\Fonts\\simsun.ttc");
//			bfCN=BaseFont.createFont("","",true);//BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H",false);	
			// 章的字体
			
			 chFont =  FontFactory.getFont("simhei", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 12);//new Font(bfCN, 12, Font.NORMAL, BaseColor.BLUE);
			 //书签字体
			 secFont=FontFactory.getFont("simhei", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 12);
			 
			 fileFilter=getFileFilter();
		
	}
	
	
	 public static void main(String[] args) {
	        String fileName="E:/工作/开发需求/中央图库/完整版"+File.separator;
	        File f=new File(fileName);
	        if(f==null || !f.isDirectory()){
	        	throw new RuntimeException("只能为目录");
	        }
	        Domo4BookMarkImageFolor domo4BookMarkImageFolor=new Domo4BookMarkImageFolor();
	        Document document=domo4BookMarkImageFolor.getDocument(fileName+"/"+f.getName()+".pdf");
	        Chapter chapter=domo4BookMarkImageFolor.getChapter(f.getName());
	        domo4BookMarkImageFolor.scanImage(f,chapter,false);
	        domo4BookMarkImageFolor.writerDocument(document, chapter);
	        
	    }
	 
	 

	 
	 /**
	  * 扫描目录的文件
	  * @param f
	  * @param section
	  * @author fushun
	  * @version V3.0商城
	  * @creation 2017年3月14日
	  * @records <p>  fushun 2017年3月14日</p>
	  */
	    public  void scanImage(File f,Section section,boolean bool){
	        if(f!=null){
	            if(f.isDirectory()){
	            	
	            	//创建 书签
	            	Section section2;
	            	if(bool==true){
	            		section2=this.getSection(section, f.getName());
	            	}else{
	            		section2=section;
	            		bool=true;
	            	}
	            	//获取目录文件
	            	File[] fileArray=f.listFiles(fileFilter);
	            	List<File> fileList=sortFile(fileArray);
	                if(fileList!=null){
	                	for (File file : fileList) {
	                		//递归调用
	                		scanImage(file,section2,bool);
						}
	                }
	            }
	            else{
	            	//添加文件到书签
	            	Section section2=this.getSection(section, f.getName());
	            	this.createImg(section2, f);
	            }
	        }
	    }
	
	    /**
	     * 获取文件列表 过滤器
	     * @return
	     * @author fushun
	     * @version V3.0商城
	     * @creation 2017年3月16日
	     * @records <p>  fushun 2017年3月16日</p>
	     */
	    private static FileFilter getFileFilter(){
	    	FileFilter fileFilter=new FileFilter() {
				public boolean accept(File pathname) {
					if(pathname.isDirectory()){
						return true;
					}
					return pathname.getName().toLowerCase().endsWith(".jpg");
				}
			};
			return fileFilter;
	    }
	
	    /**
	     * 文件名称排序，如果名称签名有数字，则直接使用数字排序
	     * @param fileArray
	     * @return
	     * @author fushun
	     * @version V3.0商城
	     * @creation 2017年3月16日
	     * @records <p>  fushun 2017年3月16日</p>
	     */
	    private List<File> sortFile(File[] fileArray){
	    	 if(fileArray!=null){
             	List<File> fileList=new ArrayList<File>();
                 for (int i = 0; i < fileArray.length; i++) {
                 	fileList.add(fileArray[i]);
                 }
                 Collections.sort(fileList,new Comparator<File>(){  
                     public int compare(File o1,File o2){
                     	String o1intStr=o1.getName().replaceAll("[^0-9]+", "");
                     	String o2intStr=o2.getName().replaceAll("[^0-9]+", "");
                     	if(!o1intStr.equals("") && !o2intStr.equals("")){
                     		Integer o1int=Integer.valueOf(o1intStr);
                     		Integer o2int=Integer.valueOf(o2intStr);
                     		return o1int.compareTo(o2int);
                     	}
                         return o1.compareTo(o2);  
                     }
                 }); 
               return fileList;
             }
	    	 return null;
	    }
	    
	/**
	 * 获取文档
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 * @author fushun
	 * @version V3.0商城
	 * @creation 2017年3月14日
	 * @records <p>  fushun 2017年3月14日</p>
	 */
	public Document getDocument(String fileName) {
		Document document = new Document();
		try {
			PdfWriter writer =PdfWriter.getInstance(document, new FileOutputStream(fileName));
			writer.setStrictImageSequence(true);  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		document.open();
		return document;
	}
	
	/**
	 * 将书签文字添加到文档中
	 * @param document
	 * @param chapter
	 * @throws DocumentException
	 * @author fushun
	 * @version V3.0商城
	 * @creation 2017年3月14日
	 * @records <p>  fushun 2017年3月14日</p>
	 */
	public void writerDocument(Document document,Chapter chapter) {
		try {
			Rectangle A42 = new RectangleReadOnly(documentWidth+60,documentHeight+maxSetionNum*35);
			document.setPageSize(A42);
			document.add(chapter);
			document.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 创建一级书签
	 * @param name
	 * @return
	 * @author fushun
	 * @version V3.0商城
	 * @creation 2017年3月14日
	 * @records <p>  fushun 2017年3月14日</p>
	 */
	public Chapter getChapter(String name){
		Chapter chapter = new Chapter(new Paragraph(name, chFont),chNum++);
		chapter.setNumberStyle(Section.NUMBERSTYLE_DOTTED);
		return chapter;
	}
	
	/**
	 * 创建子级书签
	 * @param section
	 * @param sectionName
	 * @author fushun
	 * @version V3.0商城
	 * @creation 2017年3月14日
	 * @records <p>  fushun 2017年3月14日</p>
	 */
	public Section getSection(Section section,String sectionName){
		Section section2 = section.addSection(new Paragraph(sectionName, secFont));
		section.setNumberStyle(Section.NUMBERSTYLE_DOTTED);
//		section.setNotAddedYet();
		setionNum++;
		// section.setNumberDepth(2);
		// section.setBookmarkTitle("基本信息");
//		section2.setIndentation(10);
//		section2.setIndentationLeft(10);
//		section2.setBookmarkOpen(false);
//		section2.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);
		return section2;
	}
	/**
	 * 最大宽度
	 */
	public static float documentWidth=0;
	/**
	 * 最大高度
	 */
	public static float documentHeight=0;
	/**
	 * 最大相隔的目录数
	 */
	public static float maxSetionNum=0;
	
	/**
	 * 添加图片到 文档中
	 * @param section
	 * @param file
	 * @throws DocumentException
	 * @throws IOException
	 * @author fushun
	 * @version V3.0商城
	 * @creation 2017年3月14日
	 * @records <p>  fushun 2017年3月14日</p>
	 */
	public void createImg(Section section,File file){
		// 正文的字体
		  Image png1;
		try {
			png1 = Image.getInstance(file.getAbsolutePath());
			if(documentWidth<png1.getWidth()){
				documentWidth=png1.getWidth();
			}
			if(documentHeight<png1.getHeight()){
				documentHeight=png1.getHeight();
			}
			if(maxSetionNum<setionNum){
				maxSetionNum=setionNum;
			}
			//新页
			//设置页号
			
//			  float heigth = png1.getHeight();
//			  float width = png1.getWidth();
//			  int percent = this.getPercent2(heigth, width);
//			  png1.setAlignment(Image.MIDDLE);
//			  png1.setAlignment(Image.TEXTWRAP);
//			  png1.scalePercent(percent + 3);
			
//			png1.scaleAbsolute(png1.getWidth(),png1.getHeight());// 直接设定显示尺寸
//			png1.setAbsolutePosition(2, 615);
//			png1.scalePercent(80);
			  
			  section.add(png1);
			  setionNum=0;
//			  section.newPage();
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
