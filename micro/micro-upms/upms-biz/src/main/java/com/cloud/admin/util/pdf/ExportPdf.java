package com.cloud.admin.util.pdf;

import com.cloud.admin.api.util.excel.annotation.ExcelField;
import com.cloud.admin.util.DictUtils;
import com.cloud.admin.util.ExportUtils;
import com.cloud.admin.util.Reflections;
import com.google.common.collect.Lists;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 导出pdf文件
 * 
 * @author 20150205
 * 
 */
public class ExportPdf {

	private static Logger log = LoggerFactory.getLogger(ExportPdf.class);

	public BaseFont bfChinese;
	public Font fontChinese;

	private Document document;
	private PdfPTable table;
	private Paragraph toptile;

	/**
	 * 注解列表（Object[]{ ExcelField, Field/Method }）
	 */
	List<Object[]> annotationList = Lists.newArrayList();

	public ExportPdf() {
		document = new Document();
	}

	public ExportPdf(String filetitle, Class<?> cls, String[] exportFields) throws DocumentException, IOException {
		this(filetitle, cls, 1, exportFields);
	}

	public ExportPdf(String filetitle, Class<?> cls, int type, String[] exportFields, int... groups) throws DocumentException, IOException {
		List<String> headerList = ExportUtils.getHeaderList(annotationList, filetitle, cls, type, exportFields);
		initialize(filetitle, headerList);
	}

	/**
	 * 初始化函数
	 * 
	 * @param title
	 *            表格标题，传“空值”，表示无标题
	 * @param headerList
	 *            表头列表
	 * @throws DocumentException
	 * @throws IOException
	 */
	private void initialize(String title, List<String> headerList) throws DocumentException, IOException {
		this.bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		this.fontChinese = new Font(bfChinese, 12, Font.NORMAL);
		// iText 处理中文
		this.document = new Document();
		// 设置文档的列数
		this.table = new PdfPTable(headerList.size());
		// Create title
		if (StringUtils.isNotBlank(title)) {
			this.toptile = new Paragraph(title, new Font(bfChinese, 14, Font.NORMAL));
			toptile.setAlignment(Paragraph.ALIGN_CENTER);
			toptile.add(new Paragraph("   "));
		}
		
		if (headerList == null) {
			throw new RuntimeException("headerList can not null!");
		}
		// 添加标题行
		for (int i = 0; i < headerList.size(); i++) {
			addCell(this.table, headerList.get(i), fontChinese);
		}
		log.debug("Initialize success.");
	}

	/**
	 * table添加一个单元格
	 * @param table
	 * @param content
	 * @param font
	 */
	private static void addCell(PdfPTable table, String content, Font font){
		PdfPCell cell = new PdfPCell();
		Chunk chunk = new Chunk(content, font);
		Paragraph para = new Paragraph(chunk);
        cell.setPhrase(para);
		//水平居中
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		//垂直居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell);
	}
	
	/**
	 * 添加数据（通过annotation.ExportField添加数据）
	 * 
	 * @return list 数据列表
	 * @throws DocumentException
	 */
	public <E> void setDataList(List<E> list) throws DocumentException {
		for (E e : list) {
			StringBuilder sb = new StringBuilder();
			for (Object[] os : annotationList) {
				ExcelField ef = (ExcelField) os[0];
				Object val = null;
				// Get entity value
				try {
					if (StringUtils.isNotBlank(ef.value())) {
						val = Reflections.invokeGetter(e, ef.value());
					} else {
						if (os[1] instanceof Field) {
							//注解是在属性上，直接获取属性值，如果是对象类型，就会调用该对象的toString方法。
							val = Reflections.invokeGetter(e, ((Field) os[1]).getName());
						} else if (os[1] instanceof Method) {
							//注解是在方法（get）上，获取并调用方法对象
							val = Reflections.invokeMethod(e, ((Method) os[1]).getName(), new Class[] {}, new Object[] {});
						}
					}
					// 注解上有dictType属性
					if (StringUtils.isNotBlank(ef.dictType())) {
						val = DictUtils.getDictLabel(val == null ? "" : val.toString(), ef.dictType(), "");
					}
				} catch (Exception ex) {
					// Failure to ignore
					log.info(ex.toString());
					val = "";
				}
				addCell(this.table, ExportUtils.getContent(val, ef.fieldType()), fontChinese);
				sb.append(val + ", ");
			}
			log.debug("Write success: " + sb.toString());
		}
	}

	/**
	 * 导出
	 * 
	 * @param response
	 * @param fileName
	 *            文件名称
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void write(HttpServletResponse response, String fileName) throws DocumentException, IOException {
		response.setContentType("application/pdf; charset=ISO-8859-1");
		response.setHeader("Content-Disposition", "inline; filename=" + new String(fileName.getBytes(), "iso8859-1"));
		PdfWriter.getInstance(document, response.getOutputStream());

		this.document.open();
		this.document.add(this.toptile);
		this.document.add(this.table);
		document.close();
	}


}
