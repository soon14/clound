package com.cloud.admin.api.feign;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cloud.admin.api.entity.SysDict;
import com.cloud.common.core.constant.ServiceNameConstants;
import com.cloud.common.core.util.R;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author wengshij
 * @Date Created in 2019/3/7 16:16
 * Description: 字典接口信息
 * Modified By:
 */
@FeignClient(value = ServiceNameConstants.UMPS_SERVICE)
public interface RemoteDictService {

    /**
     * 获取字典信息
     *
     * @param value        字典key
     * @param type         字典类型
     * @param defaultValue 默认值
     * @return
     */
    @GetMapping("dict/label")
    String getDictLabel(@RequestParam("value") String value,@RequestParam("type") String type,@RequestParam("defaultValue") String defaultValue);

    /**
     * 通过字典类型查找字典
     *
     * @param type 类型
     * @return 同类型字典
     */
    @GetMapping("dict/dictList")
    List<SysDict> getDictList(@RequestParam("type") String type);
}
