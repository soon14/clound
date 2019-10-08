package com.cloud.portal.judged.analyze.entertainment.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.common.data.util.DataScopeUtil;
import com.cloud.portal.common.util.DateUtil;
import com.cloud.portal.judged.analyze.entertainment.mapper.PersonTruthReportMapper;
import com.cloud.portal.judged.analyze.entertainment.model.PersonTruthReport;
import com.cloud.portal.judged.analyze.entertainment.service.PersonTruthReportService;
import com.cloud.portal.judged.analyze.machine.model.RepairRecordException;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;


/**
 * @author ryt
 * @date Created in 2019/7/9 09:55
 * @description: 从业人员未如实报备分析模型-针对娱乐场所
 * @modified By:
 */
@Service
public class PersonTruthReportServiceImpl extends ServiceImpl<PersonTruthReportMapper, PersonTruthReport> implements PersonTruthReportService {

    /**
     * 从业人员未如实报备分析列表分页信息
     *
     * @param page
     * @param personTruthReport
     * @return
     */
    @Override
    public IPage<PersonTruthReport> analyzePage(IPage<PersonTruthReport> page, PersonTruthReport personTruthReport) {
        return this.baseMapper.analyzePage(page, initSearch(personTruthReport));
    }

    /**
     * 从业人员未如实报备分析 详情列表信息
     *
     * @param page
     * @param personTruthReport
     * @return
     */
    @Override
    public IPage<PersonTruthReport> findListPage(IPage<PersonTruthReport> page, PersonTruthReport personTruthReport) {
        return this.baseMapper.findListPage(page, initSearch(personTruthReport));
    }

    /**
     * 初始化 查询条件信息
     *
     * @param personTruthReport
     * @return
     */
    private PersonTruthReport initSearch(PersonTruthReport personTruthReport) {
        String symbol = ",";
        Integer arrLength = 2;
        //判断是否时间 yyyy-mm格式
        String reg = "[0-9]{4}-[0-9]{2}";
        if (null == personTruthReport) {
            personTruthReport = new PersonTruthReport();
        }
        if (StringUtils.isNotBlank(personTruthReport.getDeptCode())) {
            personTruthReport.setDeptCode(personTruthReport.getDeptCode().replaceAll("0*$", ""));
        }
        String clockMonth = StringUtils.isBlank(personTruthReport.getClockMonth()) ? null : personTruthReport.getClockMonth().trim();
        if (StringUtils.isNotBlank(clockMonth) && personTruthReport.getClockMonth().contains(symbol)) {
            String[] searchDate = StringUtils.split(personTruthReport.getClockMonth(), symbol);
            if (ArrayUtils.isNotEmpty(searchDate) && searchDate.length == arrLength) {
                personTruthReport.setStartTime(searchDate[0]);
                personTruthReport.setEndTime(searchDate[1]);
            }
        }else if (StringUtils.isNotBlank(clockMonth) && clockMonth.substring(0,7).matches(reg)) {
            String tempClockMonth = clockMonth.substring(0,7);
            Calendar calendar = Calendar.getInstance();
            Date date= DateUtil.parseDate(tempClockMonth);
            calendar.setTime(date);
            int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            personTruthReport.setStartTime(tempClockMonth.concat("-01"));
            personTruthReport.setEndTime(tempClockMonth.concat("-"+lastDay));
        }

        personTruthReport.setDataScope(DataScopeUtil.getDataScopeSql("A.DEPT_CODE", "person_truth_report"));
        return personTruthReport;
    }
}
