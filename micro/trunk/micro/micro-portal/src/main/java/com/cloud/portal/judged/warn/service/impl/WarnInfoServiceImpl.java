package com.cloud.portal.judged.warn.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.portal.judged.warn.mapper.WarnInfoMapper;
import com.cloud.portal.judged.warn.model.WarnInfo;
import com.cloud.portal.judged.warn.service.WarnInfoService;
import org.springframework.stereotype.Service;

/**
 * @author wengshij
 * @date Created in 2019/6/25 9:52
 * @description: 异常警告信息
 * @modified By:wengshij
 */
@Service
public class WarnInfoServiceImpl extends ServiceImpl<WarnInfoMapper, WarnInfo> implements WarnInfoService {
    @Override
    public IPage<WarnInfo> findPage(IPage<WarnInfo> page, WarnInfo warnInfo) {
        return this.baseMapper.findPage(page, warnInfo);
    }
}
