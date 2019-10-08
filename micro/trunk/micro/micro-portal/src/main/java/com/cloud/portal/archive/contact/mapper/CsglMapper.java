package com.cloud.portal.archive.contact.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.portal.archive.contact.entity.Csgl;
import org.apache.ibatis.annotations.Param;

/**
 * @author ryt
 * @date Created in 2019/6/3 16:38
 * @description: 场所关联信息mapper
 * @modified By:
 */
public interface CsglMapper extends BaseMapper<Csgl> {

    /**
     * 分页查询场所关联列表
     *
     * @param page
     * @param csgl
     * @return
     */
    IPage<Csgl> findContactPage(IPage<Csgl> page, @Param("query") Csgl csgl);

    /**
     * 分页查询警综关联场所列表
     *
     * @param page
     * @param csgl
     * @return
     */
    IPage<Csgl> findJzContactPage(IPage<Csgl> page, @Param("query") Csgl csgl);

    /**
     * 分页查询场所关联列表
     *
     * @param page
     * @param csgl
     * @return
     */
    IPage<Csgl> findPage(IPage<Csgl> page, @Param("query") Csgl csgl);

    /**
     * 分页查询场所列表（娱乐系统、旅业、机修业）
     *
     * @param page
     * @param csgl
     * @return
     */
    IPage<Csgl> findJzPage(IPage<Csgl> page, @Param("query") Csgl csgl);

    /**
     * 添加关联
     *
     * @param csgl
     * @return success/false
     */
    Boolean saveCsgl(Csgl csgl);

    /**
     * 取消关联
     *
     * @param jzdwbh，qtdwbh
     * @return success/false
     */
    Boolean deleteCsgl(@Param("jzdwbh") String jzdwbh, @Param("qtdwbh") String qtdwbh);


    /**
     * 查询场所是否已被关联
     *
     * @param csgl
     * @return count
     */
    Integer findCsExists(@Param("query") Csgl csgl);

    /**
     * 自动关联
     *
     * @param
     * @return
     */
    void autoContact();

    /**
     * 根据 警综系统ID 或者其他业务系统ID 获取一条关联信息
     *
     * @param jzId      警综系统ID
     * @param defaultId 其他业务系统ID
     * @param containJoin 是否包含 其他业务系统的关联信息
     * @return
     */
    Csgl findEntityById(@Param("jzId") String jzId, @Param("defaultId") String defaultId,@Param("containJoin") boolean containJoin);


}
