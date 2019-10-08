
package com.cloud.portal.concern.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.concern.entity.PersonConcern;

/**
 *
 *
 * @author ygnet
 * @date 2019-02-27 10:48:28
 */
public interface PersonConcernService extends IService<PersonConcern> {

    IPage<PersonConcern> findPesonConcernPage(IPage<PersonConcern> page, PersonConcern personConcern);

    boolean savePersonConcern(PersonConcern personConcern);

}
