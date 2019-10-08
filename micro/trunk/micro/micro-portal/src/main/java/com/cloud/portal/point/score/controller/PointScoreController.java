package com.cloud.portal.point.score.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.admin.api.entity.SysUser;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.point.detail.model.IndexDetail;
import com.cloud.portal.point.score.model.PointScore;
import com.cloud.portal.point.score.service.PointScoreService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author wengshij
 * @date Created in 2019/7/24 9:57
 * @description: 特行场所 积分历史分值
 * @modified By:wengshij
 */
@RestController
@AllArgsConstructor
@RequestMapping("/point/score/")
@Api(value = "score")
public class PointScoreController {

    private final PointScoreService pointScoreService;

    /**
     * 重置 积分 所有分数值
     *
     * @return
     */
    @GetMapping("resetCurrentCycleScore")
    public R resetScore() {
        return pointScoreService.resetCurrentCycleScore();
    }

    @GetMapping("page")
    @SysLog("积分排名查询")
    @PreAuthorize("@pms.hasPermission('point_score_ranking')")
    public R page(Page page, PointScore pointScore) {
        return R.ok(pointScoreService.findCycleScorePage(page, pointScore));
    }

    /**
     * 获取当前场所信息
     *
     * @param placeId 场所ID
     * @return
     */
    @GetMapping("detail/{placeId}")
    public R scoreDetail(@PathVariable String placeId) {
        return R.ok(pointScoreService.findPlaceScore(placeId));
    }

    /**
     * 积分撤回功能
     *
     * @param indexDetail
     * @return
     */
    @PutMapping("rollBackScore")
    @SysLog("积分指标明细统计")
    public R rollBackScore(IndexDetail indexDetail) {
        return pointScoreService.rollBackScore(indexDetail);
    }

    /**
     * 获取当前场所信息
     *
     * @param placeId 场所ID
     * @return
     */
    @GetMapping("history/score/{placeId}")
    public R placeHistoryScore(@PathVariable String placeId) {
        return R.ok(pointScoreService.getPlaceAllScore(placeId));
    }

    @GetMapping("resetAllScore")
    public R resetAllScore() {
        pointScoreService.resetAllScore();
        return R.ok("分值重置成功");
    }

    /**
     * 根据场所获取当前周期积分
     *
     * @param placeId 场所ID
     * @return
     */
    @GetMapping("/current_score/{placeId}")
    public R getCurrentCycleScoreByPlaceId(@PathVariable String placeId) {
        return new R<>(pointScoreService.getCurrentCycleScoreByPlaceId(placeId));
    }

    @GetMapping("list")
    @SysLog("积分排名查询")
    @PreAuthorize("@pms.hasPermission('point_score_ranking')")
    public R list(PointScore pointScore) {
        return R.ok(pointScoreService.findCycleScoreList(pointScore));
    }

}
