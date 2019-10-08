package com.cloud.common.security.util;

import cn.com.jit.cinas.authentication.credential.credentials.CertCredential;
import cn.com.jit.cinas.commons.util.Base64;
import cn.com.jit.ida.util.pki.cert.X509Cert;

/**
 * Created by Administrator on 2017/4/21.
 */
public class X509Utils {

    public static CertCredential getCertCredential(String attach, String detach, String original)  {
        CertCredential credential = new CertCredential();
        byte[] signedContent = (byte[])null;
        X509Cert x509Cert =null;
        try {
                signedContent = Base64.decode(detach);
                byte[] e = Base64.decode(original);
            x509Cert = CertUtil.getX509Certs(signedContent, e);
            credential.setCert(x509Cert);
            return credential;
        } catch (Exception e) {
           e.printStackTrace();

         }
        return null;

    }



}
