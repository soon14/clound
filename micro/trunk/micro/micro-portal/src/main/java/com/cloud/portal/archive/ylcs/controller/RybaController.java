package com.cloud.portal.archive.ylcs.controller;

import cn.hutool.core.io.IoUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.portal.archive.lvye.model.Employee;
import com.cloud.portal.archive.ylcs.entity.Ryba;
import com.cloud.portal.archive.ylcs.service.RybaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @author ryt
 * @date Created in 2019/5/9 17:34
 * @description: 娱乐场所-从业人员备案控制层
 * @modified By:
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ylcs/ryba")
public class RybaController {

    private final RybaService rybaService;

    /**
     * 分页查询人员备案列表
     * @param page
     * @param ryba
     * @return
     */
    @GetMapping("/findRyListPage")
    public R<IPage> getRybaPage(Page page, Ryba ryba){
        return new R<>(rybaService.findRybaPage(page,ryba));
    }


    /**
     * 通过单位ID查询人员的在职离职数量
     *
     * @param compId
     * @return
     */
    @GetMapping("/getRybaCount/{compId}/{csbabh}")
    public R getRybaCount(@PathVariable String compId,@PathVariable String csbabh){
        return new R<>(rybaService.getRybaCount(compId,csbabh));
    }

    /**
     * 通过人员ID查询人员的详细信息
     *
     * @param rybaId 人员ID
     * @return 单位详细信息
     */
    @GetMapping("/{rybaId}")
    public R getById(@PathVariable String rybaId) {
        return new R<>(rybaService.getById(rybaId));
    }

    /**
     * 获取娱乐场所从业人员 照片信息
     * @param rybaZp 照片ID
     * @return
     */
    @GetMapping("/img/{rybaZp}")
    public void getRyImg(@PathVariable String rybaZp, HttpServletResponse response) {
        try {
            InputStream in = null;
            Ryba ryba = rybaService.findRyImgById(rybaZp);
            if (null != ryba && null != ryba.getRybaZpContent()) {
                in = new ByteArrayInputStream(ryba.getRybaZpContent());
            }
            response.setContentType("application/octet-stream; charset=UTF-8");
            IoUtil.copy(in, response.getOutputStream());
        } catch (Exception e) {
        }
    }

}
