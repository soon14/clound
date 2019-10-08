package com.cloud.portal.archive.tyyh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.archive.tyyh.model.Tyjg;

import java.util.List;

/**
 * @author lvlinc
 * @date Created in 2019/6/10
 * @description:
 * @modified By:lvlinc
 */
public interface TyjgService extends IService<Tyjg> {
    List<Tyjg> getTyjg (Tyjg tyjg,String cacheKey);
}
