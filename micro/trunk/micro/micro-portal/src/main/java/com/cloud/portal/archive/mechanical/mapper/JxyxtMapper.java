package com.cloud.portal.archive.mechanical.mapper;

import com.cloud.portal.archive.common.base.mapper.DataMapper;
import com.cloud.portal.archive.mechanical.model.Jxyxt;
import org.apache.ibatis.annotations.Param;

/**
 * @author lvlinc
 * @date Created in 2019/5/9 17:34
 * @description: 机修业企业信息
 * @modified By:lvlinc
 */
public interface JxyxtMapper extends DataMapper<Jxyxt> {
     /**
     * 通过ID查询单位的详细信息
     * @param zagldwbm
     * @return 单位详细信息
     */
     Jxyxt getById(@Param("zagldwbm")String zagldwbm);
}
