package com.cloud.portal.judged.analyze.entertainment.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.judged.analyze.entertainment.model.PersonTruthReport;
import com.cloud.portal.judged.analyze.machine.model.RepairRecordException;

/**
 * @author ryt
 * @date Created in 2019/7/9 09:50
 * @description: 从业人员未如实报备分析模型-针对娱乐场所
 * @modified By:
 */
public interface PersonTruthReportService extends IService<PersonTruthReport> {
    /**
     * 从业人员未如实报备分析列表分页信息
     *
     * @param page
     * @param personTruthReport
     * @return
     */
    IPage<PersonTruthReport> analyzePage(IPage<PersonTruthReport> page, PersonTruthReport personTruthReport);

    /**
     * 从业人员未如实报备分析 详情列表信息
     *
     * @param page
     * @param personTruthReport
     * @return
     */
    IPage<PersonTruthReport> findListPage(IPage<PersonTruthReport> page, PersonTruthReport personTruthReport);


}
