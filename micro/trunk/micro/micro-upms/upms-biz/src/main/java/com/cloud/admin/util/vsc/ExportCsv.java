package com.cloud.admin.util.vsc;

import com.cloud.admin.api.annotation.ExcelField;
import com.google.common.collect.Lists;
import com.itextpdf.text.DocumentException;
import com.cloud.admin.util.ExportUtils;
import com.cloud.admin.util.Reflections;
import com.cloud.admin.util.DictUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.List;

/**
 * 导出 csv
 * 
 * @author 20150205
 * 
 */
public class ExportCsv {
	private static Logger log = LoggerFactory.getLogger(ExportCsv.class);

	/**
	 * 注解列表（Object[]{ ExcelField, Field/Method }）
	 */
	private List<Object[]> annotationList = Lists.newArrayList();
	private List<String> headerList;
	private String filetitle;

	public ExportCsv() {
	}

	public ExportCsv(String filetitle, Class<?> cls, String[] exportFields) throws DocumentException, IOException {
		this(filetitle, cls, 1, exportFields);
	}

	public ExportCsv(String filetitle, Class<?> cls, int type, String[] exportFields, int... groups) {
		this.filetitle = filetitle;
		this.headerList = ExportUtils.getHeaderList(annotationList, filetitle, cls, type, exportFields);
	}

	/**
	 * 文件导出
	 * 
	 * @param response
	 * @param dataList
	 *            数据列表
	 * @param fileName
	 *            要导出的文件名称
	 * @throws DocumentException
	 * @throws IOException
	 */
	public <E> void write(HttpServletResponse response, List<E> dataList, String fileName) throws DocumentException, IOException {
		response.setContentType("application/csv;charset=gb18030");
		response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
		ServletOutputStream out = response.getOutputStream();
		BufferedWriter csvWtriter = null;    
		try {              
			// GB2312使正确读取分隔符","      
			csvWtriter = new BufferedWriter(new OutputStreamWriter(out, "GB2312"), 1024);
			int num = this.headerList.size()/2;
			StringBuffer buffer = new StringBuffer();
			for(int i = 0 ; i < num ; i++){
				buffer.append(" ,");
			}
 			csvWtriter.write(buffer.toString()  + filetitle  + buffer.toString());
			csvWtriter.newLine();
			
			// 写入文件头部
			writeRow(this.headerList, csvWtriter);
			for (E e : dataList) {
				StringBuilder sb = new StringBuilder();
				StringBuffer databuffer = new StringBuffer();
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
					databuffer.append("\"").append(ExportUtils.getContent(val, ef.fieldType())).append(" \",");
					sb.append(val + ", ");
				}
				csvWtriter.write(databuffer.toString());
				csvWtriter.newLine();
				log.debug("Write success: " + sb.toString());
			}
			csvWtriter.flush();
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				csvWtriter.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 写一行数据方法
	 * 
	 * @param row
	 * @param csvWriter
	 * @throws IOException
	 */
	private static void writeRow(List<String> row, BufferedWriter csvWriter) throws IOException {
		// 写入文件头部
		for (String data : row) {
			StringBuffer sb = new StringBuffer();
			String rowStr = sb.append("\"").append(data).append("\",").toString();
			csvWriter.write(rowStr);
		}
		csvWriter.newLine();
	}
}
