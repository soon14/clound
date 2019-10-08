package com.cloud.portal.archive.jingz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.portal.archive.jingz.mapper.XfjcMapper;
import com.cloud.portal.archive.jingz.model.Xfjc;
import com.cloud.portal.archive.jingz.service.XfjcService;
import org.springframework.stereotype.Service;



/**
 * @author ryt
 * @date Created in 2019/6/18 17:14
 * @description: 警综消防监督检查信息
 * @modified By:
 */
@Service
public class XfjcServiceImpl extends ServiceImpl<XfjcMapper, Xfjc> implements XfjcService {

    /**
     * 获取消防检查信息列表
     * @param page
     * @param xfjc
     * @return
     */
    @Override
    public IPage<Xfjc> findXfjcPage(IPage<Xfjc> page, Xfjc xfjc){
        return this.baseMapper.findXfjcPage(page,xfjc);
    }

    /**
     * 通过ID查询检查详细信息
     *
     * @param systemId
     * @return 检查详细信息
     */
    @Override
    public Xfjc getXfjcById(String systemId){
        return this.baseMapper.getXfjcById(systemId);
    }

}
