
package com.cloud.portal.concern.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.admin.api.entity.SysUser;
import com.cloud.common.data.datascope.DataScope;
import com.cloud.common.data.util.DataScopeUtil;
import com.cloud.common.security.service.MicroUser;
import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.concern.entity.PersonConcern;
import com.cloud.portal.concern.mapper.PersonConcernMapper;
import com.cloud.portal.concern.service.PersonConcernService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 *
 *
 * @author ygnet
 * @date 2019-02-27 10:48:28
 */
@Service
public class PersonConcernServiceImpl extends ServiceImpl<PersonConcernMapper, PersonConcern> implements PersonConcernService {

    @Override
    public IPage<PersonConcern> findPesonConcernPage(IPage<PersonConcern> page, PersonConcern personConcern) {
        MicroUser user = SecurityUtils.getUser();
        SysUser sysUser=new SysUser();
        sysUser.setUserId(user.getId());
        personConcern.setSysUser(sysUser);
        personConcern.setDataScope(DataScopeUtil.getDataScopeSql(new DataScope("o.code", "person_concern_list", "a.user_id")));
        return baseMapper.findPesonConcernPage(page,personConcern);
    }

    @Override
    public boolean savePersonConcern(PersonConcern personConcern) {
        MicroUser user = SecurityUtils.getUser();
        SysUser sysUser=new SysUser();
        sysUser.setUserId(user.getId());
        personConcern.setSysUser(sysUser);
        personConcern.setDelFlag("0");
        personConcern.setCreateDate(LocalDateTime.now());
        personConcern.setUpdateDate(LocalDateTime.now());
        return baseMapper.savePersonConcern(personConcern);
    }
}
