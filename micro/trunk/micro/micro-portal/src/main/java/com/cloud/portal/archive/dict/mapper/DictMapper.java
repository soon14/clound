package com.cloud.portal.archive.dict.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.archive.dict.model.Dict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lvlinc
 * @date Created in 2019/5/27
 * @description: 字典
 * @modified By:lvlinc
 */
@Mapper
public interface DictMapper extends BaseMapper<Dict> {
    /**
     * 分页查询数据表字段
     * @param iPage    参数集
     * @param dict 查询参数列表
     * @return Dict 数据表集合
     */
    IPage<Dict> findPage(IPage<Dict> iPage, @Param("query")Dict dict);
    /**
     * 获取字典名称
     * @param lyxt
     * @param codetype
     * @return List<Dict>
     */
    List<Dict> getDict (@Param("codetype")String codetype,@Param("lyxt")String lyxt);
    /**
     * 获取字典名称
     * @param lyxt
     * @param codetype
     * @return String
     */
    String getDictLabel (String codetype,String code,String lyxt);
}
