package com.cloud.portal.archive.jingz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.archive.jingz.model.PhotoEntity;
import com.cloud.portal.archive.jingz.model.PlaceFile;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author wengshij
 * @date Created in 2019/5/22 9:11
 * @description: 警综管辖单位附件信息
 * @modified By:wengshij
 */
public interface PlaceFileService extends IService<PlaceFile> {

    /**
     *  警综治安管辖单位从业人员信息分页
     *  根据当前单位主键ID 获取当前警钟治安管辖单位附件列表
     *  对应从主表编号（zbbh）
     * @param page
     * @param placeFile
     * @return
     */
    IPage<List<PlaceFile>> findPage(Page page, @Param("query") PlaceFile placeFile);

    /**
     * 根据主键ID 获取当前文件
     * @param systemId
     * @return
     */
    PlaceFile getFileBySystemId(String systemId);

    /***
     * 根据特行场所主键 获取该场所下所有的附件信息
     * @param bh
     * @return
     */
    List<PlaceFile> findListByBh(String bh);

    /**
     * 根据主键下载文件
     *
     * @param file
     * @param response
     */
     void downLoad(byte [] file, HttpServletResponse response);

    /**
     * 获取一张照片信息 （从业人员照片 或者治安管辖单位照片信息）
     * 来源于 B_RYZP信息
     * @param photoEntity
     * @return
     */
     PhotoEntity getOnePhoto( @Param("query") PhotoEntity photoEntity);



}
