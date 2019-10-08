package com.cloud.portal.judged.warn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.judged.warn.model.WarnInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @author wengshij
 * @date Created in 2019/6/25 9:51
 * @description: 异常警告信息
 * @modified By:wengshij
 */
public interface WarnInfoService extends IService<WarnInfo> {

    /**
     * 获取异常警告分页信息
     * 包含模型关联信息
     * @param page
     * @param warnInfo
     * @return
     */
    IPage<WarnInfo> findPage(IPage<WarnInfo> page, @Param("query")WarnInfo warnInfo);

}
