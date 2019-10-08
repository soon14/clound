
package com.cloud.quartz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.quartz.entity.SysJobLog;
import com.cloud.quartz.mapper.SysJobLogMapper;
import com.cloud.quartz.service.SysJobLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author wengshij
 * @date Created in 2019/6/25 9:51
 * @description: 定时任务执行日志表信息
 * @modified By:wengshij
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysJobLogServiceImpl extends ServiceImpl<SysJobLogMapper, SysJobLog> implements SysJobLogService {

}
