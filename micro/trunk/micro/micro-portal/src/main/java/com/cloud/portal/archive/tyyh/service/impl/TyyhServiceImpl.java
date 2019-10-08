package com.cloud.portal.archive.tyyh.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.admin.api.entity.Tyyh;
import com.cloud.portal.archive.tyyh.mapper.TyyhMapper;
import com.cloud.portal.archive.tyyh.model.*;
import com.cloud.portal.archive.tyyh.service.TyyhService;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * @author lvlinc
 * @date Created in 2019/6/6
 * @description:
 * @modified By:lvlinc
 */
@Service
public class TyyhServiceImpl extends ServiceImpl<TyyhMapper, Tyyh> implements TyyhService {
    @Autowired
    private TyyhParameter tyyhParameter;
    @Override
    public List<Tyyh> findList(Tyyh tyyh) {
        TokenInfo tokenInfo = null;
        try {
            tokenInfo = HwToken.getToken(tyyhParameter.getAccesskey(),tyyhParameter.getSecretkey());
        } catch (HttpException e) {
            e.printStackTrace();
        }
        String accessToken = "Bearer "+tokenInfo.getAccessToken();
        String url = tyyhParameter.getOrgurl() + "?orgId=" + tyyh.getOrgId();
        String userstr = Utils.httpGetUser(url, "Authorization", accessToken);
        List<Tyyh> ts = JSONArray.parseArray(userstr, Tyyh.class);
        return ts;
    }


    @Override
    public boolean dowmUser(Tyyh tyyh) {
        TokenInfo tokenInfo = null;
        try {
            tokenInfo = HwToken.getToken(tyyhParameter.getAccesskey(),tyyhParameter.getSecretkey());
        } catch (HttpException e) {
            e.printStackTrace();
        }
        String accessToken = "Bearer "+tokenInfo.getAccessToken();
        for (String orgId:tyyh.getOrgList()) {
            String url = tyyhParameter.getOrgurl() + "?orgId=" + orgId;
            String userstr = Utils.httpGetUser(url, "Authorization", accessToken);
            List<Tyyh> ts = JSONArray.parseArray(userstr, Tyyh.class);
            for (Tyyh user : ts) {
                Tyyh tyyhlist = baseMapper.getid(user.getId());
                if (tyyhlist == null) {
                    baseMapper.dowmUser(user);
                } else {
                    baseMapper.updateUser(user);
                }
            }
        }
        return true;
    }

    @Override
    public boolean updateUser(Tyyh tyyh) {
        return baseMapper.updateUser(tyyh);
    }

    @Override
    public boolean dowmUserOne(Tyyh tyyh) {
            if (tyyh!=null) {
                for (Tyyh user:tyyh.getUserList()){

                    baseMapper.dowmUser(user);
                }
            }
        return false;
    }
}
