package com.cloud.portal.archive.ylcs.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.portal.archive.ylcs.entity.Ryba;
import com.cloud.portal.archive.ylcs.mapper.RybaMapper;
import com.cloud.portal.archive.ylcs.service.RybaService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;


/**
 * @author ryt
 * @date Created in 2019/5/9 18:56
 * @description: 娱乐场所-人员备案service层实现
 * @modified By:
 */
@Service
public class RybaServiceImpl extends ServiceImpl<RybaMapper, Ryba> implements RybaService {

    @Override
    public IPage<Ryba> findRybaPage(IPage<Ryba> page, Ryba ryba) {
      return baseMapper.findRybaPage(page,ryba);
    }

    @Override
    public Ryba getRybaCount(String compId,String csbabh){
        return this.baseMapper.getRybaCount(compId,csbabh);
    }

    @Override
    public Ryba getById(String rybaId){
        return this.baseMapper.getById(rybaId);
    }

    /**
     * 获取娱乐场所从业人员 照片信息
     * @param rybaZp 照片ID
     * @return
     */
    @Override
    public Ryba findRyImgById(@Param("rybaZp") String rybaZp){
        return this.baseMapper.findRyImgById(rybaZp);
    }
}
