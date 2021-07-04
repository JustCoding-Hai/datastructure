package top.javahai.algorithm.kmp;

/**
 * @author Hai
 * @program: DataStructure
 * @description: 实现KMP算法
 * @create 2021/6/9 - 0:07
 **/
public class KMPDemo {
    public static void main(String[] args) {
        String str1 = "3225156516";
        String str2 = "515";
        System.out.println(violenceMatch(str1, str2));
    }

    /**
     * 暴力匹配算法解决实现
     * <p>
     * 1. 如果当前字符匹配成功（即 str1[i] == str2[j]），则 i++，j++，继续匹配下一个字符
     * 2. 如果失配（即 str1[i]! = str2[j]），令 i = i - (j - 1)，j = 0。相当于每次匹配失败时，i 回溯，j 被置为 0。
     * 3. 用暴力方法解决的话就会有大量的回溯，每次只移动一位，若是不匹配，移动到下一位接着判断，浪费了大量 的时间。(不可行!)
     *
     * @param str1
     * @param str2
     * @return 匹配到str1字符串的初始位置
     */
    public static int violenceMatch(String str1, String str2) {
        char[] charArr1 = str1.toCharArray();
        char[] charArr2 = str2.toCharArray();
        //遍历索引
        int i = 0;
        int j = 0;
        while (i < charArr1.length && j < charArr2.length) {
            //如果匹配
            if (charArr1[i] == charArr2[j]) {
                i++;
                j++;
            } else { //如果不匹配
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == charArr2.length) {
            return i - j;
        } else {
            return -1;
        }
    }
}
