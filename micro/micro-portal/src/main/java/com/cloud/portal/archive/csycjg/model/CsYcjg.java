package com.cloud.portal.archive.csycjg.model;

import com.cloud.portal.archive.common.base.model.DataModel;
import lombok.Data;

/**
 * @author lvlinc
 * @date Created in 2019/6/5
 * @description:
 * @modified By:lvlinc
 */
@Data
public class CsYcjg extends DataModel<CsYcjg> {
    /**研判类型*/
     private String yplx;
     /**异常内容*/
     private String ycnr;
     /**预警时间*/
     private String yjsj;
     /**模型ID*/
     private String mxid;
     /**来源系统(警综、娱乐场所、机修、旅馆业)*/
     private String lyxt;
     /** 特行场所单位编号*/
     private String dwbh;
     private String xh;
}
