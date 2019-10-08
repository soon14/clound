package com.cloud.portal.archive.ylcs.mapper;

import com.cloud.portal.archive.common.base.mapper.DataMapper;
import com.cloud.portal.archive.ylcs.entity.Csba;
import org.apache.ibatis.annotations.Param;


/**
 * @author ryt
 * @date Created in 2019/5/9 19:05
 * @description: 娱乐场所-场所备案mapper层
 * @modified By:
 */
public interface CsbaMapper extends DataMapper<Csba> {

    /**
     * 通过ID查询单位的详细信息
     *
     * @param csbaId
     * @return 单位详细信息
     */
    Csba getById(@Param("csbaId")String csbaId);


}
