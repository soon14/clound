package com.cloud.daemon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.daemon.service.ExecutionLogService;
import com.cloud.daemon.entity.ExecutionLog;
import com.cloud.daemon.mapper.ExecutionLogMapper;
import org.springframework.stereotype.Service;

/**
 * 任务日志处理
 *
 * @author lishangbu
 * @date 2018/11/22
 */
@Service("executionLogService")
public class ExecutionLogServiceImpl extends ServiceImpl<ExecutionLogMapper, ExecutionLog> implements ExecutionLogService {

}
