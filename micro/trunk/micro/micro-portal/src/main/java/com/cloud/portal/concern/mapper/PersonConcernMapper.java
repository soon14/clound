
package com.cloud.portal.concern.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.concern.entity.PersonConcern;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

/**
 *
 *
 * @author ygnet
 * @date 2019-02-27 10:48:28
 */
@Mapper
public interface PersonConcernMapper extends BaseMapper<PersonConcern> {


    /**
     * 个人关注对象 列表 包含权限信息
     * @param page
     * @param personConcern
     * @return
     */
    IPage<PersonConcern> findPesonConcernPage(IPage<PersonConcern> page,  @Param("queryCondition")PersonConcern personConcern);

    boolean savePersonConcern(PersonConcern personConcern);
}
