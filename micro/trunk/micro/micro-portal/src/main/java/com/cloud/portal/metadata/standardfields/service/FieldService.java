

package com.cloud.portal.metadata.standardfields.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.metadata.standardfields.entity.Field;

import java.util.List;


/**
 * @author ryt
 * @date Created in 2019/4/9 15:25
 * @description: 标准字段管理 数据表字段service
 * @modified By:
 */
public interface FieldService extends IService<Field> {

    /**
     * 查询数据表
     * @param field
     * @return
     */
    IPage<Field> findFieldPage(IPage<Field> page, Field field);

    /**
     * 修改字段
     *
     * @param field 字段
     * @return Boolean
     */
    Boolean updateFieldById(Field field);
    /**
     * 添加字段
     *
     * @param field 字段
     * @return Boolean
     */
    Boolean saveField(Field field);

    /**
     * 预览字段
     *
     * @param field
     * @return Boolean
     */
    List<Field> preField (Field field);

    /**
     * 保存数据表字段
     * @param field
     * @return
     */
    Boolean saveAllField(Field field);

    /**
     * 预览字段保存
     *
     * @param fieldList
     * @return Boolean
     */
    boolean saveAll(List<Field> fieldList);
}
