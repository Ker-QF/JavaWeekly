package com.it.test;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<User> list = new ArrayList<>();

        while (true) {//ctrl + alt + t
            System.out.println("欢迎来到学生管理系统");
            System.out.println("请选择操作：1登录 2注册 3忘记密码 4退出系统");
            String choice = sc.next();
            switch(choice){
                case "1"-> login(list);//单击 + alt + enter
                case "2"-> register(list);
                case "3"-> forgetPassword(list);
                case "4"-> {
                    System.out.println("谢谢使用，再见");
                    System.exit(0);
                }
                default -> System.out.println("没有这个选项");
            }
        }
    }

    private static void login(ArrayList<User> list) {
        System.out.println("登录");

        for (int i = 0; i < 3; i++) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入用户名");
            String username = sc.next();
            boolean flag = contains(list,username);

            if (!flag){
                System.out.println("用户名" + username + "未注册，请先注册再登陆");
                return;
            }

            System.out.println("请输入密码");
            String password = sc.next();

            while (true) {
                String rightCode = getCode();
                System.out.println("当前正确的验证码为" + rightCode);
                System.out.println("请输入验证码:");
                String code = sc.next();
                if (code.equalsIgnoreCase(rightCode)){
                    break;
                }else{
                    System.out.println("验证码错误");
                    continue;
                }
            }

            //把一些零散的数据封装成对象
            User userInfo = new User(username,password,null,null);
            boolean result = checkUserInfo(list,userInfo);
            if (result){
                System.out.println("登录成功，可以使用学生管理系统了");
                break;
            }else{
                System.out.println("登录失败，用户名或密码错误");
                if (i == 2){
                    System.out.println("当前帐号" + username + "被锁定，请稍后再试");
                    return;
                }else{
                    System.out.println("还剩下" + (2-i) + "次机会");
                }
            }
        }

    }

    private static boolean checkUserInfo(AbstractList<User> list,User userinfo) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if (user.getUserName().equals(userinfo.getUserName()) && user.getPassword().equals(userinfo.getPassword())){
                return true;
            }
        }

        return false;
    }

    private static void forgetPassword(ArrayList<User> list) {
        System.out.println("忘记密码");
    }

    private static void register(ArrayList<User> list) {
        System.out.println("注册");
        //把用户信息添加到集合中
        Scanner sc = new Scanner(System.in);
        String username;
        while (true) {
            System.out.println("请输入用户名");
            username = sc.next();

            boolean flag1 = checkUsername(username);
            if (flag1){
                System.out.println("用户名格式不正确，需重新输入");
                continue;
            }
            //用户名唯一
            boolean flag2 = contains(list,username);
            if (flag2){
                System.out.println("用户名" + username + "已存在，请重新输入");
            }else{
                System.out.println("用户名" + username + "可用");
                break;
            }

        }//用户名
        String password;
        while (true) {
            System.out.println("请输入要注册的密码");
            password = sc.next();
            System.out.println("请再次输入要注册的密码");
            String againPassword = sc.next();
            if (!password.equals(againPassword)){
                System.out.println("两次密码输入不一致，请重新输入");
                continue;
            }else{
                System.out.println("两次输入一致，继续录入");
                break;
            }
        }//密码
        String personId;
        while (true) {
            System.out.println("请输入身份证号码");
            personId = sc.next();
            boolean flag = checkPersonId(personId);
            if (flag){
                System.out.println("身份证号满足要求");
                break;
            }else{
                System.out.println("身份证号码格式错误，请重新输入");
                continue;
            }
        }//身份证
        String phoneNumber;
        while (true) {
            System.out.println("请输入手机号码");
            phoneNumber = sc.next();
            boolean flag = checkPhoneNumber(phoneNumber);
            if (flag){
                System.out.println("手机号码格式正确");
                break;
            }else{
                System.out.println("手机号码格式有误，请重新输入");
                continue;
            }
        }//手机号码

        User u = new User(username,password,personId,phoneNumber);
        list.add(u);
        System.out.println("注册成功");

        printList(list);
    }

    private static void printList(ArrayList<User> list) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            System.out.println(user.getUserName() + "," + user.getPassword() + "," + user.getPersonId() + "," + user.getPhoneNumber());
        }
    }

    private static boolean checkPhoneNumber(String phoneNumber) {
        int len = phoneNumber.length();
        if (len != 11){
            return false;
        }
        boolean flag = phoneNumber.startsWith("0");
        if (flag){
            return false;
        }
        for (int i = 0; i < len; i++) {
            char c = phoneNumber.charAt(0);
            if (!(c >= '0' && c <= '9')){
                return false;
            }
        }

        return true;
    }

    private static boolean checkPersonId(String personId) {
        if (personId.length() != 18){
            return false;
        }
        boolean flag = personId.startsWith("0");
        if (flag){
            return false;
        }
        for (int i = 0; i < personId.length()-1; i++) {
            char c = personId.charAt(0);
            if (!(c >= '0' && c <= '9')){
                return false;
            }
        }
        char endChar = personId.charAt(personId.length()-1);
        if ((endChar >= '0' && endChar <= '9') || (endChar == 'x') || (endChar == 'X')){
            return true;
        }else{
            return false;
        }
    }

    private static boolean contains(ArrayList<User> list,String username) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            String rightUsername = user.getUserName();
            if (rightUsername.equals(username)){
                return true;
            }
        }

        return false;
    }

    private static boolean checkUsername(String username) {
        //length 3~15
        int len = username.length();
        if (len < 3 || len > 15){
            return false;
        }
        //
        for (int i = 0; i < len; i++) {
            char c = username.charAt(i);
            if (!((c >= 'a' && c <= 'b') || (c >= '0' && c <= '9') || (c >= 'A' && c <= 'B'))){
                return false;
            }
        }
        //不能是纯数字
        for (int i = 0; i < len; i++) {
            char c = username.charAt(i);
            if ((c >= 'a' && c <= 'b') || (c >= 'A' && c <= 'B')){
                return true;
            }
        }

        return false;
    }

    //生成验证码
    private static String getCode(){
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add((char)('a' + i));
            list.add((char)('A' + i));
        }

        StringBuilder sb =  new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(list.size());
            char c = list.get(index);
            sb.append(c);
        }

        int number = r.nextInt(10);
        sb.append(number);

        char[] arr = sb.toString().toCharArray();
        int randomIndex = r.nextInt(arr.length);
        char temp = arr[randomIndex];
        arr[randomIndex] = arr[arr.length-1];
        arr[arr.length-1] = temp;

        return new String(arr);
    }


}
