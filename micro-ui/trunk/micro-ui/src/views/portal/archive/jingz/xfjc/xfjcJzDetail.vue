<template>
    <div class="execution">
        <basic-container>
            <div class="avue-detail">
                <div class="avue-group avue-group--card">
                    <div class="el-row avue-group__item" span="24">
                        <div class="avue-group__header">
                            <i class="el-icon-info avue-group__icon">
                            </i><h1 class="avue-group__title">基本信息</h1>
                        </div>
                        <el-form class="form-main">
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="编号：">
                                        <span v-text="[formDetail.jcbh]"></span>第
                                        <span v-text="[formDetail.jcdh]"></span>号
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="单位名称：">
                                        <span v-text="formDetail.dwmc"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="主要负责人：">
                                        <span v-text="formDetail.zyfzr"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="单位地址：">
                                        <span v-text="formDetail.dwdz"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="单位性质：">
                                        <span v-text="formDetail.dwxz"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="检查类型：">
                                        <span v-text="formDetail.reservation04"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="建筑面积：">
                                        <span v-text="formDetail.jzmj"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="建筑层数：">
                                        <span v-text="formDetail.jzcs"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="建筑高度：">
                                        <span v-text="formDetail.jzgd"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <!--  <el-col :span="8">
                                      <el-form-item label="检查人员：">
                                          <span v-text="formDetail.jdjcry"></span>
                                      </el-form-item>
                                  </el-col>-->
                                <el-col :span="8">
                                    <el-form-item label="单位主管人员：">
                                        <span v-text="formDetail.dwzgry"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="检查日期：">
                                        <span v-text="formDetail.jcrq"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>
                           <!-- <el-col>
                                <el-col :span="8">
                                    <el-form-item label="所属警务区：">
                                        <span v-text="formDetail.reservation24"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="所属社区：">
                                        <span v-text="formDetail.reservation18"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>-->
                        </el-form>
                    </div></div>

                <div class="avue-group avue-group--card">
                    <div class="el-row avue-group__item" span="24">
                        <div class="avue-group__header">
                            <i class="el-icon-info avue-group__icon">
                            </i><h1 class="avue-group__title">检查详情</h1>
                        </div>
                        <div class="el-table el-table--fit el-table--border ">
                            <div class="el-table__body-wrapper is-scrolling-none">
                        <table cellspacing="0" cellpadding="0" border="0" class="targetModel el-table__body" style="width: 100%">
                            <tbody>
                            <tr class="">
                                <td rowspan="2" width="250" >单位履行消防安全职责情况</td>
                                <td width="180">合法性</td>
                                <td>
                                    <div>
                                        <el-form>
                                            <el-col>
                                                <el-form-item label="被查建筑物名称：">
                                                    <span v-text="formDetail.reservation09"></span>
                                                  <!--  <el-input style="width: 80%;"
                                                            v-model="formDetail.reservation09"
                                                            :disabled="true">
                                                    </el-input>-->
                                                </el-form-item>
                                            </el-col>
                                            <el-col>
                                                <el-form-item>
                                                    <template>
                                                        <el-checkbox-group v-model="jzwxfys">
                                                            <el-checkbox label="01"  disabled>1998年9月1日之前竣工建筑且此后来改建（含建筑、用途变更）</el-checkbox><br>
                                                            <el-checkbox label="02"  disabled>依法通过消防验收</el-checkbox><br>
                                                            <el-checkbox label="03"  disabled>依法进行竣工验收消防备案</el-checkbox>
                                                        </el-checkbox-group>
                                                    </template>
                                                </el-form-item>
                                            </el-col>
                                            <el-col>
                                                <el-form-item label="其他情况：">
                                                    <span v-text="formDetail.reservation10"></span>
                                                </el-form-item>
                                            </el-col>
                                            <el-col>
                                                <el-form-item label="公众聚集场所：" :prop="formDetail.reservation11">
                                                    <el-radio disabled  v-model="formDetail.reservation11" label="01">是</el-radio>
                                                    <el-radio disabled  v-model="formDetail.reservation11" label="02">否</el-radio>
                                                </el-form-item>
                                            </el-col>
                                            <el-col>
                                                <el-form-item label="依法通过投入使用、营业前消防安全检查：" :prop="formDetail.reservation12">
                                                    <el-radio disabled  v-model="formDetail.reservation12" label="01">是</el-radio>
                                                    <el-radio disabled  v-model="formDetail.reservation12" label="02">否</el-radio>
                                                </el-form-item>
                                            </el-col>
                                        </el-form>
                                    </div>
                                </td>
                            </tr>
                            <tr class="">
                                <td>消防安全管理</td>
                                <td>
                                    <div>
                                        <el-form>
                                            <el-col>
                                                <el-form-item label="1.消防安全制度：" :prop="formDetail.xfaqzd">
                                                    <el-radio disabled  v-model="formDetail.xfaqzd" label="01">有</el-radio>
                                                    <el-radio disabled  v-model="formDetail.xfaqzd" label="02">无</el-radio>
                                                </el-form-item>
                                            </el-col>
                                        <el-col>
                                            <el-form-item label="2.员工消防安全教育培训：" :prop="formDetail.ygxfaqjypx">
                                                <el-radio disabled  v-model="formDetail.ygxfaqjypx" label="01">组织开展</el-radio>
                                                <el-radio disabled  v-model="formDetail.ygxfaqjypx" label="02">未组织开展</el-radio>
                                            </el-form-item>
                                        </el-col>
                                        <el-col>
                                            <el-form-item label="3.防火检查：" :prop="formDetail.fhjc">
                                                <el-radio disabled  v-model="formDetail.fhjc" label="01">组织开展</el-radio>
                                                <el-radio disabled  v-model="formDetail.fhjc" label="02">未组织开展</el-radio>
                                            </el-form-item>
                                        </el-col>
                                        <el-col>
                                            <el-form-item label="4.灭火和应急疏散预案：" :prop="formDetail.mhhyjssya">
                                                <el-radio disabled  v-model="formDetail.mhhyjssya" label="01">有</el-radio>
                                                <el-radio disabled  v-model="formDetail.mhhyjssya" label="02">无</el-radio>
                                            </el-form-item>
                                        </el-col>
                                        <el-col>
                                            <el-form-item label="5.消防演练：" :prop="formDetail.reservation17">
                                                <el-radio disabled  v-model="formDetail.reservation17" label="01">组织</el-radio>
                                                <el-radio disabled  v-model="formDetail.reservation17" label="02">未组织</el-radio>
                                            </el-form-item>
                                        </el-col>
                                        <el-col>
                                            <el-form-item label="6.消防控制室是否有人值班，人员是否持有相关证件：" :prop="formDetail.xfkzs">
                                                <el-radio disabled  v-model="formDetail.xfkzs" label="1">是</el-radio>
                                                <el-radio disabled  v-model="formDetail.xfkzs" label="0">否</el-radio>
                                            </el-form-item>
                                        </el-col>
                                        <el-col>
                                            <el-form-item label="7.公众聚集场所是否违反规定使用明火作业：" :prop="formDetail.gzjjcsmhzy">
                                                <el-radio disabled  v-model="formDetail.gzjjcsmhzy" label="1">是</el-radio>
                                                <el-radio disabled  v-model="formDetail.gzjjcsmhzy" label="0">否</el-radio>
                                            </el-form-item>
                                        </el-col>
                                        <el-col>
                                            <el-form-item label="8.违规用火用电用气，经营场所电器线路敷设未穿阻燃线管保护，用火线入口端没有安装漏电开关：" :prop="formDetail.wgyhydyq">
                                                <el-radio disabled  v-model="formDetail.wgyhydyq" label="1">是</el-radio>
                                                <el-radio disabled  v-model="formDetail.wgyhydyq" label="0">否</el-radio>
                                            </el-form-item>
                                        </el-col>
                                        <el-col>
                                            <el-form-item label="9.其他情况：" :prop="formDetail.xfaqqtqk">
                                               <span v-text="formDetail.xfaqqtqk"></span>
                                            </el-form-item>
                                        </el-col>
                                        </el-form>
                                     </div>
                                </td>
                            </tr>
                            <tr class="">
                                <td rowspan="3" width="250"></td>
                                <td width="180">建筑防火</td>
                                <td>
                                    <div>
                                        <el-form>
                                            <el-col>
                                                <el-form-item label="1.消防车通道：" :prop="formDetail.xfctd">
                                                    <el-radio disabled  v-model="formDetail.xfctd" label="01">畅通</el-radio>
                                                    <el-radio disabled  v-model="formDetail.xfctd" label="02">被堵塞、占用</el-radio>
                                                    <el-radio disabled  v-model="formDetail.xfctd" label="03">无</el-radio>
                                                </el-form-item>
                                            </el-col>
                                            <el-col>
                                                <el-form-item label="2.疏散通道：" :prop="formDetail.sshaqtd">
                                                    <el-radio disabled  v-model="formDetail.sshaqtd" label="01">畅通</el-radio>
                                                    <el-radio disabled  v-model="formDetail.sshaqtd" label="02">堵塞</el-radio>
                                                    <el-radio disabled  v-model="formDetail.sshaqtd" label="03">锁闭</el-radio>
                                                </el-form-item>
                                            </el-col>
                                            <el-col>
                                                <el-form-item label="3.安全出口：" :prop="formDetail.reservation13">
                                                    <el-radio disabled  v-model="formDetail.reservation13" label="01">畅通</el-radio>
                                                    <el-radio disabled  v-model="formDetail.reservation13" label="02">堵塞</el-radio>
                                                    <el-radio disabled  v-model="formDetail.reservation13" label="03">锁闭</el-radio>
                                                    <el-radio disabled  v-model="formDetail.reservation13" label="04">缺少</el-radio>
                                                </el-form-item>
                                            </el-col>
                                            <el-col>
                                                <el-form-item label="4.防火门：" :prop="formDetail.fhmqk">
                                                    <el-radio disabled  v-model="formDetail.fhmqk" label="01">完好有效</el-radio>
                                                    <el-radio disabled  v-model="formDetail.fhmqk" label="02">常闭式防火门常开</el-radio>
                                                    <el-radio disabled  v-model="formDetail.fhmqk" label="03">损坏</el-radio>
                                                    <el-radio disabled  v-model="formDetail.fhmqk" label="04">不涉及</el-radio>
                                                </el-form-item>
                                            </el-col>
                                            <el-col>
                                                <el-form-item label="5.疏散指示标志：" :prop="formDetail.sszsbz">
                                                    <el-radio disabled  v-model="formDetail.sszsbz" label="01">完好有效</el-radio>
                                                    <el-radio disabled  v-model="formDetail.sszsbz" label="02">损坏</el-radio>
                                                    <el-radio disabled  v-model="formDetail.sszsbz" label="03">缺少</el-radio>
                                                    <el-radio disabled  v-model="formDetail.sszsbz" label="04">无</el-radio>
                                                </el-form-item>
                                            </el-col>
                                            <el-col>
                                                <el-form-item label="6.应急照明：" :prop="formDetail.yjzm">
                                                    <el-radio disabled  v-model="formDetail.yjzm" label="01">完好有效</el-radio>
                                                    <el-radio disabled  v-model="formDetail.yjzm" label="02">损坏</el-radio>
                                                    <el-radio disabled  v-model="formDetail.yjzm" label="03">缺少</el-radio>
                                                    <el-radio disabled  v-model="formDetail.yjzm" label="04">无</el-radio>
                                                </el-form-item>
                                            </el-col>
                                            <el-col>
                                                <el-form-item label="7.人员密集场所外墙门窗上是否设置影响逃生、灭火救援的障碍物：" :prop="formDetail.reservation14">
                                                    <el-radio disabled  v-model="formDetail.reservation14" label="01">否</el-radio>
                                                    <el-radio disabled  v-model="formDetail.reservation14" label="02">是</el-radio>
                                                    <el-radio disabled  v-model="formDetail.reservation14" label="03">不涉及</el-radio>
                                                </el-form-item>
                                            </el-col>
                                            <el-col>
                                                <el-form-item label="8.其他情况：">
                                                  <span v-text="formDetail.jzfhqtqk"></span>
                                                </el-form-item>
                                            </el-col>
                                        </el-form>
                                    </div>
                                </td>
                            </tr>
                            <tr class="">
                                <td width="180">消防设施</td>
                                <td>
                                    <div>
                                        <el-form>
                                            <el-col>
                                                <el-form-item label="1.室内消火栓：">
                                                <template>
                                                <el-checkbox-group v-model="snxhs">
                                                    <el-checkbox label="01"  disabled>未设置</el-checkbox>
                                                    <el-checkbox label="02"  disabled>完好有效</el-checkbox>
                                                    <el-checkbox label="03"  disabled>损坏</el-checkbox>
                                                    <el-checkbox label="04"  disabled>无水</el-checkbox>
                                                    <el-checkbox label="05"  disabled>配件不齐</el-checkbox>
                                                    <el-checkbox label="06"  disabled>被遮挡、圈占</el-checkbox>
                                                    <el-checkbox label="07"  disabled>不涉及</el-checkbox>
                                                </el-checkbox-group>
                                                </template>
                                                </el-form-item>
                                            </el-col>
                                            <el-col>
                                                <el-form-item label="2.灭火器：">
                                                    <template>
                                                        <el-checkbox-group v-model="mhq">
                                                            <el-checkbox label="01" disabled>未配置</el-checkbox>
                                                            <el-checkbox label="02" disabled>完好有效</el-checkbox>
                                                            <el-checkbox label="03" disabled>失效</el-checkbox>
                                                            <el-checkbox label="04" disabled>缺少</el-checkbox>
                                                            <el-checkbox label="05" disabled>配置类型错误</el-checkbox>
                                                            <el-checkbox label="06" disabled>设置地点不当</el-checkbox>
                                                        </el-checkbox-group>
                                                    </template>
                                                </el-form-item>
                                            </el-col>
                                            <el-col>
                                                <el-form-item label="3.建筑消防设施：" :prop="formDetail.jzwxfss">
                                                        <el-radio disabled  v-model="formDetail.jzwxfss" label="01">定期维修保养并记录</el-radio>
                                                        <el-radio disabled  v-model="formDetail.jzwxfss" label="02">无记录</el-radio>
                                                        <el-radio disabled  v-model="formDetail.jzwxfss" label="03">未定期维修保养</el-radio>
                                                        <el-radio disabled  v-model="formDetail.jzwxfss" label="04">不涉及</el-radio>
                                                 </el-form-item>
                                            </el-col>
                                            <el-col>
                                                <el-form-item label="4.物业服务企业对管理区域内共用消防设施是否维护管理：" :prop="formDetail.xfsswhgl">
                                                    <el-radio disabled  v-model="formDetail.xfsswhgl" label="01">是</el-radio>
                                                    <el-radio disabled  v-model="formDetail.xfsswhgl" label="02">否</el-radio>
                                                    <el-radio disabled  v-model="formDetail.xfsswhgl" label="03">不涉及</el-radio>
                                                </el-form-item>
                                            </el-col>
                                        </el-form>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td width="180">危险品管理</td>
                                <td>
                                    <div>
                                        <el-form>
                                            <el-col>
                                                <el-form-item label="1.是否存在违反规定使用明火作业或在具有火灾、爆炸危险 的场所吸烟、使用明火：" :prop="formDetail.reservation15">
                                                    <el-radio disabled  v-model="formDetail.reservation15" label="01">否</el-radio>
                                                    <el-radio disabled  v-model="formDetail.reservation15" label="02">是</el-radio>
                                                    <el-radio disabled  v-model="formDetail.reservation15" label="03">不涉及</el-radio>
                                                </el-form-item>
                                            </el-col>
                                            <el-col>
                                                <el-form-item label="2.是否存在违反消防安全规定进入生产、储存易燃易爆危险品场所：" :prop="formDetail.reservation16">
                                                    <el-radio disabled  v-model="formDetail.reservation16" label="01">否</el-radio>
                                                    <el-radio disabled  v-model="formDetail.reservation16" label="02">是</el-radio>
                                                    <el-radio disabled  v-model="formDetail.reservation16" label="03">不涉及</el-radio>
                                                </el-form-item>
                                            </el-col>
                                            <el-col>
                                                <el-form-item label="3.生产、储存、经营易燃易爆危险品的场所是否与居住场所设置在同一建筑物内：" :prop="formDetail.wxpyjzcsyz">
                                                    <el-radio disabled  v-model="formDetail.wxpyjzcsyz" label="01">否</el-radio>
                                                    <el-radio disabled  v-model="formDetail.wxpyjzcsyz" label="02">是</el-radio>
                                                    <el-radio disabled  v-model="formDetail.wxpyjzcsyz" label="03">不涉及</el-radio>
                                                </el-form-item>
                                            </el-col>
                                        </el-form>
                                    </div>
                                </td>
                            </tr>
                            <tr class="">
                                <td>村（居)民委员会履行消防安全职责情况</td>
                                <td colspan="2">
                                    <div>
                                        <el-form>
                                            <el-col>
                                                <el-form-item label="1.消防安全管理人：" :prop="formDetail.xfaqglr">
                                                    <el-radio disabled  v-model="formDetail.xfaqglr" label="01">确定</el-radio>
                                                    <el-radio disabled  v-model="formDetail.xfaqglr" label="02">未确定</el-radio>
                                                </el-form-item>
                                            </el-col>
                                            <el-col>
                                                <el-form-item label="2.消防安全工作制度：" :prop="formDetail.xfangzzd">
                                                    <el-radio disabled  v-model="formDetail.xfangzzd" label="01">有</el-radio>
                                                    <el-radio disabled  v-model="formDetail.xfangzzd" label="02">无</el-radio>
                                                </el-form-item>
                                            </el-col>
                                            <el-col>
                                                <el-form-item label="3.防火安全公约：" :prop="formDetail.fhaqgy">
                                                    <el-radio disabled  v-model="formDetail.fhaqgy" label="01">有</el-radio>
                                                    <el-radio disabled  v-model="formDetail.fhaqgy" label="02">无</el-radio>
                                                </el-form-item>
                                            </el-col>
                                            <el-col>
                                                <el-form-item label="4.消防宣传教育：" :prop="formDetail.xfxcjy">
                                                    <el-radio disabled  v-model="formDetail.xfxcjy" label="01">开展</el-radio>
                                                    <el-radio disabled  v-model="formDetail.xfxcjy" label="02">未开展</el-radio>
                                                </el-form-item>
                                            </el-col>
                                            <el-col>
                                                <el-form-item label="5.防火安全检查：" :prop="formDetail.yzy">
                                                    <el-radio disabled  v-model="formDetail.yzy" label="01">开展</el-radio>
                                                    <el-radio disabled  v-model="formDetail.yzy" label="02">未开展</el-radio>
                                                </el-form-item>
                                            </el-col>
                                            <el-col>
                                                <el-form-item label="6.消防水源、消防车通道、消防器材：" :prop="formDetail.xfsy">
                                                    <el-radio disabled  v-model="formDetail.xfsy" label="01">维护管理</el-radio>
                                                    <el-radio disabled  v-model="formDetail.xfsy" label="02">未维护管理</el-radio>
                                                </el-form-item>
                                            </el-col>
                                            <el-col>
                                                <el-form-item label="7.多种形式消防组织：" :prop="formDetail.dzxsxfzz">
                                                    <el-radio disabled  v-model="formDetail.dzxsxfzz" label="01">建立</el-radio>
                                                    <el-radio disabled  v-model="formDetail.dzxsxfzz" label="02">未建立</el-radio>
                                                </el-form-item>
                                            </el-col>
                                        </el-form>
                                    </div>
                                </td>
                            </tr>
                            <tr class="">
                                <td>责令更改情况</td>
                                <td colspan="2">制发的法律文书名称和编号：
                                    <span v-text="formDetail.zlgzbh"></span>
                                </td>
                            </tr>
                            <tr class="">
                                <td>移送消防机构处理的内容</td>
                                <td colspan="2">
                                    <div>
                                        <el-form>
                                            <el-col>
                                                <el-form-item>
                                                    <label class="el-form-item__label">发现的下列第</label>
                                                    <el-input
                                                            style="width: 100px;"
                                                            v-model="formDetail.ysxfjgx"
                                                            :disabled="true">
                                                    </el-input>
                                                    <label>&nbsp;&nbsp;项消防安全违法行为，移送公安机关消防机构依法处理：</label>
                                                </el-form-item>
                                            </el-col>
                                            <el-col>
                                                <el-form-item>
                                                    <template>
                                                        <el-checkbox-group v-model="ysxfjgxlnr">
                                                            <el-checkbox label="1" disabled>1.建筑物未依法通过公安机关消防机构消防验收，擅自投入使用</el-checkbox><br>
                                                            <el-checkbox label="2" disabled>2.建筑物未依法进行竣工验收消防备案</el-checkbox><br>
                                                            <el-checkbox label="3" disabled>3.公众聚集场所未依法通过使用、营业前消防安全检查，擅自投入使用、营业</el-checkbox>
                                                        </el-checkbox-group>
                                                    </template>
                                                </el-form-item>
                                            </el-col>
                                        </el-form>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="avue-group avue-group--card">
                    <div class="el-row avue-group__item" span="24">
                        <div class="avue-group__header">
                            <i class="el-icon-info avue-group__icon">
                            </i><h1 class="avue-group__title">检查结果</h1>
                        </div>
                        <el-form class="form-main">
                            <el-col>
                                <el-col :span="8">
                                    <el-form-item label="是否合格：">
                                        <span v-text="formDetail.reservation02"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="处理方式：">
                                        <span v-text="formDetail.reservation21"></span>
                                    </el-form-item>
                                </el-col>
                                <el-col :span="8">
                                    <el-form-item label="处理建议：">
                                        <span v-text="formDetail.reservation22"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>
                            <el-col>
                                <el-col :span="24">
                                    <el-form-item label="备注：">
                                        <span v-text="formDetail.bz"></span>
                                    </el-form-item>
                                </el-col>
                            </el-col>
                        </el-form>
                    </div>
                </div>

            </div>
        </basic-container>
    </div>
