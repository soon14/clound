

package com.cloud.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.api.entity.SysLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 日志表 Mapper 接口
 * </p>
 *
 * @author ygnet
 * @since 2017-11-20
 */
public interface SysLogMapper extends BaseMapper<SysLog> {

    /**
     * 日志列表
     * @param page
     * @param sysLog
     * @return
     */
    IPage<List<SysLog>> findLogPage(Page page, @Param("query") SysLog sysLog);
}
