package com.cloud.common.security.util;


import cn.com.jit.ida.util.pki.PKIException;
import cn.com.jit.ida.util.pki.Parser;
import cn.com.jit.ida.util.pki.asn1.ASN1InputStream;
import cn.com.jit.ida.util.pki.asn1.ASN1Sequence;
import cn.com.jit.ida.util.pki.asn1.ASN1Set;
import cn.com.jit.ida.util.pki.asn1.pkcs.pkcs7.ContentInfo;
import cn.com.jit.ida.util.pki.asn1.pkcs.pkcs7.IssuerAndSerialNumber;
import cn.com.jit.ida.util.pki.asn1.pkcs.pkcs7.SignedData;
import cn.com.jit.ida.util.pki.asn1.pkcs.pkcs7.SignerInfo;
import cn.com.jit.ida.util.pki.asn1.x509.SubjectKeyIdentifier;
import cn.com.jit.ida.util.pki.asn1.x509.SubjectPublicKeyInfo;
import cn.com.jit.ida.util.pki.asn1.x509.X509CertificateStructure;
import cn.com.jit.ida.util.pki.asn1.x509.X509Name;
import cn.com.jit.ida.util.pki.cert.X509Cert;
import cn.com.jit.ida.util.pki.cipher.JKey;
import cn.com.jit.ida.util.pki.encoders.Base64;
import cn.com.jit.ida.util.pki.pkcs.PKCS7SignedData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.HashMap;

public class CertUtil {
    private static final Log log = LogFactory.getLog(CertUtil.class);

    public CertUtil() {
    }

