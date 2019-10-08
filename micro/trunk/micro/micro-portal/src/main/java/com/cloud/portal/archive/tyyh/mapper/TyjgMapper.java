package com.cloud.portal.archive.tyyh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.portal.archive.common.base.mapper.DataMapper;
import com.cloud.portal.archive.dict.model.Dict;
import com.cloud.portal.archive.tyyh.model.Tyjg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lvlinc
 * @date Created in 2019/6/10
 * @description:
 * @modified By:lvlinc
 */
public interface TyjgMapper extends BaseMapper<Tyjg> {
    /**
     * 获取字典名称
     * @param tyjg
     * @return List<Tyjg>
     */
    List<Tyjg> getTyjg (@Param("query") Tyjg tyjg);
}
