package com.cloud.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.admin.api.entity.SysParameter;

/**
 * 全局参数
 *
 * @author Molly
 * @since 2019-02-20
 */
public interface SysParameterService extends IService<SysParameter> {

    /**
     * 保存参数
     *
     * @param sysParameter 参数
     * @return Boolean
     */
    Boolean saveParameter(SysParameter sysParameter);

    /**
     * 修改参数
     *
     * @param sysParameter 参数
     * @return Boolean
     */
    Boolean updateParameter(SysParameter sysParameter);

    /**
     * 删除参数
     *
     * @param sysParameter 参数
     * @return boolean
     */
    Boolean deleteById(SysParameter sysParameter);
}
