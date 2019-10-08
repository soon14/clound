package com.cloud.portal.judged.analyze.seal.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.portal.judged.analyze.machine.model.RepairRecordException;
import com.cloud.portal.judged.analyze.seal.model.SameSealEngraveMany;
import org.apache.ibatis.annotations.Param;

/**
 * @author wengshij
 * @date Created in 2019/7/9 15:18
 * @description: 同一单位印章被刻制多次分析
 * @modified By:wengshij
 */
public interface SameSealEngraveManyService extends IService<SameSealEngraveMany> {


    /**
     * 同一单位印章分析记录
     *
     * @param iPage
     * @param sem
     * @return
     */
    IPage<RepairRecordException> analyzePage(IPage<SameSealEngraveMany> iPage,  SameSealEngraveMany sem);

    /**
     * 获取印章刻制列表信息
     *
     * @param iPage
     * @param sem
     * @return
     */
    IPage<RepairRecordException> findListPage(IPage<SameSealEngraveMany> iPage,SameSealEngraveMany sem);


}
