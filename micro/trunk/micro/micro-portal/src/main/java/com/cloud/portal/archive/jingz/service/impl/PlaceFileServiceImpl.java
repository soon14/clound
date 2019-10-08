package com.cloud.portal.archive.jingz.service.impl;

import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.portal.archive.jingz.mapper.PlaceFileMapper;
import com.cloud.portal.archive.jingz.model.PhotoEntity;
import com.cloud.portal.archive.jingz.model.PlaceFile;
import com.cloud.portal.archive.jingz.service.PlaceFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @author wengshij
 * @date Created in 2019/5/22 9:11
 * @description:警综管辖单位附件信息
 * @modified By:wengshij
 */
@Slf4j
@Service
public class PlaceFileServiceImpl extends ServiceImpl<PlaceFileMapper, PlaceFile> implements PlaceFileService {


    @Override
    public IPage<List<PlaceFile>> findPage(Page page, PlaceFile placeFile) {
        return baseMapper.findPage(page, placeFile);
    }

    @Override
    public PlaceFile getFileBySystemId(String systemId) {
        return baseMapper.getFileBySystemId(systemId);
    }

    @Override
    @CacheEvict(value = "exclusive_places", key = "('bh').concat('_').concat(#bh)")
    public List<PlaceFile> findListByBh(String bh) {
        PlaceFile placeFile = new PlaceFile();
        placeFile.setZbbh(bh);
        return baseMapper.findList(placeFile);
    }

    /**
     * 根据主键下载文件
     *
     * @param file  文件流
     * @param response
     */
    @Override
    public void downLoad(byte[] file, HttpServletResponse response) {
        try {
            InputStream in = null;
            if (null != file && file.length > 0) {
               in = new ByteArrayInputStream(file);
            }
            response.setContentType("application/octet-stream; charset=UTF-8");
            IoUtil.copy(in, response.getOutputStream());
        } catch (Exception e) {
            log.error("获取文件异常", e);
        }
    }

    @Override
    public PhotoEntity getOnePhoto(PhotoEntity photoEntity) {
        return baseMapper.getOnePhoto(photoEntity);
    }
}
