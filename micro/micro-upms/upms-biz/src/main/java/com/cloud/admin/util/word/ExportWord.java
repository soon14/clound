package com.cloud.admin.util.word;

import com.google.common.collect.Lists;
import com.itextpdf.text.DocumentException;
import com.cloud.admin.util.ExportUtils;
import com.cloud.admin.util.Reflections;
import com.cloud.admin.api.util.excel.annotation.ExcelField;
import com.cloud.admin.util.DictUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.List;

/**
 * word 导出
 * 
 * @author 20150205
 * 
 */
public class ExportWord {

	private static Logger log = LoggerFactory.getLogger(ExportWord.class);

	private XWPFDocument doc;
	private XWPFParagraph xwpf;

	/**
	 * 注解列表（Object[]{ ExcelField, Field/Method }）
	 */
	private List<Object[]> annotationList = Lists.newArrayList();
	private List headerList;

	public ExportWord() {
	}

	public ExportWord(String filetitle, Class<?> cls, String[] exportFields) throws DocumentException, IOException {
		this(filetitle, cls, 1, exportFields);
	}

	public ExportWord(String title, Class<?> cls, int type, String[] exportFields, int... groups) {
		this.headerList = ExportUtils.getHeaderList(annotationList, title, cls, type, exportFields);
		initialize(title);
	}

	private void initialize(String title) {
		this.doc = new XWPFDocument();
		this.xwpf = this.doc.createParagraph();
		// 设置字体对齐方式
		xwpf.setAlignment(ParagraphAlignment.CENTER);
		xwpf.setVerticalAlignment(TextAlignment.TOP);
		// 第一页要使用p1所定义的属性
		XWPFRun r1 = xwpf.createRun();
		// 设置字体是否加粗
		r1.setBold(true);
		r1.setFontSize(20);
		// 设置使用何种字体
		r1.setFontFamily("Courier");
		// 设置上下两行之间的间距
		r1.setTextPosition(20);
		r1.setText(title);
	}
	
	/**
	 * table添加一个单元格
	 * @param cell
	 * @param content
	 */
	private static void addCell(XWPFTableCell  cell , String content ){
		XWPFParagraph newPara = new XWPFParagraph(cell.getCTTc().addNewP(), cell);
	  	XWPFRun run=newPara.createRun();
	  	//内容居中显示
     	newPara.setAlignment(ParagraphAlignment.CENTER);
     	run.setText(content);
	}

	public <E> void setDataList(List<E> list) {
		XWPFTable table = this.doc.createTable(list.size() + 1, this.headerList.size());
		CTTblPr tblPr = table.getCTTbl().getTblPr();
		tblPr.getTblW().setType(STTblWidth.DXA);
		tblPr.getTblW().setW(new BigInteger("8000"));
		table.setCellMargins(20, 20, 20, 20);
		List<XWPFTableCell> tableCells = table.getRow(0).getTableCells();
		for (int i = 0; i < headerList.size(); i++) {
			// 设置标题
			addCell(tableCells.get(i),headerList.get(i).toString());
		}
		int j = 1;
		for (E e : list) {
			int colunm = 0;
			StringBuilder sb = new StringBuilder();
			tableCells = table.getRow(j++).getTableCells();
			System.out.println(j);
			for (Object[] os : annotationList) {

				ExcelField ef = (ExcelField) os[0];
				Object val = null;
				// Get entity value
				try {
					if (StringUtils.isNotBlank(ef.value())) {
						val = Reflections.invokeGetter(e, ef.value());
					} else {
						if (os[1] instanceof Field) {
							val = Reflections.invokeGetter(e, ((Field) os[1]).getName());
						} else if (os[1] instanceof Method) {
							val = Reflections.invokeMethod(e, ((Method) os[1]).getName(), new Class[] {}, new Object[] {});
						}
					}
					// If is dict, get dict label
					if (StringUtils.isNotBlank(ef.dictType())) {
						val = DictUtils.getDictLabel(val == null ? "" : val.toString(), ef.dictType(), "");
					}
				} catch (Exception ex) {
					// Failure to ignore
					log.info(ex.toString());
					val = "";
				}
				addCell(tableCells.get(colunm++),ExportUtils.getContent(val, ef.fieldType()));
				sb.append(val + ", ");
			}
			log.debug("Write success:  " + sb.toString());
		}
	}

	/**
	 * 输出到客户端
	 * 
	 * @param fileName
	 *            输出文件名
	 * @throws IOException
	 */
	public void write(HttpServletResponse response, String fileName) throws IOException {
		response.reset();
		response.setContentType("application/x-msdownloadoctet-stream;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(fileName, "UTF-8"));
		OutputStream out = response.getOutputStream();
		this.doc.write(out);
		out.flush();
		out.close();
	}

}
