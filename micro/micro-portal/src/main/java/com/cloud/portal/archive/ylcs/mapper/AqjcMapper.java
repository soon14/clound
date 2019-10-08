package com.cloud.portal.archive.ylcs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.archive.ylcs.entity.Aqjc;
import com.cloud.portal.archive.ylcs.entity.AqjcContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ryt
 * @date Created in 2019/5/21 15:02
 * @description: 娱乐场所-公安日常检查mapper层
 * @modified By:
 */
public interface AqjcMapper extends BaseMapper<Aqjc> {

    /**
     * 获取安全检查信息列表
     * @param page
     * @param aqjc
     * @return
     */
    IPage<Aqjc> findAqjcPage(IPage<Aqjc> page, @Param("query") Aqjc aqjc);

    /**
     * 通过ID查询检查详细信息
     *
     * @param aqjcId
     * @return 检查详细信息
     */
    Aqjc getAqjcById(@Param("aqjcId") String aqjcId);

    /**
     * 获取安全检查项内容
     * @param aqjcId
     * @return
     */
    List<AqjcContent> getAqjcContent(@Param("aqjcId") String aqjcId);
}
