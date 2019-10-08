package com.cloud.portal.archive.csycjg.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.portal.archive.common.base.mapper.DataMapper;
import com.cloud.portal.archive.csycjg.model.CsYcjg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lvlinc
 * @date Created in 2019/6/5
 * @description:
 * @modified By:lvlinc
 */
@Mapper
public interface CsYcjgMapper extends DataMapper<CsYcjg> {
             /**
             * 分页查询数据表字段
             * @param page    参数集
             * @param csYcjg 查询参数列表
             * @return IPage<CsYcjg> 数据表集合
             */
             IPage<CsYcjg> findPage(Page<CsYcjg> page, @Param("query")CsYcjg csYcjg);

}
