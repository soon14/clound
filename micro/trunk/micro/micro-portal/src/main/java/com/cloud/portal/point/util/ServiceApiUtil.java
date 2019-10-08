package com.cloud.portal.point.util;

import com.cloud.common.core.util.SpringContextHolder;
import com.cloud.portal.point.index.model.PointIndex;
import com.cloud.portal.point.index.service.PointIndexService;
import com.cloud.portal.point.level.model.PointLevelChild;
import com.cloud.portal.point.level.service.PointLevelService;
import com.cloud.portal.point.score.model.PointScore;
import com.cloud.portal.point.score.service.PointScoreService;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

/**
 * @author wengshij
 * @date Created in 2019/7/26 10:41
 * @description:service层 接口方法 why:因为service层有些需要相互调用 会出现循环调用的BUG
 * 例如 积分pointScoreService会调用PointLevelService ，PointLevelService会调用pointScoreService 会出现死循环
 * @modified By:wengshij
 */
@Slf4j
@UtilityClass
public class ServiceApiUtil {

    private PointLevelService pointLevelService = SpringContextHolder.getBean(PointLevelService.class);

    private PointIndexService pointIndexService = SpringContextHolder.getBean(PointIndexService.class);

    private PointScoreService pointScoreService = SpringContextHolder.getBean(PointScoreService.class);


    /**
     * 根据特行场所类型 行政区划代码 和分值 获取等级评定信息
     *
     * @param placeType 场所类型
     * @param cityCode  行政区划代码
     * @param score     分值
     * @return
     */
    public PointLevelChild getPointLevel(String placeType, String cityCode, Integer score) {
        return pointLevelService.getLevel(placeType, cityCode, score);
    }

    /**
     * 根据主键ID获取指标信息
     *
     * @param id 主键ID
     * @return
     */
    public PointIndex getPointIndexById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        return pointIndexService.getById(id);
    }

    /**
     * 重置分数值
     * @param pointScore
     */
    public  void resetPlaceScore(PointScore pointScore){
        pointScoreService.resetPlaceScore(pointScore);
    }
}
