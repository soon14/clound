package com.cloud.portal.asj.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.portal.asj.mapper.DanweiAsjMapper;
import com.cloud.portal.asj.model.DanweiAsj;
import com.cloud.portal.asj.service.DanweiAsjService;
import org.springframework.stereotype.Service;

/**
 * @author lvlinc
 * @date Created in 2019/8/2210:36
 * @description:
 * @modified By:lvlinc
 */
@Service
public class DanweiAsjServiceImpl extends ServiceImpl<DanweiAsjMapper, DanweiAsj> implements DanweiAsjService {
    @Override
    public IPage<DanweiAsj> findPage(IPage<DanweiAsj> page, DanweiAsj danweiAsj) {
        return baseMapper.findPage(page,danweiAsj);
    }
}
