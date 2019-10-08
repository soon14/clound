
package com.cloud.portal.concern.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.common.core.util.R;
import com.cloud.common.log.annotation.SysLog;
import com.cloud.portal.concern.entity.PersonConcern;
import com.cloud.portal.concern.service.PersonConcernService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 *
 *
 * @author ygnet
 * @date 2019-02-27 10:48:28
 */
@RestController
@AllArgsConstructor
@RequestMapping("/personConcern")
public class PersonConcernController {

  private final PersonConcernService personConcernService;

  /**
   * 分页查询
   * @param page 分页对象
   * @param personConcern
   * @return
   */
  @GetMapping("/page")
  public R getPersonConcernPage(Page page,  PersonConcern personConcern) {
//    LambdaQueryWrapper<PersonConcern> conditions=new LambdaQueryWrapper<>(personConcern);
//    conditions.like(personConcern.getName()!=null,PersonConcern::getName, personConcern.getName());
//    conditions.like(personConcern.getName()!=null,PersonConcern::getName, personConcern.getName());
//
//
//    return  new R<>(personConcernService.page(page,Wrappers.query(new PersonConcern()).like(personConcern.getIdCard()!=null,PersonConcern::getIdCard, personConcern.getIdCard())));

      return new R<>(personConcernService.findPesonConcernPage(page,personConcern));

  }


  /**
   * 通过id查询
   * @param id id
   * @return R
   */
  @GetMapping("/{id}")
  public R getById(@PathVariable("id") String id){
    return new R<>(personConcernService.getById(id));
  }

  /**
   * 新增
   * @param personConcern
   * @return R
   */
  @SysLog("新增")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('concern_person_add')")
  public R save(@RequestBody PersonConcern personConcern){
//    return new R<>(personConcernService.save(personConcern));
    return new R<>(personConcernService.savePersonConcern(personConcern));
  }

  /**
   * 修改
   * @param personConcern
   * @return R
   */
  @SysLog("修改")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('concern_person_edit')")
  public R updateById(@RequestBody PersonConcern personConcern){
    return new R<>(personConcernService.updateById(personConcern));
  }

  /**
   * 通过id删除
   * @param id id
   * @return R
   */
  @SysLog("删除")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('concern_person_del')")
  public R removeById(@PathVariable String id){
    return new R<>(personConcernService.removeById(id));
  }


  /**
   * 根据证件类型查询
   *
   * @param idCard 用户名
   * @return
   */
  @GetMapping("/getOneByidCard/{idCard}")
  public R getOneByidCard(@PathVariable String idCard) {
    PersonConcern condition = new PersonConcern();
    condition.setIdCard(idCard);
    return new R<>(personConcernService.getOne(new QueryWrapper<>(condition)));
  }
}
