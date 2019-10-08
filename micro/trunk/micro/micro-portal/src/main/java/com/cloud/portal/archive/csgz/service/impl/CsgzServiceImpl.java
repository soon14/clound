package com.cloud.portal.archive.csgz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.archive.common.base.service.impl.DataServiceImpl;
import com.cloud.portal.archive.csgz.mapper.CsgzMapper;
import com.cloud.portal.archive.csgz.model.Csgz;
import com.cloud.portal.archive.csgz.service.CsgzService;
import org.springframework.stereotype.Service;
/**
 * @author lvlinc
 * @date Created in 2019/5/22 10:22:00
 * @description: 特行场所关注信息
 * @modified By:lvlinc
 */
@Service
public class CsgzServiceImpl extends DataServiceImpl<CsgzMapper, Csgz> implements CsgzService {
    @Override
    public IPage<Csgz> findPage(IPage<Csgz> page, Csgz csgz) {
        return baseMapper.findPage(page,csgz);
    }

    @Override
    public int getis(Csgz csgz) {
        if (csgz.getIsgz()==1){
            int i = baseMapper.getis(csgz);
            if(i>0){
              baseMapper.remove(csgz.getCsdwbh(),csgz.getUserid());
            }else{
              baseMapper.savecsgz(csgz);
            }
        }
        return baseMapper.getis(csgz);
    }

    @Override
    public boolean remove(String csdwbh,String userid) {
        return baseMapper.remove(csdwbh,userid);
    }
}
