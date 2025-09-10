package com.it.zhengZeBiaoDaShi;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DoubaoImprove {
    // 常用User-Agent列表
    private static final String[] USER_AGENTS = {
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:90.0) Gecko/20100101 Firefox/90.0",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36"
    };

    public static void main(String[] args) {
        try {
            // 信任所有证书（仅用于测试环境）
            disableSslVerification();

            // 目标URL
            URL url = new URL("https://www.nlc.cn/web/index.shtml");

            // 打开连接并设置请求头
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            setRequestHeaders(conn);

            // 设置超时时间
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            // 获取响应码
            int responseCode = conn.getResponseCode();
            System.out.println("响应码: " + responseCode);

            // 读取响应内容
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line;
            StringBuilder response = new StringBuilder();

            while ((line = br.readLine()) != null) {
                response.append(line).append("\n");
            }

            br.close();

            // 输出响应内容
            System.out.println("响应内容长度: " + response.length());

            // 简单检查是否遇到验证码
            if (response.toString().contains("验证码") || response.toString().contains("captcha")) {
                System.out.println("检测到验证码页面，可能触发了反爬机制");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 禁用SSL验证（仅用于测试环境）
    private static void disableSslVerification() {
        try {
            // 创建信任所有证书的TrustManager
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() { return null; }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    }
            };

            // 安装信任管理器
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // 禁用主机名验证
            HostnameVerifier allHostsValid = (hostname, session) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }

    // 设置常用请求头
    private static void setRequestHeaders(HttpURLConnection conn) {
        // 随机选择一个User-Agent
        Random random = new Random();
        String userAgent = USER_AGENTS[random.nextInt(USER_AGENTS.length)];

        // 设置基本请求头
        conn.setRequestProperty("User-Agent", userAgent);
        conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("Upgrade-Insecure-Requests", "1");

        // 其他可选请求头
        conn.setRequestProperty("Cache-Control", "max-age=0");
        conn.setRequestProperty("DNT", "1"); // Do Not Track
    }
}