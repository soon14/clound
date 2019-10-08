
package com.cloud.quartz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.quartz.entity.SysJob;
import com.cloud.quartz.mapper.SysJobMapper;
import com.cloud.quartz.service.SysJobService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author wengshij
 * @date Created in 2019/6/25 9:51
 * @description: 定时任务日志表信息
 * @modified By:wengshij
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysJobServiceImpl extends ServiceImpl<SysJobMapper, SysJob> implements SysJobService {

}
