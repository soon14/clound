package com.cloud.admin.util.txt;

import com.google.common.collect.Lists;
import com.itextpdf.text.DocumentException;
import com.cloud.admin.util.ExportUtils;
import com.cloud.admin.util.Reflections;
import com.cloud.admin.api.util.excel.annotation.ExcelField;
import com.cloud.admin.util.DictUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.List;

/**
 * TXT文件导出
 * 
 * @author 20150205
 * 
 */
public class ExportTxt {
	private static Logger log = LoggerFactory.getLogger(ExportTxt.class);

	/**
	 * 注解列表（Object[]{ ExcelField, Field/Method }）
	 */
	List<Object[]> annotationList = Lists.newArrayList();
	List<String> headerList;

	public ExportTxt() {
	}

	public ExportTxt(String filetitle, Class<?> cls, String[] exportFields) throws DocumentException, IOException {
		this(filetitle, cls, 1, exportFields);
	}

	public ExportTxt(String filetitle, Class<?> cls, int type, String[] exportFields, int... groups) {
		this.headerList = ExportUtils.getHeaderList(annotationList, filetitle, cls, type, exportFields);
	}

	/**
	 * 文件导出
	 * 
	 * @param response
	 * @param dataList
	 *            数据列表
	 * @param fileName
	 *            文件名称
	 * @throws DocumentException
	 * @throws IOException
	 */
	public <E> void write(HttpServletResponse response, List<E> dataList, String fileName) throws DocumentException, IOException {
		response.setContentType("text/plain");
		response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
		BufferedOutputStream outbuffer = null;
		StringBuffer buffer = new StringBuffer();
		ServletOutputStream output = null;
		try {
			output = response.getOutputStream(); 
			outbuffer = new BufferedOutputStream(output);
			buffer.append(getFormateString(fileName) + "\r\n");
			// 写入文件头部
			buffer.append(getRowContent(this.headerList));
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
					buffer.append(getFormateString(ExportUtils.getContent(val, ef.fieldType())).toCharArray());
					sb.append(val + ", ");
				}
				buffer.append("\r\n");
				log.info("Write success: " + sb.toString());
			}
			outbuffer.write(buffer.toString().getBytes("UTF-8"));
			outbuffer.flush();
			outbuffer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 设置字符格式
	 * @param data
	 * @return
	 */
	public static String getFormateString(String data) {
		return String.format("%-20s", data);
	}

	/**
	 * 写一行数据方法
	 * 
	 * @param row
	 * @throws IOException
	 */
	private static String getRowContent(List<String> row) {
		// 写入文件头部
		StringBuffer sb = new StringBuffer();
		for (String data : row) {
			sb.append(getFormateString(data));
		}
		sb.append("\r\n");
		return sb.toString();
	}
}
