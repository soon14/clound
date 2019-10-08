package com.cloud.portal.judged.analyze.machine.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.common.data.util.DataScopeUtil;
import com.cloud.portal.common.util.DateUtil;
import com.cloud.portal.judged.analyze.machine.mapper.DifferentPlacesRevampMapper;
import com.cloud.portal.judged.analyze.machine.model.DifferentPlacesRevamp;
import com.cloud.portal.judged.analyze.machine.service.DifferentPlacesRevampService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * @author lvlinc
 * @date Created in 2019/7/10
 * @description:机修 同一车辆不同车行多次修改分析
 * @modified By:lvlinc
 */
@Service
public class DifferentPlacesRevampServiceImpl extends ServiceImpl<DifferentPlacesRevampMapper, DifferentPlacesRevamp> implements DifferentPlacesRevampService {
    @Override
    public IPage<DifferentPlacesRevamp> findPage(IPage<DifferentPlacesRevamp> page, DifferentPlacesRevamp dpr) {
        return baseMapper.findPage(page,initSearchCondition(dpr));
    }

    @Override
    public IPage<DifferentPlacesRevamp> monthPage(IPage<DifferentPlacesRevamp> page, DifferentPlacesRevamp dpr) {
        return baseMapper.monthPage(page,initSearchCondition(dpr));
    }

    @Override
    public IPage<DifferentPlacesRevamp> oneToMany(IPage<DifferentPlacesRevamp> page, DifferentPlacesRevamp dpr) {
        return baseMapper.oneToMany(page,dpr);
    }

    /**
     * 初始化查询条件
     *
     * @param dpr
     * @return
     */
    private DifferentPlacesRevamp initSearchCondition(DifferentPlacesRevamp dpr) {
        String comma = ",";
        Integer arrLength = 2;
        //判断是否时间 yyyy-mm格式
        String reg = "[0-9]{4}-[0-9]{2}";
        if (null == dpr) {
            dpr = new DifferentPlacesRevamp();
        }
        String searchDate = StringUtils.isBlank(dpr.getRepairMonth()) ? null : dpr.getRepairMonth().trim();
        if (StringUtils.isNotBlank(searchDate) && dpr.getRepairMonth().contains(comma)) {
            String[] searchTime = StringUtils.split(dpr.getRepairMonth(), comma);

            if (ArrayUtils.isNotEmpty(searchTime) && searchTime.length == arrLength) {
                dpr.setStartTime(searchTime[0]);
                dpr.setEndTime(searchTime[1]);
            }
        } else if (StringUtils.isNotBlank(searchDate) && searchDate.matches(reg)) {

            Calendar cal = Calendar.getInstance();
            Date date= DateUtil.parseDate(searchDate);
            cal.setTime(date);
            int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            dpr.setStartTime(searchDate.concat("-01"));
            dpr.setEndTime(searchDate.concat("-"+lastDay));
        }
        dpr.setDataScope(DataScopeUtil.getDataScopeSql("t.DEPT_CODE", "same_site_multiple_modification"));
        return dpr;
    }
}