</template>


<script>


    import {getDict,findLabel,getLabel}  from  "@/api/portal/archive/dict/dict";
    import {getXfjcDetail} from "@/api/portal/archive/jingz/xfjc";
    import { validatenull } from "@/util/validate";

    export default {
        name: 'xfjcJzDetail',
        props:["param"],
        data(){
            return {
                formDetail:{},
                jzwxfys:[],
                snxhs:[],//室内消火栓
                mhq:[],//灭火器
                ysxfjgxlnr:[]
            }
        },
        created(){
            this.getFormDetail(this.param.systemId)
        },

        watch: {
            param: {
                //深度监控父组件传递过来的值、否则无法根据传递过来的值更新数据
                handler() {
                    this.getFormDetail(this.param.systemId)
                },
                deep: true
            },
        },
        methods: {
            getFormDetail(id) {
                getXfjcDetail(id).then(response => {
                    this.formDetail = response.data.data
                    this.initData()
                    this.jzwxfys=validatenull(this.formDetail.jzwxfys)?[]:this.formDetail.jzwxfys.split(",")
                    this.snxhs=validatenull(this.formDetail.snxhs)?[]:this.formDetail.snxhs.split(",")
                    this.mhq=validatenull(this.formDetail.mhq)?[]:this.formDetail.mhq.split(",")
                    this.ysxfjgxlnr=validatenull(this.formDetail.ysxfjgxlnr)?[]:this.formDetail.ysxfjgxlnr.split(",")
                })
            },
            initData() {
                findLabel('za_001', 'jzxt', this.formDetail.dwxz).then(value => {
                    this.formDetail.dwxz = value
                }).catch((err) => {
                });
                findLabel('XF_022', 'jzDict', this.formDetail.reservation04).then(value => {
                    this.formDetail.reservation04 = value
                }).catch((err) => {
                });
                findLabel('XF_020', 'jzDict', this.formDetail.reservation02).then(value => {
                    this.formDetail.reservation02 = value
                }).catch((err) => {
                });

            },

        }
    }
</script>

<style>
    .el-col{
        margin: 0px !important;
    }
    .el-form-item{
        margin-bottom: 10px!important;
    }
    .el-radio {
        color: #606266;
        font-weight: 500;
        line-height: 1;
        cursor: pointer;
        white-space: nowrap;
        outline: 0;
        margin-right: 12px !important;
    }

    .el-checkbox {
        font-size: 14px;
        cursor: pointer;
        user-select: none;
        margin-right: 10px!important;
    }
</style>