package com.cloud.portal.judged.analyze.entertainment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.judged.analyze.entertainment.model.PersonTruthReport;
import org.apache.ibatis.annotations.Param;

/**
 * @author ryt
 * @date Created in 2019/7/9 10:41
 * @description: 从业人员未如实报备分析模型-针对娱乐场所
 * @modified By:
 */
public interface PersonTruthReportMapper extends BaseMapper<PersonTruthReport> {

    /**
     * 从业人员未如实报备分析列表分页信息
     *
     * @param page
     * @param personTruthReport
     * @return
     */
    IPage<PersonTruthReport> analyzePage(IPage<PersonTruthReport> page, @Param("query") PersonTruthReport personTruthReport);

    /**
     * 从业人员未如实报备分析 详情列表信息
     *
     * @param page
     * @param personTruthReport
     * @return
     */
    IPage<PersonTruthReport> findListPage(IPage<PersonTruthReport> page, @Param("query") PersonTruthReport personTruthReport);
}
