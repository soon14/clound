

package com.cloud.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.api.entity.SysLog;
import com.cloud.admin.api.vo.PreLogVo;
import com.cloud.admin.service.SysLogService;
import com.cloud.admin.util.ExportUtils;
import com.cloud.common.core.util.R;
import com.cloud.common.security.annotation.Inner;
import com.itextpdf.text.DocumentException;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 日志表 前端控制器
 * </p>
 *
 * @author ygnet
 * @since 2017-11-20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/log")
@Api(value = "log", description = "日志管理模块")
public class LogController {
	private final SysLogService sysLogService;

	/**
	 * 简单分页查询
	 *
	 * @param page   分页对象
	 * @param sysLog 系统日志
	 * @return
	 */
	@GetMapping("/page")
	public R getLogPage(Page page, SysLog sysLog) {
		return new R<>(sysLogService.findLogPage(page, sysLog));
	}

	/**
	 * 删除日志
	 *
	 * @param id ID
	 * @return success/false
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_log_del')")
	public R removeById(@PathVariable String id) {
		return new R<>(sysLogService.removeById(id));
	}

	/**
	 * 插入日志
	 *
	 * @param sysLog 日志实体
	 * @return success/false
	 */
	@Inner
	@PostMapping("/save")
	public R save(@Valid @RequestBody SysLog sysLog) {
		return new R<>(sysLogService.save(sysLog));
	}

	/**
	 * 批量插入前端异常日志
	 *
	 * @param preLogVoList 日志实体
	 * @return success/false
	 */
	@PostMapping("/logs")
	public R saveBatchLogs(@RequestBody List<PreLogVo> preLogVoList) {
		return new R<>(sysLogService.saveBatchLogs(preLogVoList));
	}

	/**
	 * 数据导出
	 * @param sysLog
	 * @param exportfields 需要导出的属性
	 * @param exportftype 导出的类型
	 * @param page  分页信息，传递导出的记录数
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "export", method = RequestMethod.GET)
	@ResponseBody
	public void logexport(SysLog sysLog,
						  @RequestParam(value = "exportfields", required = true) String exportfields,
						  @RequestParam(value = "exportftype", required = true) int exportftype,
						  @RequestParam(value = "filetitle", required = true) String filetitle,
						  Page page, HttpServletResponse response)
			throws IOException, DocumentException {
		String[] fields = exportfields.trim().split(",");
		IPage<SysLog> data = sysLogService.page(page, Wrappers.query(sysLog));
		ExportUtils.doExport(data.getRecords(), SysLog.class, fields, filetitle, exportftype, response);
	}
}
