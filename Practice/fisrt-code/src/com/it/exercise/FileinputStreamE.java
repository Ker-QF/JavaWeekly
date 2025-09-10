package com.it.exercise;

import java.io.FileInputStream;
import java.io.IOException;

public class FileinputStreamE {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\ker\\Desktop\\Javatest\\Practice\\fisrt-code\\src\\com\\it\\exercise\\a.txt");

        /*char b = (char)fis.read();
        System.out.println(b);*/

        int a;
        while((a = fis.read()) != -1){
            System.out.print((char)a);
        }


        fis.close();
    }

}
