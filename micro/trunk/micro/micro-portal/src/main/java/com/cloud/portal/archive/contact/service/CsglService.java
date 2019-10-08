package com.cloud.portal.archive.contact.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.common.core.util.R;
import com.cloud.portal.archive.contact.entity.Csgl;
import org.apache.ibatis.annotations.Param;

/**
 * @author ryt
 * @date Created in 2019/6/3 16:40
 * @description: 场所关联信息service层
 * @modified By:
 */
public interface CsglService extends IService<Csgl> {

    /**
     * 分页查询场所关联列表
     * @param page
     * @param csgl
     * @return
     */
    IPage<Csgl> findContactPage(IPage<Csgl> page,Csgl csgl);

    /**
     * 分页查询场所列表
     * @param page
     * @param csgl
     * @return
     */
    IPage<Csgl> findPage(IPage<Csgl> page, Csgl csgl);

    /**
     * 分页查询警综场所关联列表
     * @param page
     * @param csgl
     * @return
     */
    IPage<Csgl> findJzContactPage(IPage<Csgl> page,Csgl csgl);

    /**
     * 分页查询警综场所列表
     * @param page
     * @param csgl
     * @return
     */
    IPage<Csgl> findJzPage(IPage<Csgl> page, Csgl csgl);

    /**
     * 保存场所关联
     * @param csgl
     * @return
     */
    R saveCsgl(Csgl csgl);

    /**
     * 保存场所关联-警综
     * @param csgl
     * @return
     */
    R saveJzCsgl(Csgl csgl);

    /**
     * 自动关联
     * @return
     */
    Boolean autoSave();

    /**
     * 取消关联
     *
     * @param jzdwbh,qtdwbh
     * @return success/false
     */
    Boolean deleteCsgl(String jzdwbh,String qtdwbh);


    /**
     * 根据 警综系统ID 或者其他业务系统ID 获取一条关联信息
     *
     * @param jzId      警综系统ID
     * @param defaultId 其他业务系统ID
      * @param containJoin 是否关联其他业务系统信息
     * @return
     */
    Csgl findEntityById( String jzId,  String defaultId,boolean containJoin);

}
