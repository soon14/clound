package com.cloud.portal.archive.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.common.core.util.R;
import com.cloud.common.core.util.SpringContextHolder;
import com.cloud.common.data.datascope.DataScope;
import com.cloud.common.data.util.DataScopeUtil;
import com.cloud.portal.archive.common.base.model.DataModel;
import com.cloud.portal.archive.common.base.model.TabEntity;
import com.cloud.portal.archive.common.base.service.DataService;
import com.cloud.portal.archive.unitmenu.entity.UnitMenu;
import com.cloud.portal.archive.unitmenu.service.UnitMenuService;
import com.cloud.portal.common.constant.PortalConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * @author wengshij
 * @date Created in 2019/5/10 9:24
 * @description: 智能档案 -处理处理工具类
 * @modified By:wengshij
 */
@Component
@Slf4j
public class DataUtil {

    private static DataUtil dataUtil;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    private UnitMenuService unitMenuService;
    /**
     * 默认bean名称、默认为警综系统的七行八所
     */
    private static final String DEFAULT_BEAN_NAME = "gxdwServiceImpl";

    @PostConstruct
    public void init() {
        dataUtil = this;
        dataUtil.threadPoolTaskExecutor = this.threadPoolTaskExecutor;
        dataUtil.unitMenuService = this.unitMenuService;
    }

    /**
     * 初始化统计所有场所信息次数
     *
     * @param model
     * @return
     */
    public static List<TabEntity> initCount(DataModel model) {
        List<TabEntity> list = initTabList(Boolean.TRUE);
        if (null != list && list.size() > 0) {
            final CountDownLatch countDownLatch = new CountDownLatch(list.size());
            for (int i = 0; i < list.size(); i++) {
                int finalI = i;
                try {
                    dataUtil.threadPoolTaskExecutor.execute(() -> {
                        try {
                            DataService baseService = SpringContextHolder.getBean(list.get(finalI).getBeanName());
                            DataModel entity = new DataModel();
                            BeanUtils.copyProperties(model, entity);
                            entity.setDataScope(list.get(finalI).getDataScope());
                            int num = baseService.getCount(entity);
                            list.get(finalI).setNum(num);
                            list.get(finalI).setLabel(list.get(finalI).getLabel() + "（" + num + "）");
                        } catch (Exception e) {
                            list.get(finalI).setNum(0);
                            list.get(finalI).setLabel(list.get(finalI).getLabel() + "（0）");
                            log.error(e.getMessage());
                            e.printStackTrace();
                        } finally {
                            countDownLatch.countDown();
                        }
                    });
                } catch (Exception e) {
                    log.error(e.getMessage());
                }

            }
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 初始化 特行场所菜单分类列表
     *
     * @param initScope 是否初始化权限标识、
     * @return
     */
    public static List<TabEntity> initTabList(boolean initScope) {
        List<TabEntity> list = dataUtil.unitMenuService.findAll(PortalConstants.ALL_UNIT_MENU_CACHE_KEY)
                .stream()
                .filter(unitMenu -> StringUtils.isNotEmpty(unitMenu.getBeanName()) && !("-1").equals(unitMenu.getParentId())
                )
                .map(unit -> new TabEntity(unit.getIcon(), unit.getName() + (initScope == true ? "" : "（0）"), unit.getLinks(), unit.getId(), 0,
                        unit.getBeanName(),unit.getName(),
                        (initScope == true ? DataScopeUtil.getDataScopeSql(new DataScope(unit.getDataScope(), null)) : null)
                )).collect(Collectors.toList());
        return list;
    }

    /**
     * 智能档案初始化 分页数据
     *
     * @param page
     * @param model
     * @return
     */
    public static IPage initPage(IPage page, DataModel model) {
        //todo 通过配置的beanName 和权限字段 可获取列表
        UnitMenu unitMenu = dataUtil.unitMenuService.getById(model.getMenuId());
        String beanName = DEFAULT_BEAN_NAME;
        if (null != unitMenu) {
            beanName = unitMenu.getBeanName();
            model.setDataScope(DataScopeUtil.getDataScopeSql(new DataScope(unitMenu.getDataScope(), null)));
        }

        DataService baseService = SpringContextHolder.getBean(beanName);
        return baseService.findListPage(page, model);
    }

    /**
     * 智能档案-初始化根据主键获取场所数据
     *
     * @param model
     * @return
     */
    public static R initEntity(DataModel model) {
        UnitMenu unitMenu = null;
        String beanName = DEFAULT_BEAN_NAME;
        if (null != model && StringUtils.isNotBlank(model.getMenuId())) {
            unitMenu= dataUtil.unitMenuService.getById(model.getMenuId());
        }
        if (null != unitMenu) {
            beanName = unitMenu.getBeanName();
        }
        DataService baseService = SpringContextHolder.getBean(beanName);
        return new R<>(baseService.findOne(model));
    }

}
