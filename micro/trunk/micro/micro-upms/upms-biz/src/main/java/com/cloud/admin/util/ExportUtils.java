package com.cloud.admin.util;

import com.cloud.admin.api.annotation.ExcelField;
import com.cloud.admin.util.excel.ExportExcel;
import com.google.common.collect.Lists;
import com.itextpdf.text.DocumentException;
import com.cloud.admin.util.pdf.ExportPdf;
import com.cloud.admin.util.txt.ExportTxt;
import com.cloud.admin.util.vsc.ExportCsv;
import com.cloud.admin.util.word.ExportWord;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 导出帮助类
 * 
 * @author 20150205
 * 
 */
public class ExportUtils {

	public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private static Logger log = LoggerFactory.getLogger(ExportUtils.class);

	public static final int EXPORT_FILE_TYPE_EXCEL = 1;
	public static final int EXPORT_FILE_TYPE_PDF = 2;
	public static final int EXPORT_FILE_TYPE_CSV = 3;
	public static final int EXPORT_FILE_TYPE_WORLD = 4;
	public static final int EXPORT_FILE_TYPE_TXT = 5;

	public static String getContent(Object val, Class<?> fieldType) {
		String value = "";
		try {
			if (val == null) {
				 
			}else if (val instanceof String) {
				value =  (String) val;
			}  else if (val instanceof Date) {
				value = format.format((Date) val);
			} else if (val instanceof Integer) {
				value =  String.valueOf( val);
			} else if (val instanceof Long) {
				value = String.valueOf( val);
			} else if (val instanceof Double) {
				value = String.valueOf( val);
			} else if (val instanceof Float) {
				value = String.valueOf( val);
			} else {
				if (fieldType != Class.class) {
					value =  (String) fieldType.getMethod("setValue", Object.class).invoke(null, val);
				} else {//对于一对多的情况下，获取处理后的值
					System.out.println(ExportExcel.class.getName().replaceAll(ExportExcel.class.getSimpleName(),
							"fieldtype." + val.getClass().getSimpleName() + "Type"));
					value =  (String) Class.forName(ExportExcel.class.getName().replaceAll(ExportExcel.class.getSimpleName(),
							"fieldtype." + val.getClass().getSimpleName() + "Type")).getMethod("setValue", Object.class).invoke(null, val);
				}
			}
		}catch (Exception ex) {
			log.info("Set cell value ["+value+"] error: " + ex.toString());
		}
		return value;
	}

	public static List<String> getHeaderList(List<Object[]> annotationList, String filetitle, Class<?> cls, int type, String[] exportFields) {
		Field[] fs = cls.getDeclaredFields();
		List<String> tags = new ArrayList<String>(exportFields.length);
		//传入需要导出的字段
		tags.addAll(Arrays.asList(exportFields));
		//记录已经放入annotationList中的属性
		List<String> refield = new ArrayList<String>();
		for (Field f : fs) {
			// 获取配置的需要导出的属性的ExcelField annotation 列表并保存
			for (String expfield : tags) {
				if (f.getName().equals(expfield)) {
					// 当前的字段需要导出 company.name
					// 当前属性的ExcelField annotation
					ExcelField ef = f.getAnnotation(ExcelField.class);
					// 存在ExcelField且type为0或1
					boolean existsExcelFieldType = ef != null && (ef.type() == 0 || ef.type() == type);
					if (existsExcelFieldType) {
						// 将注解对象、field对象保存
						annotationList.add(new Object[] { ef, f });
						refield.add(expfield);
					}
				}
			}
		}
		tags.removeAll(refield);
		if (tags.size() != 0) {
			// 需要导出的字段的注解，有一部分没有找到，需要在方法上继续寻找
			// 获取方法上的注解
			Method[] ms = cls.getDeclaredMethods();
			for (Method m : ms) {
				for (String expfield : tags) {
					// 该方法的get、set方法存在注解
					if (m.getName().equalsIgnoreCase("get" + expfield) || m.getName().equalsIgnoreCase("set" + expfield)) {
						ExcelField ef = m.getAnnotation(ExcelField.class);
						boolean existsExcelFieldType = ef != null && (ef.type() == 0 || ef.type() == type);
						if (existsExcelFieldType) {
								annotationList.add(new Object[] { ef, m });
						}
					}
				}
			}
		}
		// 属性排序
		Collections.sort(annotationList, new Comparator<Object[]>() {
			@Override
			public int compare(Object[] o1, Object[] o2) {
				return new Integer(((ExcelField) o1[0]).sort()).compareTo(new Integer(((ExcelField) o2[0]).sort()));
			};
		});
		// 初始化
		List<String> headerList = Lists.newArrayList();
		for (Object[] os : annotationList) {
			String t = ((ExcelField) os[0]).title();
			// 如果是导出，则去掉注释
			if (type == 1) {
				String[] ss = StringUtils.split(t, "**", 2);
				if (ss.length == 2) {
					t = ss[0];
				}
			}
			headerList.add(t);
		}
		return headerList;
	}

	/**
	 * 
	 * @param data
	 *            需要导出的数据
	 * @param clazz
	 *            导出的实体Class
	 * @param fields
	 *            需要导出的属性
	 * @param filetitle
	 *            文件标题
	 * @param exportftype
	 *            导出的文件类型
	 * @param response
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static <T> void doExport(List<T> data, Class<T> clazz, String[] fields, String filetitle, int exportftype, HttpServletResponse response)
			throws IOException, DocumentException {
		if (exportftype == EXPORT_FILE_TYPE_EXCEL) {
			// excel
			ExportExcel excel = new ExportExcel(filetitle, clazz, fields);
			excel.setDataList(data);
			log.info(data.toString());
			String fileName = filetitle + ".xlsx";
			try {
				excel.write(response, fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (exportftype == EXPORT_FILE_TYPE_PDF) {
			// pdf
			String fileName = filetitle + ".pdf";
			try {
				ExportPdf expdf = new ExportPdf(filetitle, clazz, fields);
				expdf.setDataList(data);
				expdf.write(response, fileName);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		} else if (exportftype == EXPORT_FILE_TYPE_CSV) {
			// csv
			String fileName = filetitle + ".csv";
			ExportCsv csvdf = new ExportCsv(filetitle, clazz, fields);
			csvdf.write(response, data, fileName);
		} else if (exportftype == EXPORT_FILE_TYPE_WORLD) {
			// world
			String fileName = filetitle + ".docx";
			ExportWord wordexport = new ExportWord(filetitle, clazz, fields);
			wordexport.setDataList(data);
			wordexport.write(response, fileName);
		} else if (exportftype == EXPORT_FILE_TYPE_TXT) {
			// txt
			String fileName = filetitle + ".txt";
			ExportTxt txtdf = new ExportTxt(filetitle, clazz, fields);
			txtdf.write(response, data, fileName);
		}
	}


	// public static void main(String[] args) {
	// (String)OaNotify.classgetMethod("getValue", Object.class).invoke(null,
	// val)
	// (String)OaNotify.class.getMethod("getValue", Object.class).invoke(null,
	// val)
	// for (Field field : getAllField(OaNotify.class))
	// System.out.println(field);
	// }
}
