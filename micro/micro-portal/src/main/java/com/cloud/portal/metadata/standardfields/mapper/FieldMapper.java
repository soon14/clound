

package com.cloud.portal.metadata.standardfields.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.metadata.standardfields.entity.Field;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ryt
 * @date Created in 2019/4/9 15:25
 * @description: 标准字段管理 数据表字段mapper接口
 * @modified By:
 */
public interface FieldMapper extends BaseMapper<Field> {

    /**
     * 表字段 列表
     * @param page
     * @param field
     * @return
     */
    IPage<Field> findFieldPage(IPage<Field> page, @Param("queryCondition")Field field);

    /**
     * 表字段 更新
     * @param field
     * @return
     */
    boolean updateFieldById(Field field);

    /**
     * 批量插入数据
     *
     * @param fieldList
     */
    void saveAll(@Param("fieldList") List<Field> fieldList);

    /**
     * 字段预览时候去除已经添加过的字段
     *
     * @param field
     * @return List<Field>
     */
    List<Field> findExistsFieldList(@Param("queryCondition")Field field);
}
