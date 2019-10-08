package com.cloud.portal.archive.kyzy.mapper;

import com.cloud.portal.archive.common.base.mapper.DataMapper;
import com.cloud.portal.archive.kyzy.model.ProduceUnit;
import org.apache.ibatis.annotations.Param;


/**
 * @author hyx
 * @date Created in 2019/5/9 19:05
 * @description: 刻印章业-单位表mapper层
 * @modified By:
 */
public interface ProduceUnitMapper extends DataMapper<ProduceUnit> {

    /**
     * 根据id查询刻印章单位
     * @param id
     * @return
     */
    public ProduceUnit findById(@Param("id")String id);
}
