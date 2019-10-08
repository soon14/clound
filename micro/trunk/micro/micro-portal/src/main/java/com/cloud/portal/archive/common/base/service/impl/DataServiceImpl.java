package com.cloud.portal.archive.common.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.portal.archive.common.base.mapper.DataMapper;
import com.cloud.portal.archive.common.base.model.DataModel;
import com.cloud.portal.archive.common.base.service.DataService;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * @author wengshij
 * @date Created in 2019/5/5 10:15
 * @description:
 * @modified By:wengshij
 */
public class DataServiceImpl<M extends DataMapper<T>, T extends DataModel> extends ServiceImpl<M, T> implements DataService<T> {

    @Override
    public int getCount(T entity) {
        return this.baseMapper.getCount(initSearchEntity(entity));
    }

    @Override
    public IPage<List<T>> findListPage(IPage<T> page, T entity) {

        return this.baseMapper.findListPage(page, initSearchEntity(entity));
    }

    /**
     * 截取查询条件
     *
     * @param entity
     * @return
     */
    private T initSearchEntity(T entity) {
        String st = "0000", city = "00", dh = ",";
        if (null != entity && StringUtils.isNotBlank(entity.getOrgCode())) {
            if (entity.getOrgCode().endsWith(st)) {
                entity.setOrgCode(entity.getOrgCode().substring(0, 2));
            } else if (entity.getOrgCode().endsWith(city)) {
                entity.setOrgCode(entity.getOrgCode().substring(0, 4));
            }
        }

        if (StringUtils.isNotBlank(entity.getPlaceType()) && entity.getPlaceType().contains(dh)) {
            List<String> typeList = Arrays.asList(entity.getPlaceType().split(dh));
            if (null != typeList) {
                if (typeList.size() == 1) {
                    entity.setPlaceType(typeList.get(0));
                } else {
                    entity.setPlaceTypeList(typeList);
                }
            }
        }

        return entity;
    }

    @Override
    public T findOne(T entity) {
        return this.baseMapper.findOne(entity);
    }


}
