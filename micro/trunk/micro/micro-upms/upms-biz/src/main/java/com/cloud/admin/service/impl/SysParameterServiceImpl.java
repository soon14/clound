

package com.cloud.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.admin.api.entity.SysParameter;
import com.cloud.admin.mapper.SysParameterMapper;
import com.cloud.admin.service.SysParameterService;
import com.cloud.common.core.constant.CommonConstants;
import com.cloud.common.security.util.SecurityUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author ygnet
 * @since 2017-11-19
 */
@Service
public class SysParameterServiceImpl extends ServiceImpl<SysParameterMapper, SysParameter> implements SysParameterService {

   @Override
   @Transactional(rollbackFor = Exception.class)
   @CacheEvict(value = "parameter_details", key="#sysParameter.parameterKey")
    public Boolean saveParameter(SysParameter sysParameter){
       sysParameter.setCreateBy(SecurityUtils.getUser().getId());
       sysParameter.setUpdateBy(SecurityUtils.getUser().getId());
       sysParameter.setCreateDate(LocalDateTime.now());
       sysParameter.setUpdateDate(LocalDateTime.now());
       sysParameter.setDelFlag(CommonConstants.STATUS_NORMAL);
       return this.save(sysParameter);
   }

   @Override
   @Transactional(rollbackFor = Exception.class)
   @CacheEvict(value = "parameter_details",key = "#sysParameter.parameterKey")
   public Boolean updateParameter(SysParameter sysParameter){
      sysParameter.setUpdateBy(SecurityUtils.getUser().getId());
      sysParameter.setUpdateDate(LocalDateTime.now());
      return this.updateById(sysParameter);
   }

   @Override
   @CacheEvict(value = "parameter_details",key = "#sysParameter.parameterKey")
   public Boolean deleteById(SysParameter sysParameter) {
      this.removeById(sysParameter.getId());
      return Boolean.TRUE;
   }
}
