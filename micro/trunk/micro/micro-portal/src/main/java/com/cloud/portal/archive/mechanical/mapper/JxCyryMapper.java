package com.cloud.portal.archive.mechanical.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.archive.mechanical.model.Cjcl;
import com.cloud.portal.archive.mechanical.model.Cyry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
 * @author lvlinc
 * @date Created in 2019/5/9 17:34
 * @description: 机修从业人员
 * @modified By:lvlinc
 */
@Mapper
public interface JxCyryMapper extends BaseMapper<Cyry> {
    /**
     * 查询列表分页信息
     * @param page
     * @param cyry
     * @return
     */
    IPage<Cyry> findCyryPage(IPage<Cyry> page, @Param("query") Cyry cyry);
    /**
     * 统计在岗人员数量
     * @param cyry
     * @return
     */
    Cyry CountAll(Cyry cyry);
    /**
     * 获取人员详情信息
     * @param xxid
     * @return
     */
    Cyry getById(@Param("xxid")String xxid);

    /**
     * 获取人员照片信息
     * @param id 主键ID
     * @return
     */
    Cyry CyryImgById(@Param("id") String id);

}
