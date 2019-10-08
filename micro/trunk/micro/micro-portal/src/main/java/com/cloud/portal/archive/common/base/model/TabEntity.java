package com.cloud.portal.archive.common.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;

/**
 * @author wengshij
 * @date Created in 2019/5/8 14:51
 * @description:
 * @modified By:wengshij
 */
@Data
@AllArgsConstructor
public class TabEntity  implements Serializable {


    private String icon;
    private String label;
    private String prop;
    private String id;
    private Integer num;
    private String beanName;
    private String sourceName;
    @JsonIgnore
    private String dataScope;
}
