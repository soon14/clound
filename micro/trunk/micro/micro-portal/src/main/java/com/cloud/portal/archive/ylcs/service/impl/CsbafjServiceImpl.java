package com.cloud.portal.archive.ylcs.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.portal.archive.ylcs.entity.Csbafj;
import com.cloud.portal.archive.ylcs.mapper.CsbafjMapper;
import com.cloud.portal.archive.ylcs.service.CsbafjService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * @author ryt
 * @date Created in 2019/5/22 18:56
 * @description: 娱乐场所-场所备案附件(证件信息)service层实现
 * @modified By:
 */
@Service
public class CsbafjServiceImpl extends ServiceImpl<CsbafjMapper, Csbafj> implements CsbafjService {

    /**
     * 查询场所备案附件列表
     *
     * @param page,csbafj
     * @return 场所备案附件列表
     */
    @Override
    public IPage<Csbafj> findCsbafjPage(IPage<Csbafj> page, Csbafj csbafj){
        return this.baseMapper.findCsbafjPage(page,csbafj);
    }

    /**
     * 获取场所 照片信息
     * @param csbafj 照片ID
     * @return
     */
    @Override
    public Csbafj findImgById( Csbafj csbafj){
        return  this.baseMapper.findImgById(csbafj);
    }

    /**
     * 获取场所平面图
     * @param csbaId 场所ID
     * @return
     */
    @Override
   public Csbafj findPmtById(String csbaId){
        return this.baseMapper.findPmtById(csbaId);
    }
}
