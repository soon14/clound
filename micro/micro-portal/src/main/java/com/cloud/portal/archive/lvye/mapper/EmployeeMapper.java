package com.cloud.portal.archive.lvye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.portal.archive.lvye.model.Employee;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author wengshij
 * @date Created in 2019/5/22 9:10
 * @description: 旅馆业从业人员信息 数据层
 * @modified By:wengshij
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    /**
     *  旅馆从业人员信息分页
     *  根据当前旅馆单位编码 获取当前旅馆从业人员列表
     * @param page
     * @param employee
     * @return
     */
    IPage<List<Employee>> findPage(Page page, @Param("query") Employee employee);

    /**
     * 根据单位ID、统计当前月份 离岗 上岗 人数
     * @param unitId
     * @return
     */
    List<Map> monthCount(String unitId);

    /**
     * 获取旅馆业从业人员 照片信息
     * @param id 主键ID
     * @param code 地市代码
     * @return
     */
    Employee findImgById(@Param("id") String id,@Param("code") String code);
}
