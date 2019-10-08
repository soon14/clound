package com.cloud.portal.judged.lib.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.admin.api.dto.DeptTree;
import com.cloud.portal.judged.lib.model.PlanLibrary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlanLibraryService extends IService<PlanLibrary> {
    IPage<List<PlanLibrary>> findPage(IPage<PlanLibrary> iPage, @Param("query")PlanLibrary planLibrary);

    /**
     * 更新数据源
     * @param planLibrary
     * @return
     */
    boolean insertPlanLibrary(PlanLibrary planLibrary);

    /**
     * 异步加载树菜单
     * @param planLibrary
     * @return 叶子节点数
     */
    List<DeptTree> fetchLibrarylist(PlanLibrary planLibrary);

}
