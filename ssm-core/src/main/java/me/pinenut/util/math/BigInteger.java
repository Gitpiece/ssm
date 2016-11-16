package me.pinenut.util.math;

import java.util.Stack;

/**
 * Created by root on 15-6-12.
 */
public class BigInteger {
    /**
     * 计算进位
     *
     * @param bit 数组
     * @param pos 用于判断是否是数组的最高位
     */
    private void carry(int[] bit, int pos) {
        int i, carray = 0;
        for (i = 0; i <= pos; i++)//从0到pos逐位检查是否需要进位
        {
            bit[i] += carray;//累加进位
            if (bit[i] <= 9)   //小于9不进位
            {
                carray = 0;
            } else if (bit[i] > 9 && i < pos)//大于9，但不是最高位
            {
                carray = bit[i] / 10;//保存进位值
                bit[i] = bit[i] % 10;//得到该位的一位数
            } else if (bit[i] > 9 && i >= pos)//大于9，且是最高位
            {
                while (bit[i] > 9)//循环向前进位
                {
                    carray = bit[i] / 10;//计算进位值
                    bit[i] = bit[i] % 10;//当前的第一位数
                    i++;
                    bit[i] = carray;//在下一位保存进位值
                }
            }
        }
    }

    /**
     * 大整数阶乘
     *
     * @param bigInteger 所计算的大整数
     */
    private void bigFactorial(int bigInteger) {
        int pos = 0;//
        int digit;//数据长度
        int a, b;
        int m = 0;//统计输出位数
        int n = 0;//统计输出行数
        double sum = 0;//阶乘位数
        for (a = 1; a <= bigInteger; a++)//计算阶乘位数
        {
            sum += Math.log10(a);
        }
        digit = (int) sum + 1;//数据长度

        int[] fact = new int[digit];//初始化一个数组
        fact[0] = 1;//设个位为 1

        for (a = 2; a <= bigInteger; a++)//将2^bigInteger逐个与原来的积相乘
        {
            for (b = digit - 1; b >= 0; b--)//查找最高位{}
            {
                if (fact[b] != 0) {
                    pos = b;//记录最高位
                    break;
                }
            }

            for (b = 0; b <= pos; b++) {
                fact[b] *= a;//每一位与i乘
            }
            carry(fact, pos);
        }

        for (b = digit - 1; b >= 0; b--) {
            if (fact[b] != 0) {
                pos = b;//记录最高位
                break;
            }
        }
        System.out.println(bigInteger + "阶乘结果为：");
        for (a = pos; a >= 0; a--)//输出计算结果
        {
            System.out.print(fact[a]);
            m++;
            if (m % 5 == 0) {
                System.out.print(" ");
            }
            if (40 == m) {
                System.out.println("");
                m = 0;
                n++;
                if (10 == n) {
                    System.out.print("\n");
                    n = 0;
                }
            }
        }
        System.out.println("\n" + "阶乘共有: " + (pos + 1) + " 位");

    }


    /**
     * 整数加法
     *
     * @param lv     左值
     * @param rv     右值
     * @param result 相加的结果
     * @数值存放说明 数值的每一位作为栈的一项存放在栈中, 从栈底到栈顶依次是数值的高位到低位
     * @算法描述 输入的加数倒序存放在栈中（即栈顶是数的最低位,栈底是数的最高位）。 计算的时候,依次弹出栈中的数据,对每一位执行加操作。
     * 若遇到进位,则将进位标志carry设置为1,以在进行下一位计算的时候将其加上。 进位加结束后,将carry的值重置为0。
     * 每次计算都会检查进位标志carry的值
     */
    public void plus(Stack<Integer> lv, Stack<Integer> rv, Stack<Integer> result) {
        int sum = 0;
        // 进位标志
        int carry = 0;
        while (true) {
            try {
                // 两个加数的长度都还不为0,继续分别在两个栈中取出一位相加
                if (!lv.empty() && !rv.empty()) {
                    // 两个加数取出一位值相加,再加上进位（因为在有进位时carry=1,无进位时carry=0,根据任何数与0相加都等于0,所以可以直接加上）
                    sum = lv.pop() + rv.pop() + carry;
                    // 进位标志使用后,重置为0
                    carry = 0;
                    // 根据当前加数的和再加上进位的结果是否大于9（即10甚至更大,因为每一位加数的最大值只可能是9,所以这里最大也只能是18）
                    // 进行是否进位的判断依据,若大于9,将当前位相加的结果减去10,并将进位标志设置为1
                    if (sum > 9) {
                        sum -= 10;
                        carry = 1;
                    }
                    result.push(sum);
                    // 在两个加数的每一位都进行了计算后,判断是否还有进位,若有则加到结果的最高位
                    // 为了在两个加数位数（长度）不相等时能正确地计算,这个条件必需放在下面的两个条件前面
                } else if (lv.empty() && rv.empty()) {
                    if (carry == 1)
                        result.push(carry);
                    return;
                    // 左值已经为空（每一位都参与了计算）,而右值还不为空时,将右值的每一位取出加上进位值放到结果中
                } else if (lv.empty()) {
                    sum = rv.pop() + carry;
                    carry = 0;
                    if (sum > 9) {
                        sum -= 10;
                        carry = 1;
                    }
                    result.push(sum);
                    // 同上面左值说明
                } else if (rv.empty()) {
                    sum = lv.pop() + carry;
                    carry = 0;
                    if (sum > 9) {
                        sum -= 10;
                        carry = 1;
                    }
                    result.push(sum);
                }
            } catch (Exception e) {
                System.err.println("栈溢出");
            }
        }
    }

    public void doBigFactorial(int bigInteger) {
        int timeBegin = (int) System.currentTimeMillis();
        this.bigFactorial(bigInteger);
        int timeFinishi = (int) System.currentTimeMillis();
        int time = timeFinishi - timeBegin;
        System.out.println("计算耗时: " + time + "毫秒");
    }

    public static void main(String[] args) {
        BigInteger bi = new BigInteger();
        bi.doBigFactorial(100);
    }
}
