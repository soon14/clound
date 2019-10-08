package com.cloud.portal.archive.jingz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.archive.jingz.model.Xfjc;
import org.apache.ibatis.annotations.Param;



/**
 * @author ryt
 * @date Created in 2019/6/18 17:14
 * @description: 警综消防监督检查信息
 * @modified By:
 */
public interface XfjcMapper extends BaseMapper<Xfjc> {

    /**
     * 获取消防检查信息列表
     * @param page
     * @param xfjc
     * @return
     */
     IPage<Xfjc> findXfjcPage(IPage<Xfjc> page, @Param("query") Xfjc xfjc);

    /**
     * 通过ID查询检查详细信息
     *
     * @param systemId
     * @return 检查详细信息
     */
    Xfjc getXfjcById(@Param("systemId") String systemId);

}
