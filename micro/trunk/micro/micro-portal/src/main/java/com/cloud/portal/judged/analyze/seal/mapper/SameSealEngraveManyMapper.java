package com.cloud.portal.judged.analyze.seal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.judged.analyze.machine.model.RepairRecordException;
import com.cloud.portal.judged.analyze.seal.model.SameSealEngraveMany;
import org.apache.ibatis.annotations.Param;

/**
 * @author wengshij
 * @date Created in 2019/7/9 15:18
 * @description: 同一单位印章被刻制多次分析
 * @modified By:wengshij
 */
public interface SameSealEngraveManyMapper extends BaseMapper<SameSealEngraveMany> {

    /**
     * 同一单位印章分析记录
     *
     * @param iPage
     * @param sem
     * @return
     */
    IPage<RepairRecordException> analyzePage(IPage<SameSealEngraveMany> iPage, @Param("query") SameSealEngraveMany sem);



    /**
     * 获取印章刻制列表信息
     *
     * @param iPage
     * @param sem
     * @return
     */
    IPage<RepairRecordException> findListPage(IPage<SameSealEngraveMany> iPage, @Param("query") SameSealEngraveMany sem);

}
