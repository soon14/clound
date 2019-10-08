package com.cloud.portal.archive.dict.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.cloud.admin.api.dto.TreeNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author lvlinc
 * @date Created in 2019-5-27
 * @description:
 * @modified By:lvlinc
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class Dict extends TreeNode implements Serializable {
    /**id*/
    private String id;
    /** 子Id */
    private String ids;
    /** 父Id */
    private String parentId;
    /** 层级父Id 使用,分割*/
    private String parentIds;
    /**字典类型*/
    private String codetype;
    /**字典代码*/
    private String code;
    /**字典名称*/
    private String title;
    /**字典title*/
    private String label;
    /**字典code*/
    private String value;
    /**字典codetype*/
    private String type;
    /**字典拼音*/
    private String spell;
    /**字典超码*/
    private String supercode;
    /**字典来源表*/
    private String lyb;
    /**字典来源*/
    private String lyxt;

}
