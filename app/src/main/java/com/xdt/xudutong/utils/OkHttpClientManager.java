package com.xdt.xudutong.utils;

/**
 * Created by Administrator on 2018/5/2.
 */

public class OkHttpClientManager {

//    OkHttpClient mOkHttpClient = new OkHttpClient();
//
//    public void setCertificates(InputStream... certificates)
//    {
//        try
//        {
//            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
//            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//            keyStore.load(null);
//            int index = 0;
//            for (InputStream certificate : certificates)
//            {
//                String certificateAlias = Integer.toString(index++);
//                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));
//
//                try
//                {
//                    if (certificate != null)
//                        certificate.close();
//                } catch (IOException e)
//                {
//                }
//            }
//
//            SSLContext sslContext = SSLContext.getInstance("TLS");
//
//            TrustManagerFactory trustManagerFactory =
//                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//
//            trustManagerFactory.init(keyStore);
//            sslContext.init
//                    (
//                            null,
//                            trustManagerFactory.getTrustManagers(),
//                            new SecureRandom()
//                    );
//            mOkHttpClient.setSslSocketFactory(sslContext.getSocketFactory());
//
//
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//    }
}
