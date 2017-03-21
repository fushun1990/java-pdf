package com.superisong.GeneratePdf;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
 
/**
 *
 * @author &lt;a href="http://www.micmiu.com"&gt;Michael Sun&lt;/a&gt;
 */
public class Demo4BookMark {
 
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String fileName = "E:/工作/itext/demo-bookmark.pdf";
		Demo4BookMark.testBookMarks(fileName);
	}
 
	/**
	 *
	 * @param fileName
	 * @throws Exception
	 */
	public static void testBookMarks(String fileName) throws Exception {
		
		BaseFont bfCN = BaseFont.createFont();//BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H",false);
		// 章的字体
		Font chFont = new Font(bfCN, 12, Font.NORMAL, BaseColor.BLUE);
		// 节的字体
		Font secFont = new Font(bfCN, 12, Font.NORMAL, new BaseColor(0, 204,
				255));
		// 正文的字体
		Font textFont = new Font(bfCN, 12, Font.NORMAL, BaseColor.BLACK);
 
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(fileName));
		document.open();
 
		int chNum = 1;
		Chapter chapter = new Chapter(new Paragraph("Michael介绍", chFont),
				chNum++);
 
		Section section = chapter.addSection(new Paragraph("基本信息", secFont));
		// section.setNumberDepth(2);
		// section.setBookmarkTitle("基本信息");
		section.setIndentation(10);
		section.setIndentationLeft(10);
		section.setBookmarkOpen(false);
		section.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);
		section.add(new Paragraph("苦逼的码农一枚。。。", textFont));
 
		Section section2 = chapter.addSection(new Paragraph("SNS", secFont));
		section2.setIndentation(10);
		section2.setIndentationLeft(10);
		section2.setBookmarkOpen(false);
		section2.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);
		section2.add(new Paragraph("SNS地址分类：", textFont));
 
		section = section2.addSection(new Paragraph(new Chunk("我的博客", secFont)
				.setUnderline(0.2f, -2f).setAnchor("http://www.micmiu.com")));
		section.setBookmarkOpen(false);
		section.setIndentation(10);
		section.setIndentationLeft(10);
		section.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);
		section.add(new Paragraph(new Chunk("我的blog地址：http://www.micmiu.com",
				textFont).setUnderline(0.2f, -2f).setAnchor(
				"http://www.micmiu.com")));
		section.add(new Paragraph("分享自己的技术心得。", textFont));
 
		section = section2.addSection(new Paragraph(new Chunk("我的weibo",
				secFont).setUnderline(0.2f, -2f).setAnchor(
				"http://weibo.com/ctosun")));
		section.setIndentation(10);
		section.setIndentationLeft(10);
		section.setBookmarkOpen(false);
		section.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);
		section.add(new Paragraph(new Chunk("我的weibo：http://weibo.com/ctosun",
				textFont).setUnderline(0.2f, -2f).setAnchor(
				"http://weibo.com/ctosun")));
		section.add(new Paragraph("发表下心情，分享下技术，转转乱七八糟的新闻。", textFont));
 
		section = section2.addSection(new Paragraph(new Chunk("twitter",
				secFont)));
		section.setIndentation(10);
		section.setIndentationLeft(10);
		section.setBookmarkOpen(false);
		section.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);
		section.add(new Paragraph(new Chunk("twitter：@suncto", textFont)
				.setUnderline(0.2f, -2f).setAnchor("http://twitter/cuncto")));
		section.add(new Paragraph("一个常常被墙的地方", textFont));
 
		LineSeparator line = new LineSeparator(1, 100, new BaseColor(204, 204,
				204), Element.ALIGN_CENTER, -2);
 
		Paragraph p_line = new Paragraph("分割线");
		p_line.add(line);
		chapter.add(p_line);
		document.add(chapter);
 
		chapter = new Chapter(new Paragraph("Miu的介绍", chFont), chNum++);
		section = chapter.addSection(new Paragraph("基本信息", secFont));
		section.setIndentation(10);
		section.setIndentationLeft(10);
		section.setBookmarkOpen(false);
		section.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);
		section.add(new Paragraph("90后一枚，喜欢美食和旅游。。。", textFont));
 
		document.add(chapter);
 
		document.close();
	}
 
}