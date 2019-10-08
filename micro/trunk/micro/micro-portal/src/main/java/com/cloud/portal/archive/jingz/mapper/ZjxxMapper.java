package com.cloud.portal.archive.jingz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.portal.archive.jingz.model.Zjxx;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author llc
 * @date Created in 2019/5/7 11:11
 * @description:警综 单位证件信息
 * @modified By:llc
 */
public interface ZjxxMapper extends BaseMapper<Zjxx> {

    /**
     *  警综治安管辖单位证件信息分页
     * @param page
     * @param zjxx
     * @return
     */
    IPage<List<Zjxx>> findPage(Page page, @Param("query") Zjxx zjxx);

    /**
     * 获取照片信息
     * @param fileid 主键ID
     * @return
     */
    Zjxx imgById(@Param("fileid") String fileid);
}
