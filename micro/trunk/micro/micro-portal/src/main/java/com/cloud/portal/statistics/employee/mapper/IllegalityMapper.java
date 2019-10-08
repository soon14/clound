package com.cloud.portal.statistics.employee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.portal.statistics.employee.model.Illegality;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Molly
 * @date Created in 2019/8/9
 * @description: 统计查询-统计报表-案件信息mapper
 * @modified By: Molly
 */
public interface IllegalityMapper extends BaseMapper<Illegality> {

    /**
     * 根据身份证号获取违法犯罪列表
     * @param zjhm
     * @return
     */
    public List<Illegality> findListByZJHM(@Param("zjhm") String zjhm);

}
