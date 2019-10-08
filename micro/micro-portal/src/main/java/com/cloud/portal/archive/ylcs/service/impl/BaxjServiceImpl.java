package com.cloud.portal.archive.ylcs.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.portal.archive.ylcs.entity.Baxj;
import com.cloud.portal.archive.ylcs.mapper.BaxjMapper;
import com.cloud.portal.archive.ylcs.service.BaxjService;
import org.springframework.stereotype.Service;

/**
 * @author ryt
 * @date Created in 2019/5/20 10:00
 * @description: 娱乐场所-保安巡检service层实现
 * @modified By:
 */
@Service
public class BaxjServiceImpl extends ServiceImpl<BaxjMapper, Baxj> implements BaxjService {
    /**
     * 获取保安巡检信息列表
     *
     * @param page,ryLog
     * @return 保安巡检信息
     */
    @Override
    public  IPage<Baxj> findBaxjPage(IPage<Baxj> page, Baxj baxj) {
      return baseMapper.findBaxjPage(page,baxj);
    }

}
