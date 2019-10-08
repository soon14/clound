package com.cloud.portal.archive.contact.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.common.core.util.R;
import com.cloud.common.data.datascope.DataScope;
import com.cloud.common.data.util.DataScopeUtil;
import com.cloud.portal.archive.contact.entity.Csgl;
import com.cloud.portal.archive.contact.mapper.CsglMapper;
import com.cloud.portal.archive.contact.service.CsglService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author ryt
 * @date Created in 2019/6/3 16:41
 * @description: 场所关联信息service实现
 * @modified By:
 */
@Service
public class CsglServiceImpl extends ServiceImpl<CsglMapper, Csgl> implements CsglService {

    /**
     * 分页查询场所关联列表
     *
     * @param page
     * @param csgl
     * @return
     */
    @Override
    public IPage<Csgl> findContactPage(IPage<Csgl> page, Csgl csgl) {
        return this.baseMapper.findContactPage(page, csgl);
    }

    /**
     * 分页查询场所列表
     *
     * @param page
     * @param csgl
     * @return
     */
    @Override
    public IPage<Csgl> findPage(IPage<Csgl> page, Csgl csgl) {
        csgl.setDataScope(DataScopeUtil.getDataScopeSql(new DataScope("A.DEPARTMENTCODE", null)));
        return this.baseMapper.findPage(page, csgl);
    }

    /**
     * 分页查询场所关联列表-警综
     *
     * @param page
     * @param csgl
     * @return
     */
    @Override
    public IPage<Csgl> findJzContactPage(IPage<Csgl> page, Csgl csgl) {
        return this.baseMapper.findJzContactPage(page, csgl);
    }

    /**
     * 分页查询场所列表-警综
     *
     * @param page
     * @param csgl
     * @return
     */
    @Override
    public IPage<Csgl> findJzPage(IPage<Csgl> page, Csgl csgl) {
        csgl.setDataScope(DataScopeUtil.getDataScopeSql(new DataScope("A.datascope", null)));
        return this.baseMapper.findJzPage(page, csgl);
    }

    /**
     * 添加关联
     * 场所关联由一对多改为一对一，关联前进行校验，无论是不是当前场所关联的，只要关联表中存在的场所均不允许多次被关联
     *
     * @param csgl
     * @return success/false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R saveCsgl(Csgl csgl) {
        Csgl temp = new Csgl();
        temp.setJzdwbh(csgl.getJzdwbh());
        int count = baseMapper.findCsExists(temp);
        if (count > 0) {
            return R.failed("该场所已与其他场所关联");
        } else {
            csgl.setCreateTime(LocalDateTime.now());
            return R.ok(baseMapper.saveCsgl(csgl));
        }

    }

    /**
     * 添加关联-警综
     * 场所关联由一对多改为一对一，关联前进行校验，无论是不是当前场所关联的，只要关联表中存在的场所均不允许多次被关联
     *
     * @param csgl
     * @return success/false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R saveJzCsgl(Csgl csgl) {
        Csgl temp = new Csgl();
        temp.setQtdwbh(csgl.getQtdwbh());
        int count = baseMapper.findCsExists(temp);
        if (count > 0) {
            return R.failed("该场所已与其他场所关联.");
        } else {
            csgl.setCreateTime(LocalDateTime.now());
            return R.ok(baseMapper.saveCsgl(csgl));
        }
    }

    /**
     * 自动关联
     *
     * @return success/false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean autoSave() {
        this.baseMapper.autoContact();
        return true;
    }

    /**
     * 取消关联
     *
     * @param jzdwbh 警综场所单位编号
     * @param qtdwbh 其他场所单位编号
     * @return success/false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteCsgl(String jzdwbh, String qtdwbh) {
        return this.baseMapper.deleteCsgl(jzdwbh, qtdwbh);
    }

    @Override
    public Csgl findEntityById(String jzId, String defaultId,boolean containJoin) {
        if (StringUtils.isBlank(jzId) && StringUtils.isBlank(defaultId)) {
            return null;
        }
        return this.baseMapper.findEntityById(jzId, defaultId,containJoin);
    }

}
