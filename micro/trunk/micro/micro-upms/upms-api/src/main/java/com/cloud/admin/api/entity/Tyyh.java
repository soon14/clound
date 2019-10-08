package com.cloud.admin.api.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.List;


/**
 * @author lvlinc
 * @date Created in 2019/6/6
 * @description:
 * @modified By:lvlinc
 */
@Data
public class Tyyh extends Model<Tyyh> {
    /** 编号*/
    private String id;
    /** 姓名*/
    private String name;
    /** 证件号码*/
    private String idCard;
    /** 民族*/
    private String nation;
    /** 性别*/
    private String sex;
    /** 出生日期*/
    private String birth;
    /** 创建时期*/
    private String createTime;
    /** 照片id*/
    private String photo;

    /**机构id,当userType等于30时，为施工人员所在项目的建设单位id*/
    private String orgId;

    /**机构名称,当userType等于30时，为施工人员所在项目的建设单位名称*/
    private String orgName;

    /**联系地址*/
    private String address;

    /**排序号*/
    private String sortNo;

    /**人员身份类型，引用表码4.6*/
    private String manType;

    /**警衔,引用表码4.7*/
    private String rank;

    /**职级,引用表码4.8*/
    private String title;

    /**责任民警ID*/
    private String policeId;

    /**责任民警姓名*/
    private String policeName;

    /**警号/辅警编号/施工人员编号*/
    private String policeNumber;

    /**施工人员所属公司id*/
    private String company;

    /**所属公司负责人*/
    private String companyPerson;

    /** 厂商代码*/
    private String companyCode;

    /** 单位编码*/
    private String orgCode;

    /**单位层级,引用表码4.3*/
    private String orgLevel;

    /** 业务域,引用码表4.14*/
    private String policeBusiness;

    /** 所属公司负责人电话*/
    private String personMobile;

    /** 施工人员所在施工项目id*/
    private String project;

    /**0：有效；1：删除*/
    private String deleted;

    /**警种，引用表码4.9*/
    private String policeCategory;

    /**辅警类型 （10：合同工、20：聘用、30：事业编、40返聘 90其它）*/
    private String auxiliaryType;

    /**省份，引用表码4.12*/
    private String area;

    /** 手机号码(私人号)*/
    private String mobilePrivate;

    /** 主手机号码(工作号)*/
    private String mobileWork;

    /** 座机*/
    private String phone;

    /**  QQ号*/
    private String qqAccount;

    /**  邮件*/
    private String email;

    /** 微信号*/
    private String wxAccount;

    /**在职类型（11：在职  12：兼职  13：挂职  14：返聘 15：退休 16：离职 17：死亡）*/
    private String jobType;

    /**  职务*/
    private String postType;

    /**出生日期String类型，格式yyyyMMdd*/
    private String brithStr;

    /**创建日期String类型，格式yyyyMMdd*/
    private String createTimeStr;

    /**施工人员所在项目id*/
    private String dutyProject;

    /**判断返回的 orgName和 orgId是否是该用户的主机构， 1：是、 0 ：否(警员专用)*/
    private String isParentOrg;

    List<Tyyh> userList;

    List<String> orgList;
}