    public static final X509Cert getPKICert(X509Certificate cert) {
        X509Cert pkiCert = null;

        try {
            pkiCert = new X509Cert(cert.getEncoded());
            if (log.isDebugEnabled()) {
                log.debug("[PKICert DN] : " + pkiCert.getSubject());
            }
        } catch (CertificateEncodingException var3) {
            log.error("PKI[Cert] - > X509Certificate is failed!", var3);
        } catch (PKIException var4) {
            log.error("PKI[Cert] - > X509Certificate is failed!", var4);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pkiCert;
    }

    public static final X509Certificate getX509Certificate(X509Cert cert) {
        try {
            byte[] encoded = cert.getEncoded();
            CertificateFactory factory = CertificateFactory.getInstance("X.509");
            return (X509Certificate) factory.generateCertificate(new ByteArrayInputStream(encoded));
        } catch (PKIException var4) {
            log.error("Get X509Certificate is failed!", var4);
        } catch (CertificateException var5) {
            log.error("Get X509Certificate is failed!", var5);
        }

        return null;
    }

    public static byte[] cleanAndDecodeBase64(byte[] data) {
        if (data == null) {
            throw new NullPointerException();
        } else if (Parser.isBase64Encode(data)) {
            if (log.isDebugEnabled()) {
                log.debug("decoding base64 string");
            }

            byte[] buf = new byte[data.length];
            System.arraycopy(data, 0, buf, 0, data.length);
            buf = Parser.convertBase64(buf);
            buf = Base64.decode(buf);
            return buf;
        } else {
            return data;
        }
    }

    public static String getIssuerAndSerialNumberString(X509Cert cert) {
        BigInteger sn = cert.getSerialNumber();
        X509Name issuer = new X509Name(cert.getIssuer());
        IssuerAndSerialNumber iasn = new IssuerAndSerialNumber(issuer, sn);
        StringBuilder sb = new StringBuilder();
        sb.append(iasn.getName().toString());
        sb.append(iasn.getCertificateSerialNumber().getValue().toString());
        return sb.toString();
    }

    public static String getSubjectKeyIdentifierBase64String(X509Cert cert) throws PKIException {
        JKey publicKey = cert.getPublicKey();
        SubjectPublicKeyInfo subjectPublicKeyInfo = Parser.key2SPKI(publicKey);
        SubjectKeyIdentifier id = new SubjectKeyIdentifier(subjectPublicKeyInfo);
        byte[] temp = id.getKeyIdentifier();
        byte[] tempBase64 = Base64.encode(temp);
        return new String(tempBase64);
    }

    public static HashMap<String, X509Cert> getX509Certs(SignedData signedData) {
        HashMap tmap = new HashMap(1);
        String tIssuerAndSN = "";
        ASN1Set certSet = signedData.getCertificates();
        Enumeration enumeration = certSet.getObjects();
        X509Cert cert = null;
        Object tobject = null;
        while (enumeration.hasMoreElements()) {
            tobject = enumeration.nextElement();
            if (tobject != null) {
                try {
                    X509CertificateStructure certStruc = X509CertificateStructure.getInstance(tobject);
                    cert = new X509Cert(certStruc);
                    tIssuerAndSN = cert.getIssuer() + cert.getSerialNumber().toString();
                    tmap.put(tIssuerAndSN, cert);
                } catch (Exception var8) {
                    ;
                }
            }
        }

        return tmap;
    }



    public static X509Cert getX509Certs(byte[] signedContent, byte[] srcContent) throws Exception {
        SignedData signedData = null;
        ASN1InputStream asn1Input = null;
        ByteArrayInputStream byteInput = null;
        X509Cert tIsValid = null;
        ASN1Sequence sequence = null;
        ContentInfo contentInfo = null;
        byteInput = new ByteArrayInputStream(signedContent);
        asn1Input = new ASN1InputStream(byteInput);
        sequence = (ASN1Sequence) asn1Input.readObject();
        contentInfo = ContentInfo.getInstance(sequence);
        signedData = SignedData.getInstance(contentInfo.getContent());
        tIsValid = verifySign(signedData, srcContent);
       return tIsValid;
    }





    public static SignedData getSignedData(byte[] signedContent) throws IOException {
        SignedData signedData = null;
        ASN1InputStream asn1Input = null;
        ByteArrayInputStream byteInput = null;
        ASN1Sequence sequence = null;
        ContentInfo contentInfo = null;
        byteInput = new ByteArrayInputStream(signedContent);
        asn1Input = new ASN1InputStream(byteInput);
        sequence = (ASN1Sequence) asn1Input.readObject();
        contentInfo = ContentInfo.getInstance(sequence);
        signedData = SignedData.getInstance(contentInfo.getContent());
        return signedData;
    }

    public static HashMap<String, X509Cert> getX509Certs(byte[] signedContent) throws IOException {
        return getX509Certs(getSignedData(signedContent));
    }

    public static X509Cert verifySign(SignedData signedData, byte[] srcContent) throws Exception {
        return verifySign((SignedData) signedData, srcContent, (HashMap) null);
    }




    public static X509Cert verifySign(SignedData signedData, byte[] srcContent, HashMap<String, X509Cert> certsMap) throws Exception {
        PKCS7SignedData pkcs7 = null;
        SignerInfo signerinfo = null;
        ASN1Set signerSet = null;
        HashMap tcertsmap = null;
        boolean tIsValid = false;
        X509Cert cert = null;
        String tIssuerAndSN = "";
        tcertsmap = getX509Certs(signedData);

        try {
            signerSet = signedData.getSignerInfos();
            if (signerSet != null && signerSet.size() > 0) {
                signerinfo = SignerInfo.getInstance(signerSet.getObjectAt(0));
                tIssuerAndSN = signerinfo.getIssuerAndSerialNumber().getName() + signerinfo.getIssuerAndSerialNumber().getCertificateSerialNumber().getValue().toString();
                if (tcertsmap != null && tcertsmap.size() > 0) {
                    cert = (X509Cert) tcertsmap.get(tIssuerAndSN);
                }

                if (cert == null && certsMap != null && certsMap.size() > 0) {
                    cert = (X509Cert) certsMap.get(tIssuerAndSN);
                }

                if (cert == null) {
                    throw new Exception();
                }

            }

            return cert;
        } catch (Throwable var12) {
          throw new Exception();
        }
    }
}

