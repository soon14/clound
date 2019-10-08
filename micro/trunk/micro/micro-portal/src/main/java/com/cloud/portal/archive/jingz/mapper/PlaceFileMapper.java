package com.cloud.portal.archive.jingz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.portal.archive.jingz.model.EmployeeJz;
import com.cloud.portal.archive.jingz.model.PhotoEntity;
import com.cloud.portal.archive.jingz.model.PlaceFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wengshij
 * @date Created in 2019/6/17 10:37
 * @description: 警综特行场所文件信息
 * @modified By:wengshij
 */
public interface PlaceFileMapper extends BaseMapper<PlaceFile> {

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

    /**
     * 获取附件内容信息
     * @param placeFile
     * @return
     */
    List<PlaceFile> findList(@Param("query") PlaceFile placeFile);

    /**
     * 获取一张照片信息 （从业人员照片 或者治安管辖单位照片信息）
     * 来源于 B_RYZP信息
     * @param photoEntity
     * @return
     */
    PhotoEntity getOnePhoto(@Param("query") PhotoEntity photoEntity);

}
