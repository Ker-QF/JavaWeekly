package com.it.exercise;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputSteamE {
    public static void main(String[] args) throws IOException {

        /*
            换行：
                windows:\r\n 但java优化，只写其中一个就行
                Linux:\n
                Mac: \r




        */



        //另一个参数是打开续写开关的
        FileOutputStream fos = new FileOutputStream("C:\\Users\\ker\\Desktop\\Javatest\\Practice\\fisrt-code\\src\\com\\it\\exercise\\a.txt",true);

        //fos.write(97);

        byte[] bytes = {97,98,99,100,101};
        /*fos.write(bytes);*/
        //fos.write(bytes,1,2);//会清空原数据


        String str = "abcdefg";
        String wrap = "\r\n";
        byte[] arr = str.getBytes();
        byte[] arr2 = wrap.getBytes();


        /*fos.write(arr);
        fos.write(arr2);
        fos.write('a');*/

        fos.write(bytes);//打开续写开关


        fos.close();


    }
}
