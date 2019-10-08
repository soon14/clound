package com.cloud.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.admin.api.entity.SysFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author wuxx
 * @date 2019/3/4 9:59
 * @description: 文件service接口
 * @modified By:
 **/
public interface SysFileService extends IService<SysFile> {

    /**
     * 文件上传
     * @param multipartRequest 带有文件流的request
     * @param sysFile 文件属性
     * @return 上传结果
     * @throws IOException
     */
    Map<String, Object> upload(MultipartHttpServletRequest multipartRequest, SysFile sysFile) throws Exception;

    /**
     * 根据buket名称和文件名称获取文件流
     * @param bucketName buket 名称
     * @param fileName 文件名称
     * @return
     */
    InputStream getInputStream(String bucketName, String fileName);
}