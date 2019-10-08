package com.cloud.portal.archive.tyyh.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.cloud.admin.api.dto.TreeNode;
import com.cloud.portal.archive.common.base.model.DataModel;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lvlinc
 * @date Created in 2019/6/10
 * @description:
 * @modified By:lvlinc
 */
@Data
public class Tyjg extends Model<Tyjg> {
    private String id;
    private String pid;
    private String text;
    private String isLeaf;
    private String rootNodeId;
    private String isTreeNode;
    private String parentId;
}
