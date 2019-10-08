package com.cloud.portal.judged.analyze.seal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.common.data.util.DataScopeUtil;
import com.cloud.portal.common.util.DateUtil;
import com.cloud.portal.judged.analyze.machine.model.RepairRecordException;
import com.cloud.portal.judged.analyze.seal.mapper.SameSealEngraveManyMapper;
import com.cloud.portal.judged.analyze.seal.model.SameSealEngraveMany;
import com.cloud.portal.judged.analyze.seal.service.SameSealEngraveManyService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * @author wengshij
 * @date Created in 2019/7/9 15:18
 * @description: 同一单位印章被刻制多次分析
 * @modified By:wengshij
 */
@Service
public class SameSealEngraveManyServiceImpl extends ServiceImpl<SameSealEngraveManyMapper, SameSealEngraveMany> implements SameSealEngraveManyService {
    @Override
    public IPage<RepairRecordException> analyzePage(IPage<SameSealEngraveMany> iPage, SameSealEngraveMany sem) {
        return baseMapper.analyzePage(iPage, initSearchCondition(sem));
    }

    @Override
    public IPage<RepairRecordException> findListPage(IPage<SameSealEngraveMany> iPage, SameSealEngraveMany sem) {
        return baseMapper.findListPage(iPage, initSearchCondition(sem));
    }

    /**
     * 初始化查询条件
     *
     * @param sem
     * @return
     */
    private SameSealEngraveMany initSearchCondition(SameSealEngraveMany sem) {
        String comma = ",";
        Integer arrLength = 2;
        //判断是否时间 yyyy-mm格式
        String reg = "[0-9]{4}-[0-9]{2}";
        if (null == sem) {
            sem = new SameSealEngraveMany();
        }
        String searchDate = StringUtils.isBlank(sem.getEngraveMonth()) ? null : sem.getEngraveMonth().trim();
        if (StringUtils.isNotBlank(searchDate) && sem.getEngraveMonth().contains(comma)) {
            String[] searchTime = StringUtils.split(sem.getEngraveMonth(), comma);

            if (ArrayUtils.isNotEmpty(searchTime) && searchTime.length == arrLength) {
                sem.setStartTime(searchTime[0]);
                sem.setEndTime(searchTime[1]);
            }
        } else if (StringUtils.isNotBlank(searchDate) && searchDate.matches(reg)) {

            Calendar cal = Calendar.getInstance();
            Date date= DateUtil.parseDate(searchDate);
            cal.setTime(date);
            int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            sem.setStartTime(searchDate.concat("-01"));
            sem.setEndTime(searchDate.concat("-"+lastDay));
        }
        sem.setDataScope(DataScopeUtil.getDataScopeSql("A.DEPT_CODE", "same_seal_engrave_many"));
        return sem;
    }
}
