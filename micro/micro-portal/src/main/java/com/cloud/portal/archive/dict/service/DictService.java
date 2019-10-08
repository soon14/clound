package com.cloud.portal.archive.dict.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.archive.dict.model.Dict;

import java.util.List;

/**
 * @author lvlinc
 * @date Created in 2019-5-27
 * @description: 字典
 * @modified By:lvlinc
 */
public interface DictService extends IService<Dict> {

    /**
     * 分页查询字典
     *
     * @param  iPage
     * @param dict
     * @return Dict
     */
    IPage<Dict> findDict(IPage<Dict> iPage, Dict dict);
    /**
     * 获取字典名称
     * @param codetype
     * @param lyxt
     * @return  List<Dict>
     */
    List<Dict> getDict (String codetype,String lyxt);
    /**
     * 获取字典名称
     * @param lyxt
     * @param codetype
     *  @param code
     * @return String
     */
    String getDictLabel (String codetype,String code,String lyxt);
}
