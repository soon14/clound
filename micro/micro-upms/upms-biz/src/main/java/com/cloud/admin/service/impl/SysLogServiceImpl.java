

package com.cloud.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.admin.api.entity.SysLog;
import com.cloud.admin.api.vo.PreLogVo;
import com.cloud.admin.mapper.SysLogMapper;
import com.cloud.admin.service.SysLogService;
import com.cloud.common.core.constant.CommonConstants;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author ygnet
 * @since 2017-11-20
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    /**
     * 批量插入前端错误日志
     *
     * @param preLogVoList 日志信息
     * @return true/false
     */
    @Override
    public Boolean saveBatchLogs(List<PreLogVo> preLogVoList) {
        List<SysLog> sysLogs = preLogVoList.stream()
                .map(pre -> {
                    SysLog log = new SysLog();
                    log.setType(CommonConstants.STATUS_LOCK);
                    log.setTitle(pre.getInfo());
                    log.setException(pre.getStack());
                    log.setParams(pre.getMessage());
                    log.setCreateTime(LocalDateTime.now());
                    log.setRequestUri(pre.getUrl());
                    log.setCreateBy(pre.getUser());
                    return log;
                })
                .collect(Collectors.toList());
        return this.saveBatch(sysLogs);
    }

    @Override
    public IPage<List<SysLog>> findLogPage(Page page, SysLog sysLog) {
        return baseMapper.findLogPage(page, sysLog);
    }
}
