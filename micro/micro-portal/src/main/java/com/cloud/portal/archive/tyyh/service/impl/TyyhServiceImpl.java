package com.cloud.portal.archive.tyyh.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.portal.archive.common.base.service.impl.DataServiceImpl;
import com.cloud.portal.archive.tyyh.mapper.TyyhMapper;
import com.cloud.portal.archive.tyyh.model.HwToken;
import com.cloud.portal.archive.tyyh.model.TokenInfo;
import com.cloud.portal.archive.tyyh.model.Tyyh;
import com.cloud.portal.archive.tyyh.model.Utils;
import com.cloud.portal.archive.tyyh.service.TyyhService;
import org.apache.http.HttpException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cloud.portal.common.constant.PortalConstants.ACCESS_APP_KEY;
import static com.cloud.portal.common.constant.PortalConstants.ACCESS_SECRET_KEY;

/**
 * @author lvlinc
 * @date Created in 2019/6/6
 * @description:
 * @modified By:lvlinc
 */
@Service
public class TyyhServiceImpl extends DataServiceImpl<TyyhMapper, Tyyh> implements TyyhService {
    @Override
    public IPage<Tyyh> findPage(Page<Tyyh> page, Tyyh tyyh) {
        return baseMapper.findPage(page,tyyh);
    }

    @Override
    public boolean dowmUser(String orgid) {
        TokenInfo tokenInfo = null;
        try {
            tokenInfo = HwToken.getToken(ACCESS_APP_KEY,ACCESS_SECRET_KEY);
        } catch (HttpException e) {
            e.printStackTrace();
        }
        String accessToken = "Bearer "+tokenInfo.getAccessToken();
        String url = "http://68.26.19.197:8380/dcuc/api/users.action?orgid="+orgid;
        String userstr = Utils.httpGetUser(url,"Authorization",accessToken);
        List<Tyyh> ts = JSONArray.parseArray(userstr,Tyyh.class);
        for (Tyyh user:ts){
            baseMapper.dowmUser(user);
        }
        return true;
    }
}
