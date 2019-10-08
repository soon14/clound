package com.cloud.portal.archive.jingz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.portal.archive.jingz.mapper.ZajcMapper;
import com.cloud.portal.archive.jingz.model.Zajc;
import com.cloud.portal.archive.jingz.model.ZajcConfig;
import com.cloud.portal.archive.jingz.service.ZajcService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author ryt
 * @date Created in 2019/6/15 11:11
 * @description: 警综治安检查（日常检查）信息
 * @modified By:
 */
@Service
public class ZajcServiceImpl extends ServiceImpl<ZajcMapper, Zajc> implements ZajcService {

    /**
     * 获取治安检查信息列表
     * @param page
     * @param zajc
     * @return
     */
    @Override
   public IPage<Zajc> findZajcPage(IPage<Zajc> page, Zajc zajc){
       return this.baseMapper.findZajcPage(page,zajc);
   }

    /**
     * 通过ID查询检查详细信息
     *
     * @param systemId
     * @return 检查详细信息
     */
    @Override
   public Zajc getZajcById(String systemId){
       return this.baseMapper.getZajcById(systemId);
   }

    /**
     * 获取治安检查项内容
     * @param systemId
     * @return
     */
    @Override
   public List<ZajcConfig> getZajcConfig(String systemId){
       List<ZajcConfig> zajcConfigList = this.baseMapper.getZajcConfig(systemId);
       return zajcConfigList.stream()
               .sorted(Comparator.comparingInt(ZajcConfig::getSywh))
               .collect(Collectors.toList());
   }

}
