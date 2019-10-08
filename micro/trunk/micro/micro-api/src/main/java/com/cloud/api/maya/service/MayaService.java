package com.cloud.api.maya.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.api.common.entity.RestParam;
import com.cloud.api.maya.mapper.Maya;

import java.util.Map;

/**
 * @author wengshij
 * @date Created in 2019/8/10 15:18
 * @description:美亚接口层
 * @modified By:wengshij
 */

public interface MayaService {

    /**
     * 查询获取接口分页信息
     *
     * @param page
     * @param maya
     * @return
     */
    IPage<Map> findPage(Page page, Maya maya);

    /**
     * 远程服务调用 分页信息
     * 调用第三方rest 接口信息
     *
     * @param restParam
     * @return
     */
    Page<Map> restPage(RestParam restParam);


}
