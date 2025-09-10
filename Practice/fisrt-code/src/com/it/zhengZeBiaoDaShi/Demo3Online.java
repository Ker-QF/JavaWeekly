package com.it.zhengZeBiaoDaShi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo3Online {
    public static void main(String[] args) throws IOException {
        //创建一个URL对象
        URL url = new URL("https://www.doubao.com/chat/13982265758445314");

        URLConnection conn = url.openConnection();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;

        Pattern pattern = Pattern.compile("豆包");

        while ((line = br.readLine()) != null){
            System.out.println(line);
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()){
                System.out.println(matcher.group());
            }
        }

        br.close();
    }
}
