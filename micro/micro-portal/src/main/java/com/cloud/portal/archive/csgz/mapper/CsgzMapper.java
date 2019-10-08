package com.cloud.portal.archive.csgz.mapper;

import com.cloud.portal.archive.common.base.mapper.DataMapper;
import com.cloud.portal.archive.csgz.model.Csgz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lvlinc
 * @date Created in 2019/5/22 10:22:00
 * @description: 特行场所关注信息
 * @modified By:lvlinc
 */
@Mapper
public interface CsgzMapper extends DataMapper<Csgz> {
    /**
     * 添加场所关注
     * @param csgz
     * @return
     */
    boolean savecsgz(Csgz csgz);
    /**
     * 是否场所关注
     * @param csgz
     * @return
     */
    int getis (Csgz csgz);
    /**
     * 取消场所关注
     * @param csdwbh
     * @param userid
     * @return
     */
    boolean remove(@Param("csdwbh")String csdwbh, @Param("userid")String userid);
}
