package com.cloud.admin.controller;

import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.api.entity.SysFile;
import com.cloud.admin.service.SysFileService;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Map;

/**
 * @author ygnet
 * @date 2018-12-30
 * <p>
 * 文件管理
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/file")
public class FileController {

	private final SysFileService sysFileService;

	public static final DecimalFormat FILE_SIZE_FORMAT = new DecimalFormat("###.##");

	/**
	 * 分页查询字典类型信息
	 *
	 * @param page 分页对象
	 * @return 分页对象
	 */
	@GetMapping("/page")
	public R<IPage> getFilePage(Page page, SysFile sysFile) {
		return new R<>(sysFileService.page(page, Wrappers.query(sysFile)));
	}

	/**
	 * 上传文件
	 * 文件名采用uuid,避免原始文件名中带"-"符号导致下载的时候解析出现异常
	 * @param sysFile
	 * @param multipartRequest 资源
	 * @return R(bucketName, filename)
	 */
	@PostMapping("/upload")
	public R upload(SysFile sysFile, MultipartHttpServletRequest multipartRequest){
		MultipartFile multipartFile = multipartRequest.getFile("file");
		String fileName = multipartFile.getOriginalFilename();
		sysFile.setFileName(fileName.substring(0, fileName.lastIndexOf(".")));
		sysFile.setExtName(fileName.substring(fileName.lastIndexOf(".")));

		if(StringUtils.isBlank(sysFile.getTbName())){
			sysFile.setTbName(SysFile.class.getSimpleName());
		}

		float fileSize = multipartFile.getSize() / 1024 == 0 ? Float
				.parseFloat(FILE_SIZE_FORMAT.format((float) multipartFile.getSize() / 1024))
				: multipartFile.getSize() / 1024;
		sysFile.setFileSize(fileSize);
		Map<String, Object> result = null;
		try {
			result = sysFileService.upload(multipartRequest, sysFile);
			return R.builder().data(result).build();
		} catch (Exception e) {
			e.printStackTrace();
			return R.builder().data(e.getMessage()).build();
		}
	}

	/**
	 * 获取文件
	 *
	 * @param id 文件id
	 * @param response
	 * @return
	 */
	@GetMapping("/{id}")
	public void download(@PathVariable String id, HttpServletResponse response) {
		try {
			SysFile sysFile = this.sysFileService.getById(id);
			InputStream in = null;
			if(null!=sysFile&&StringUtils.isNotBlank(sysFile.getBucketName())){
				in = sysFileService.getInputStream(sysFile.getBucketName(), sysFile.getFileName());
			}else {
				in = new ByteArrayInputStream(sysFile.getFiles());
			}
			response.setContentType("application/octet-stream; charset=UTF-8");
			IoUtil.copy(in, response.getOutputStream());
		} catch (Exception e) {
			log.error("文件读取异常", e);
		}
	}

	/**
	 * 获取列表
	 * @param sysFile
	 * @return
	 */
	@GetMapping("/list")
	public R getFilePage(SysFile sysFile) {
		return new R(sysFileService.list( Wrappers.query(sysFile)));
	}

	/**
	 * 删除字典字典类型，并且清除字典缓存
	 *
	 * @param id   ID
	 * @return R
	 */
	@SysLog("删除字典类型")
	@DeleteMapping("/{id}")
	public R removeById(@PathVariable String id) {
		return new R<>(sysFileService.removeById(id));
	}

}
