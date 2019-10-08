package com.cloud.portal.statistics.employee.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.statistics.employee.model.Illegality;

import java.util.List;

/**
 * @author Molly
 * @date Created in 2019/8/9
 * @description: 统计查询-统计报表-案件信息service接口
 * @modified By: Molly
 * */
public interface IllegalityService extends IService<Illegality> {

    /**
     * 根据身份证号获取违法犯罪列表
     * @param zjhm
     * @return
     */
    public List<Illegality> findListByZJHM(String zjhm);

}
