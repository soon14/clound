package com.cloud.portal.judged.analyze.machine.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.common.data.util.DataScopeUtil;
import com.cloud.portal.common.util.DateUtil;
import com.cloud.portal.judged.analyze.machine.mapper.RepairRecordExceptionMapper;
import com.cloud.portal.judged.analyze.machine.model.RepairRecordException;
import com.cloud.portal.judged.analyze.machine.service.RepairRecordExceptionService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;


/**
 * @author wengshij
 * @date Created in 2019/7/2 14:46
 * @description: 机修 维修记录异常分析（按天统计-每天记录少于3次视为异常）
 * @modified By:wengshij
 */
@Service
public class RepairRecordExceptionServiceImpl extends ServiceImpl<RepairRecordExceptionMapper, RepairRecordException> implements RepairRecordExceptionService {


    @Override
    public IPage<RepairRecordException> analyzePage(IPage<RepairRecordException> iPage, RepairRecordException rre) {

        return this.baseMapper.analyzePage(iPage, initSearch(rre));
    }

    @Override
    public IPage<RepairRecordException> findListPage(IPage<RepairRecordException> iPage, RepairRecordException rre) {
        return this.baseMapper.findListPage(iPage, initSearch(rre));
    }

    /**
     * 初始化 一些查询条件信息
     *
     * @param rre
     * @return
     */
    private RepairRecordException initSearch(RepairRecordException rre) {
        String comma = ",";
        Integer arrLength = 2;
        if (null == rre) {
            rre = new RepairRecordException();
        }
        if (StringUtils.isNotBlank(rre.getDeptCode())) {
            rre.setDeptCode(rre.getDeptCode().replaceAll("0*$", ""));
        }
        if (StringUtils.isNotBlank(rre.getSearchTime()) && rre.getSearchTime().contains(comma)) {
            String[] searchTime = StringUtils.split(rre.getSearchTime(), comma);
            if (ArrayUtils.isNotEmpty(searchTime) && searchTime.length == arrLength) {
                rre.setStartTime(DateUtil.parseDate(searchTime[0]));
                rre.setEndTime(DateUtil.parseDate(searchTime[1]));
            }
        }
        rre.setDataScope(DataScopeUtil.getDataScopeSql("A.DEPT_CODE", "repair_record_exception"));
        return rre;
    }
}
