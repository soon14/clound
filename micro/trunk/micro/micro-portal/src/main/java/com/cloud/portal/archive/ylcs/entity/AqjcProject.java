package com.cloud.portal.archive.ylcs.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @author ryt
 * @date Created in 2019/5/21 14:54
 * @description: 娱乐场所-公安日常检查项,分为歌舞,游艺,按摩
 * @modified By:
 */
@Data
public class AqjcProject extends Model<AqjcProject> {
    private static final long serialVersionUID = 1L;

    /**主键*/
    private String proId;
    /**名称*/
    private String proName;
    /**单位id,公安添加的检查项后,再添加日常检查时加载出自己添加的检查项,初始定义的项目,compId值为0,即系统管理员增加的项目*/
    private String compId;
    /**单位名*/
    private String compName;
    /**公用项,值为1表示初始定义的检查项,不可删除;默认为0自定义检查项*/
    private Integer pbc;
    /**是否注销,0未注销,1已注销 预留*/
    private Integer del;
    /**检查类型是1歌舞,2游艺,3按摩*/
    private Integer leixing;
    /**安全检查类型排序标识,排序值*/
    private Integer proOrderby;
}
