package com.cloud.portal.archive.mechanical.model;

import com.cloud.portal.archive.common.base.model.DataModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
/**
 * @author lvlinc
 * @date Created in 2019/5/9 17:34
 * @description: 机修承接车辆
 * @modified By:lvlinc
 */
@Data
public class Cjcl  extends DataModel<Cjcl>{
    /**业务流水号*/
    private String ywlsh;
    /**单位编码*/
    private String zagldwbm;
    /**单位名称*/
    private String dwmc;
    /**机动车所有人名称*/
    private String jdcsyrmc;
    /**机动车车辆类型*/
    private String jdccllxdm;
    /**车辆整车_照片*/
    private byte[] clzczp;
    /**中文品牌名称*/
    private String zwppmc;
    /**英文品牌名称*/
    private String ywppmc;
    /**车辆型号*/
    private String clxh;
    /**机动车车身车身颜色*/
    private String jdccsysdm;
    /**机动车车身车身颜色第一辅色*/
    private String jdccsysdyfsdm;
    /**机动车车身车身颜色第二辅色*/
    private String jdccsysdefsdm;
    /**机动车号牌号码*/
    private String jdchphm;
    /**机动车发动机号*/
    private String jdcfdjddjh;
    /**车辆识别代号*/
    private String clsbdh;
    /**机动车号牌种类代码*/
    private String jdchpzldm;
    /**机动车行驶公里数*/
    private String jdcxsgls;
    /**送修人_姓名*/
    private String sxrxm;
    /**送修人_证件号码*/
    private String sxrzjhm;
    /**送修人_常用证件代码*/
    private String sxrcyzjdm;
    /**送修人_联系电话*/
    private String sxrlxdh;
    /**送修时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sxsj;
    /**收车人_姓名*/
    private String scrxm;
    /**车辆故障_简要情况*/
    private String clgzjyqk;
    /**数据归属单位名称*/
    private String sjgsdwmc;
    /**数据归属单位代码*/
    private String sjgsdwdm;
    /**登记时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime djsj;
    /**更新时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gxsj;
    /**取车人姓名*/
    private String qcrxm;
    /**取车人_证件号码*/
    private String qcrzjhm;
    /**取车人_常用证件代码*/
    private String qcrcyzjdm;
    /**取车人_联系电话*/
    private String qcrlxdh;
    /**取车时间*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime qcsj;
    private String xxid;
    private String sjgxsm;
    private String xh;
    private String xh1;
    private String jjzt;
    private String cjcls;
    private String jjcls;

}
