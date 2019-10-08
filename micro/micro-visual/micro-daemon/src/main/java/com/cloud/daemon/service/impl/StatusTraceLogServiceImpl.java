package com.cloud.daemon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.daemon.service.StatusTraceLogService;
import com.cloud.daemon.entity.StatusTraceLog;
import com.cloud.daemon.mapper.StatusTraceLogMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 任务轨迹处理
 *
 * @author lishangbu
 * @date 2018/11/22
 */
@Service("statusTraceLogService")
@AllArgsConstructor
public class StatusTraceLogServiceImpl extends ServiceImpl<StatusTraceLogMapper, StatusTraceLog> implements StatusTraceLogService {

}
