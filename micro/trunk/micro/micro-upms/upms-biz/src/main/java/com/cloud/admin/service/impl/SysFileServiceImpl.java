package com.cloud.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.admin.api.entity.SysFile;
import com.cloud.admin.mapper.SysFileMapper;
import com.cloud.admin.service.SysFileService;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.minio.service.MinioTemplate;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author wuxx
 * @date 2019/3/4 10:06
 * @description: 文件service实现
 * @modified By:
 **/

@Service
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements SysFileService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MinioTemplate minioTemplate;

    /**
     * 文件上传
     *
     * @param multipartRequest 带有文件流的request
     * @param sysFile          文件属性
     * @return 上传结果
     * @throws IOException
     */
    @Override
    public Map<String, Object> upload(MultipartHttpServletRequest multipartRequest, SysFile sysFile) throws Exception {
        Map<String, Object> result = new HashMap<>(2);
        MultipartFile multipartFile = multipartRequest.getFile("file");

        String bucketName = CommonConstants.BUCKET_NAME;
        sysFile.setBucketName(bucketName);

        boolean uploadStatus = true;
        if (SysFile.STORAGE_MODEL_DB.equalsIgnoreCase(sysFile.getStorageModel())) {
            // 存储在数据库中
            try {
                sysFile.setFiles(multipartFile.getBytes());
            } catch (IOException e) {
                uploadStatus = false;
                logger.error("获取文件失败！" + e.getMessage());
            }
        } else if (SysFile.STORAGE_MODEL_DISK.equalsIgnoreCase(sysFile.getStorageModel())) {
            // 存储在ss3
            // uploadStatus = uploadToLocalDisk(multipartFile,  sysFile, tmpName, targetDir);
            uploadStatus = uploadToSS3(bucketName, sysFile.getFileName(), multipartFile);
        } else if (SysFile.STORAGE_MODEL_BOTH.equalsIgnoreCase(sysFile.getStorageModel())) {
            // 存储在数据库和磁盘中
            try {
                sysFile.setFiles(multipartFile.getBytes());
                // uploadStatus = uploadToLocalDisk(multipartFile, sysFile, tmpName, targetDir);
                uploadStatus = uploadToSS3(bucketName, sysFile.getFileName(), multipartFile);
            } catch (IOException e) {
                uploadStatus = false;
                logger.error("获取文件失败！" + e.getMessage());
            } catch (Exception e) {
                uploadStatus = false;
                logger.error("文件上传失败！" + e.getMessage());
            }
        } else { // 默认存储在ss3
            // uploadStatus = uploadToLocalDisk(multipartFile, sysFile, tmpName, targetDir);
            uploadStatus = uploadToSS3(bucketName, sysFile.getFileName(), multipartFile);
        }
        if (uploadStatus) {
            String emptyValue="null";
            if (StringUtils.isNotBlank(sysFile.getId())&&!emptyValue.equalsIgnoreCase(sysFile.getId())) {
                SysFile tempSysFile = this.getById(sysFile.getId());
                if (null == tempSysFile) {
                    uploadStatus = save(sysFile);

                } else {
                    uploadStatus = this.updateById(sysFile);
                }
            } else {
                sysFile.setId(UUID.randomUUID().toString());
                uploadStatus = save(sysFile);
            }
        }
        result.put("sysFile", sysFile);
        result.put("status", uploadStatus);
        return result;
    }

    /**
     * 根据buket名称和文件名称获取文件流
     *
     * @param bucketName buket 名称
     * @param fileName   文件名称
     * @return
     */
    @Override
    public InputStream getInputStream(String bucketName, String fileName) {
        return this.minioTemplate.getObject(bucketName, fileName);
    }

    /**
     * 删除文件
     *
     * @param id
     * @return
     */
    @Override
    public boolean removeById(Serializable id) {
        SysFile sysFile = this.getById(id);
        if (sysFile != null) {
            try {
                if (StringUtils.isNoneBlank(sysFile.getBucketName())) {
                    this.minioTemplate.removeObject(sysFile.getBucketName(), sysFile.getFileName());
                }
            } catch (Exception e) {
                logger.error("从ss3删除文件失败，文件id：" + sysFile.getId() + "异常信息" + e.getMessage());
                return false;
            }

            File file = new File(sysFile.getPath());
            if (file.exists()) {
                file.delete();
            }
            return this.baseMapper.deleteById(id) == 1;
        }
        return true;
    }

    /**
     * @param bucketName
     * @param fileName
     * @param multipartFile
     * @return
     * @throws Exception
     */
    private boolean uploadToSS3(String bucketName, String fileName, MultipartFile multipartFile) throws Exception {
        boolean uploadStatus = true;
        try {
            minioTemplate.putObject(bucketName, fileName, multipartFile.getInputStream());
        } catch (Exception e) {
            uploadStatus = false;
            logger.error("上传失败", e);
            throw new Exception(e);
        }
        return uploadStatus;
    }

    /**
     * 将文件流写入磁盘
     *
     * @param multipartFile
     * @param sysFile
     * @param tmpName
     * @param targetDir
     * @return
     * @throws IOException
     */
    private boolean uploadToLocalDisk(MultipartFile multipartFile, SysFile sysFile, String tmpName, String targetDir) throws IOException {
        boolean isSuccess = true;
        OutputStream out = null;
        InputStream in = null;
        try {
            File dir = new File(targetDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File destFile = new File(targetDir, tmpName);
            out = new BufferedOutputStream(new FileOutputStream(destFile));
            in = multipartFile.getInputStream();
            IOUtils.copy(in, out);
        } catch (IOException e) {
            isSuccess = false;
            throw new IOException(e);
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
            } catch (IOException e) {
            }
        }
        return isSuccess;
    }
}