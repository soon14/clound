package com.cloud.portal.archive.jingz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.archive.jingz.model.Zjxx;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lvlinc
 * @date Created in 2019/6/15
 * @description:
 * @modified By:lvlinc
 */
public interface ZjxxService extends IService<Zjxx> {
    /**
     *  警综治安管辖单位证件信息分页
     * @param page
     * @param zjxx
     * @return
     */
    IPage<List<Zjxx>> findPage(Page page,Zjxx zjxx);

    /**
     * 获取照片信息
     * @param fileid 主键ID
     * @return
     */
    Zjxx imgById(String fileid);
}
