package com.cloud.portal.archive.ylcs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.archive.common.base.mapper.DataMapper;
import com.cloud.portal.archive.ylcs.entity.Baxj;
import org.apache.ibatis.annotations.Param;


/**
 * @author ryt
 * @date Created in 2019/5/20 10:00
 * @description: 娱乐场所-保安巡检mapper层
 * @modified By:
 */
public interface BaxjMapper extends BaseMapper<Baxj> {

    /**
     * 获取保安巡检信息列表
     * @param page
     * @param baxj
     * @return
     */
    IPage<Baxj> findBaxjPage(IPage<Baxj> page, @Param("query") Baxj baxj);


}
