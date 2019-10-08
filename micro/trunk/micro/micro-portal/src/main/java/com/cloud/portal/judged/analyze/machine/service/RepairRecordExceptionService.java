package com.cloud.portal.judged.analyze.machine.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.judged.analyze.machine.model.RepairRecordException;

/**
 * @author wengshij
 * @date Created in 2019/7/2 14:46
 * @description: 机修 维修记录异常分析（按天统计-每天记录少于3次视为异常）
 * @modified By:wengshij
 */
public interface RepairRecordExceptionService extends IService<RepairRecordException> {
    /**
     * 维修记录异常分析列表分页信息
     *
     * @param iPage
     * @param rre
     * @return
     */
    IPage<RepairRecordException> analyzePage(IPage<RepairRecordException> iPage,  RepairRecordException rre);

    /**
     * 维修记录异常分析列表分页信息 列表信息
     *
     * @param iPage
     * @param rre
     * @return
     */
    IPage<RepairRecordException> findListPage(IPage<RepairRecordException> iPage, RepairRecordException rre);


}
