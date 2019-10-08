package com.cloud.portal.archive.tyyh.model;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class HwToken {
    private static final String APIG_MICROGW_FLOAT_IP = "68.26.19.197";

    private static final String ACCESS_TOKEN_URI = "/v1/apigw/oauth2/token";


    public static void HwToken(){
        String appKey = "4bdfba6dc01f4345916deae2e00af53d";
        String secretKey = "a4bfae64eef949f1a288a2fcee014a8f";
        TokenInfo tokenInfo = null;
        try {
            tokenInfo = getToken(appKey, secretKey);
        } catch (HttpException e) {
            e.printStackTrace();
        }

        //access_token
        String accessToken = tokenInfo.getAccessToken();
        System.out.println("The accessToken is:" + accessToken);

        //access_token
        String refreshToken = tokenInfo.getRefreshToken();
        try {
            refreshToken(appKey, secretKey, refreshToken);
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }

    public static TokenInfo getToken(String appKey, String secretKey) throws HttpException
    {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("grant_type", "client_credentials"));
        pairs.add(new BasicNameValuePair("client_id", appKey));
        pairs.add(new BasicNameValuePair("client_secret", secretKey));

        return request(pairs);
    }

    public static TokenInfo refreshToken(String appKey, String secretKey, String refreshToken) throws HttpException
    {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("grant_type", "refresh_token"));
        pairs.add(new BasicNameValuePair("client_id", appKey));
        pairs.add(new BasicNameValuePair("client_secret", secretKey));
        pairs.add(new BasicNameValuePair("refresh_token", refreshToken));

        return request(pairs);
    }

    public static TokenInfo request(List<NameValuePair> data) throws HttpException
    {
        TokenInfo tokenInfoInfo = new TokenInfo();

        SSLContext sslContext;
        CloseableHttpClient client;
        try
        {
            sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).useTLS().build();
            SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext,
                    new AllowAllHostnameVerifier());
            client = HttpClients.custom().setSSLSocketFactory(sslSocketFactory).build();

            // ����POST����,���URL
            URIBuilder uriBuilder = new URIBuilder("https://" + APIG_MICROGW_FLOAT_IP + ":8343" + ACCESS_TOKEN_URI);
            HttpPost httpPost = new HttpPost(uriBuilder.build());

            httpPost.setEntity(new UrlEncodedFormEntity(data, "UTF-8"));
            // �������ͷ
            httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");

            // ��������
            HttpResponse response = client.execute(httpPost);

            // ������Ӧ����
            if (response.getStatusLine().getStatusCode() == 200)
            {
                String tokenStr = EntityUtils.toString((response).getEntity());

                tokenInfoInfo = Utils.toObject(tokenStr, TokenInfo.class);
            }
            else
            {
                throw new HttpException(response.getStatusLine() + EntityUtils.toString((response).getEntity()));
            }
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (KeyManagementException e)
        {
            e.printStackTrace();
        }
        catch (KeyStoreException e)
        {
            e.printStackTrace();
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        catch (ClientProtocolException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return tokenInfoInfo;
    }
}
