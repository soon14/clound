package com.cloud.portal.archive.jingz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.portal.archive.jingz.mapper.ZjxxMapper;
import com.cloud.portal.archive.jingz.model.Zjxx;
import com.cloud.portal.archive.jingz.service.ZjxxService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lvlinc
 * @date Created in 2019/6/15
 * @description:
 * @modified By:lvlinc
 */
@Service
public class ZjxxServiceImpl extends ServiceImpl<ZjxxMapper, Zjxx>  implements ZjxxService {
    @Override
    public IPage<List<Zjxx>> findPage(Page page, Zjxx zjxx) {
        return baseMapper.findPage(page,zjxx);
    }

    @Override
    public Zjxx imgById(String fileid) {
        return baseMapper.imgById(fileid);
    }
}
