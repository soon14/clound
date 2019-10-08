package com.cloud.portal.archive.ylcs.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.portal.archive.ylcs.entity.Aqjc;
import com.cloud.portal.archive.ylcs.entity.AqjcContent;
import com.cloud.portal.archive.ylcs.mapper.AqjcMapper;
import com.cloud.portal.archive.ylcs.service.AqjcService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ryt
 * @date Created in 2019/5/20 15:00
 * @description: 娱乐场所-公安日常检查service层实现
 * @modified By:
 */
@Service
public class AqjcServiceImpl extends ServiceImpl<AqjcMapper, Aqjc> implements AqjcService {

    /**
     * 获取安全检查信息列表
     * @param page
     * @param aqjc
     * @return
     */
    @Override
    public IPage<Aqjc> findAqjcPage(IPage<Aqjc> page, Aqjc aqjc){
        return this.baseMapper.findAqjcPage(page,aqjc);
    }

    /**
     * 通过ID查询检查详细信息
     *
     * @param aqjcId
     * @return 检查详细信息
     */
    @Override
    public Aqjc getAqjcById( String aqjcId){
        return this.baseMapper.getAqjcById(aqjcId);
    }

    /**
     * 获取安全检查项内容
     * @param aqjcId
     * @return
     */
    @Override
    public List<AqjcContent> getAqjcContent(String aqjcId){
        List<AqjcContent> aqjcContentList = this.baseMapper.getAqjcContent(aqjcId);
        return aqjcContentList.stream()
                .sorted(Comparator.comparingInt(AqjcContent::getProOrderby))
                .collect(Collectors.toList());
    }
}
