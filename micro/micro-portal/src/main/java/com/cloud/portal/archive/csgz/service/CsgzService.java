package com.cloud.portal.archive.csgz.service;

import com.cloud.portal.archive.common.base.service.DataService;
import com.cloud.portal.archive.csgz.model.Csgz;

/**
 * @author lvlinc
 * @date Created in 2019/5/22 10:22:00
 * @description: 特行场所关注信息
 * @modified By:lvlinc
 */
public interface CsgzService extends DataService<Csgz> {
    /**
     * 添加场所关注
     * @param csgz
     * @return
     */
    boolean savecsgz(Csgz csgz);
    /**
     * 是否场所关注
     * @param csgz
     * @return
     */
    int getis (Csgz csgz);
    /**
     * 取消场所关注
     * @param csdwbh
     * @param userid
     * @return
     */
    boolean remove(String csdwbh,String userid);


}
