package com.cloud.portal.archive.kyzy.mapper;

import com.cloud.portal.archive.common.base.mapper.DataMapper;
import com.cloud.portal.archive.kyzy.model.SealInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @author hyx
 * @date Created in 2019/5/9 19:05
 * @description: 刻印章业-印章表mapper层
 * @modified By:
 */
public interface SealInfoMapper extends DataMapper<SealInfo> {

    /**
     * 根据id查询印章
     * @param id
     * @return
     */
    public SealInfo findById(@Param("id")String id);
}
