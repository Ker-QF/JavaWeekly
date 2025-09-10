package com.it.Recursion;

public class P1219 {
    static int sum = 0;

    public static void main(String[] args) {
        boolean[] b = new boolean[7];
        sum = 0;
        getSum(1,b);
        System.out.println(sum);
    }

    public static void getSum(int u,boolean[] b){
        if (u == 6){
            sum++;
            return;
        }

        for (int j = 1;j <= 6;j++){
            // 只有当元素未被使用时，才选择它（修正条件判断）
            if (!b[j]) {  // 改为!b[j]，表示选择未使用的元素
                b[j] = true;  // 标记为已使用
                getSum(u + 1, b);  // 递归进入下一层
                b[j] = false;  // 回溯：取消标记
            }
        }
    }

}
