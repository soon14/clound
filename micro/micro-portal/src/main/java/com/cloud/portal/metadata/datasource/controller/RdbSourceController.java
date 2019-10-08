package com.cloud.portal.metadata.datasource.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.metadata.datasource.Util.DbUtil;
import com.cloud.portal.metadata.datasource.constant.DataSourceEnum;
import com.cloud.portal.metadata.datasource.entity.RdbSource;
import com.cloud.portal.metadata.datasource.service.RdbSourceService;
import com.cloud.portal.metadata.standardfields.entity.Table;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/RdbSource")
public class RdbSourceController {
    private final RdbSourceService RdbSourceService;


    /**
     * 分页查询数据表字段
     *
     * @param page    参数集
     * @param rdbSource 查询参数列表
     * @return 数据表集合
     */
    @GetMapping("/page")
    @SysLog("数据源列表查询")
    public R getFieldPage(Page page, RdbSource rdbSource) {
        return new R<>(RdbSourceService.findFieldPage(page,rdbSource));
    }


    /**
     * 新增
     * @param rdbSource
     * @return R
     */
    @SysLog("新增数据源")
    @PostMapping("/add")
    public R save(@RequestBody RdbSource rdbSource){

        return new R<>(RdbSourceService.savedatasourceType(rdbSource));
    }

    /**
     * 修改
     * @param rdbSource
     * @return R
     */
    @SysLog("修改")
    @PutMapping("/put")
    public R updateById(@RequestBody RdbSource rdbSource){

        return new R<>(RdbSourceService.updatesourceById(rdbSource));
    }

    /**
     * 通过id删除
     * @param id type
     * @return R
     */
    @SysLog("删除")
    @SneakyThrows
    @DeleteMapping("removeById/{id}/{type}")
    public R removeById(@PathVariable String id,@PathVariable String type){
        return new R<>(RdbSourceService.removesourceById(id,type));
    }

    /**
     * 标准字段 测试连接数据库
     * @param dbId
     * @return R
     */
    @SysLog("测试连接数据库")
    @GetMapping("/checkDbConn/{dbId}")
    public RdbSource checkDbConn(@PathVariable String dbId){
        Table table = new Table();
        table.setDbId(dbId);
        RdbSource rdbSource = RdbSourceService.getById(table);
        return check(rdbSource);
    }

    /**
     * 测试
     * @param rdbSource
     * @return R
     */
    @SysLog("测试连接数据库")
    @GetMapping("/check")
    public RdbSource check(RdbSource rdbSource){
        rdbSource.setMessage("测试数据源连接成功");
        /**
         * 判断是否form表单上的测试链接
         */
        /*if(isForm==null){
            source = (E)service.get(source);
        }*/

        try {
            DbUtil.testConnWithoutRetry(rdbSource.getType(), rdbSource.getJdbcUrl(), rdbSource.getAccount(), rdbSource.getPwd());
        } catch (Exception e) {
            rdbSource.setMessage(e.getMessage());
        }
        return rdbSource;
    }

    /**
     * 根据数据库名称查询
     *
     * @param name 数据库名称
     * @return
     */
    @GetMapping("/getOneByName/{name}")
    public R getOneByName(@PathVariable String name) {
        RdbSource condition = new RdbSource();
        condition.setName(name);
        return new R<>(RdbSourceService.getOneByName(condition));
    }

}
