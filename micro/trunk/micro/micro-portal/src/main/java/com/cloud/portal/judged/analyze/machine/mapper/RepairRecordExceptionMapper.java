package com.cloud.portal.judged.analyze.machine.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.judged.analyze.machine.model.RepairRecordException;
import org.apache.ibatis.annotations.Param;

/**
 * @author wengshij
 * @date Created in 2019/7/2 14:45
 * @description: 机修 维修记录异常分析（按天统计-每天记录少于3次视为异常）
 * @modified By:wengshij
 */
public interface RepairRecordExceptionMapper extends BaseMapper<RepairRecordException> {

    /**
     * 维修记录异常分析列表分页信息-包含统计信息
     *
     * @param iPage
     * @param rre
     * @return
     */
    IPage<RepairRecordException> analyzePage(IPage<RepairRecordException> iPage, @Param("query") RepairRecordException rre);

    /**
     * 维修记录异常分析列表分页信息 列表信息
     *
     * @param iPage
     * @param rre
     * @return
     */
    IPage<RepairRecordException> findListPage(IPage<RepairRecordException> iPage, @Param("query") RepairRecordException rre);



}
