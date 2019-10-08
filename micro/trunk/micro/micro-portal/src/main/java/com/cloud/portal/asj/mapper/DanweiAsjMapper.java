package com.cloud.portal.asj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.asj.model.DanweiAsj;
import org.apache.ibatis.annotations.Param;

/**
 * @author lvlinc
 * @date Created in 2019/8/2210:36
 * @description:
 * @modified By:lvlinc
 */
public interface DanweiAsjMapper extends BaseMapper<DanweiAsj> {
    /**
     * fetch data by rule id
     *
     * @param page page number
     * @param danweiAsj json format context
     * @return IPage<DanweiAsj>
     */
    IPage<DanweiAsj> findPage (IPage<DanweiAsj> page,@Param("query")DanweiAsj danweiAsj);
}
