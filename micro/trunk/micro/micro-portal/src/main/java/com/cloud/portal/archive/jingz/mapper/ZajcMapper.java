package com.cloud.portal.archive.jingz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.archive.jingz.model.Zajc;
import com.cloud.portal.archive.jingz.model.ZajcConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author ryt
 * @date Created in 2019/6/15 11:11
 * @description: 警综治安检查（日常检查）信息
 * @modified By:
 */
public interface ZajcMapper extends BaseMapper<Zajc> {

    /**
     * 获取治安检查信息列表
     * @param page
     * @param zajc
     * @return
     */
     IPage<Zajc> findZajcPage(IPage<Zajc> page, @Param("query")Zajc zajc);

    /**
     * 通过ID查询检查详细信息
     *
     * @param systemId
     * @return 检查详细信息
     */
    Zajc getZajcById(@Param("systemId") String systemId);

    /**
     * 获取治安检查项内容
     * @param systemId
     * @return
     */
    List<ZajcConfig> getZajcConfig(@Param("systemId") String systemId);
}
