package com.cloud.cas.filter;


import cn.hutool.core.collection.CollectionUtil;
import com.cloud.cas.contant.CasConstant;
import com.cloud.cas.entity.CasUser;
import com.dragonsoft.dcuc.client.validation.DcucTicketValidationFilter;
import com.dragonsoft.dcuc.common.util.PropUtils;
import org.jasig.cas.client.validation.Assertion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author wengshij
 * @date Created in 2019/8/22 9:41
 * @description:
 * @modified By:wengshij
 */
public class LoginFilter extends DcucTicketValidationFilter {

    /**
     * 单点登录登录后的回调
     *
     * @param request
     * @param response
     * @param assertion
     * @return 返回要回调的前端地址（默认为sevice的地址）
     */
    @Override
    protected String onSuccessfulValidation(HttpServletRequest request, HttpServletResponse response, Assertion assertion) {
        String userId = assertion.getPrincipal().getName();
        CasUser casUser = new CasUser();
        casUser.setUserId(userId);
        Map<String, Object> infoMap = assertion.getPrincipal().getAttributes();
        if (CollectionUtil.isNotEmpty(infoMap)) {
            casUser.setIdCode((String) infoMap.get(CasConstant.TICKET_ID_CODE_PARAMS));
            casUser.setDeptCode((String) infoMap.get(CasConstant.TICKET_DEPT_CODE_PARAMS));
            casUser.setDeptName((String) infoMap.get(CasConstant.TICKET_POLICE_DEPT_NAME_PARAMS));
            casUser.setPoliceNumber((String) infoMap.get(CasConstant.TICKET_POLICE_NUMBER_PARAMS));
            casUser.setName((String) infoMap.get(CasConstant.TICKET_NAME_PARAMS));
            casUser.setPoliceCategory((String) infoMap.get(CasConstant.TICKET_POLICE_CATEGORY_PARAMS));
        }
        request.getSession().setAttribute(CasConstant.SESSION_USER_PARAMS, casUser);
        //返回前端地址
        return PropUtils.getInstance().getConfigItem(CasConstant.CAS_CLIENT_URL);
    }
}
