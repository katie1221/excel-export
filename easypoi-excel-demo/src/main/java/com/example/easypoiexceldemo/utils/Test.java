package com.example.easypoiexceldemo.utils;

import java.math.BigDecimal;

/**
 * @author qzz
 */
public class Test {

    public static void main(String[] args) {
        Double d1=12.0;
        Double d2=6.1;
        Double d3=4.0;
        BigDecimal b1 = new BigDecimal(d1.toString());
        BigDecimal b2 = new BigDecimal(d2.toString());
        BigDecimal b3 = new BigDecimal(d3.toString());
        //获取两个double数之间的除法：使用BigDecimal中的divide(BigDecimal divisor,int scale, int roundingMode)方法
        //第一个参数（divisor）是除数，第二个参数（scale）代表保留几位小数，第三个（roundingMode）代表的是使用的模式
        //BigDecimal.ROUND_DOWN:直接省略多余的小数，比如1.28如果保留1位小数，得到的就是1.2
        //BigDecimal.ROUND_UP:直接进位，比如1.21如果保留1位小数，得到的就是1.3
        //BigDecimal.ROUND_HALF_UP:四舍五入，2.35保留1位，变成2.4
        //BigDecimal.ROUND_HALF_DOWN:四舍五入，2.35保留1位，变成2.3；BigDecimal.ROUND_HALF_DOWN:四舍五入，2.354保留1位，变成2.4
        //后边两种的区别
        //ROUND_HALF_UP：四舍五入
        //ROUND_HALF_DOWN:如果保留位 + 1 = 总位数，保留位后一位正好是5的时候就舍弃掉；大于5的时候直接进位；如果保留位 + 1 < 总位数，保留位后一位正好大于等于5的时候，直接进位处理

        System.out.println("-----------BigDecimal中的divide(BigDecimal divisor,int scale, int roundingMode)方法的使用-----------");
        // 12 除于 6.1 =1.9672131... 保留一个小数，其他的小数直接省略
        System.out.println("BigDecimal.ROUND_DOWN的使用(1.9672131 保留一个小数，其他的小数直接省略)："+b1.divide(b2,1,BigDecimal.ROUND_DOWN));
        // 12 除于 6.1 =1.9672131... 保留一个小数，直接进位
        System.out.println("BigDecimal.ROUND_UP的使用(1.9672131 保留一个小数，直接进位)："+b1.divide(b2,1,BigDecimal.ROUND_UP));
        // 12 除于 6.1 =1.9672131... 四舍五入，后一位大于等于5直接进一位
        System.out.println("BigDecimal.ROUND_HALF_UP的使用(1.9672131 保留一个小数，后一位大于等于5直接进一位)："+b1.divide(b2,1,BigDecimal.ROUND_HALF_UP));
        // 25 除于 4 =6.25 四舍五入，后一位大于等于5直接进一位 6.25
        System.out.println("BigDecimal.ROUND_HALF_DOWN的使用(6.25 保留一个小数，后一位正好是5的时候舍弃掉)："+new BigDecimal("25").divide(b3,1,BigDecimal.ROUND_HALF_DOWN));
        // 25 除于 4 =6.25 四舍五入，后一位大于等于5直接进一位 6.26
        System.out.println("BigDecimal.ROUND_HALF_DOWN的使用(6.26 保留一个小数，后一位正好大于5的时候，直接进位)："+new BigDecimal("25.04").divide(b3,1,BigDecimal.ROUND_HALF_DOWN));
        // 25.016 除于 4 =6.254 四舍五入，后一位大于等于5直接进一位 6.254
        System.out.println("BigDecimal.ROUND_HALF_DOWN的使用(6.254 保留一个小数，后一位大于等于5,还有其他位数时，直接进一位)："+new BigDecimal("25.016").divide(b3,1,BigDecimal.ROUND_HALF_DOWN));


        System.out.println("-----------BigDecimal.setScale()方法用于格式化小数点-----------");
        //setScale(1)表示保留一位小数，默认用四舍五入方式 使用时 要保留的小数位必须大于等于BigDecimal的小数位，不然会报错Rounding necessary
        //setScale(1,BigDecimal.ROUND_DOWN)直接删除多余的小数位，如2.35会变成2.3
        //setScale(1,BigDecimal.ROUND_UP)进位处理，2.35变成2.4
        //setScale(1,BigDecimal.ROUND_HALF_UP)四舍五入，2.35变成2.4
        //setScaler(1,BigDecimal.ROUND_HALF_DOWN)四舍五入，2.35变成2.3，如果是5则向下舍
        //setScaler(1,BigDecimal.ROUND_HALF_DOWN)四舍五入，2.354变成2.4
        //setScaler(1,BigDecimal.ROUND_HALF_DOWN)四舍五入，2.36变成2.4

        BigDecimal b4 = new BigDecimal("4.65");
        BigDecimal b5 = new BigDecimal("4.631");
        BigDecimal b6 = new BigDecimal("4.654");
        BigDecimal b7 = new BigDecimal("4.66");
        System.out.println("4.65 保留一位小数:"+b4.setScale(2));
        System.out.println("setScale(1,BigDecimal.ROUND_DOWN) 4.65 保留一位小数,直接删除多余的小数位:"+b4.setScale(1,BigDecimal.ROUND_DOWN));
        System.out.println("setScale(1,BigDecimal.ROUND_UP) 4.631 进位处理:"+b5.setScale(1,BigDecimal.ROUND_UP));
        System.out.println("setScale(1,BigDecimal.ROUND_HALF_UP) 4.631 四舍五入:"+b5.setScale(1,BigDecimal.ROUND_HALF_UP));
        System.out.println("setScale(1,BigDecimal.ROUND_HALF_UP) 4.65 四舍五入:"+b4.setScale(1,BigDecimal.ROUND_HALF_UP));
        System.out.println("setScale(1,BigDecimal.ROUND_HALF_DOWN) 4.65 四舍五入,如果后一位是5则向下舍:"+b4.setScale(1,BigDecimal.ROUND_HALF_DOWN));
        System.out.println("setScale(1,BigDecimal.ROUND_HALF_DOWN) 4.66 四舍五入,如果后一位大于5则直接进位:"+b7.setScale(1,BigDecimal.ROUND_HALF_DOWN));
        System.out.println("setScale(1,BigDecimal.ROUND_HALF_DOWN) 4.654 四舍五入，如果后一位是大于等于5，还有其他位数，则进位处理:"+b6.setScale(1,BigDecimal.ROUND_HALF_DOWN));
    }
}
