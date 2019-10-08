package com.cloud.portal.common.feign;

import com.cloud.admin.api.dto.DictTree;
import com.cloud.admin.api.entity.SysDict;
import com.cloud.admin.api.feign.RemoteDictService;
import com.cloud.admin.api.vo.TreeUtil;
import com.cloud.common.core.util.R;
import com.cloud.common.core.util.SpringContextHolder;
import com.cloud.common.security.service.MicroUser;
import com.cloud.common.security.util.SecurityUtils;
import com.cloud.portal.common.constant.PortalConstants;
import com.cloud.portal.judged.model.model.ModelMenu;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wengshij
 * @date Created in 2019/7/24 23:15
 * @description: 系统字典API接口调用工具类
 * @modified By:gxx
 */
@Slf4j
public class SysDictApiUtil {

    private static RemoteDictService remoteDictService = SpringContextHolder.getBean(RemoteDictService.class);
    private static CacheManager cacheManager = SpringContextHolder.getBean(CacheManager.class);

    /**
     * 通过字典类型查找字典
     *
     * @param type 类型
     * @return 同类型字典
     */
    public static List<SysDict> getDictList(@RequestParam("type") String type){
        List<SysDict> data = remoteDictService.getDictList(type);
        return data;
    }

    public static List<DictTree> getDictTreeList(List<SysDict> sysDicts){
        List<DictTree> data = TreeUtil.bulidDictTree(sysDicts,"0");
        return data;
    }
}
