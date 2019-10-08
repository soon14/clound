

package com.cloud.act.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.act.entity.LeaveBill;
import com.cloud.act.mapper.LeaveBillMapper;
import com.cloud.act.service.LeaveBillService;
import org.springframework.stereotype.Service;

/**
 * @author ygnet
 * @date 2018-09-27
 */
@Service("leaveBillService")
public class LeaveBillServiceImpl extends ServiceImpl<LeaveBillMapper, LeaveBill> implements LeaveBillService {

}
