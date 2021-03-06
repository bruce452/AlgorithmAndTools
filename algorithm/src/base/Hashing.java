package base;

/**
 * 如何查看哈希值（内存地址）
 */
public class Hashing {

    public static void main(String[] args) {
        Object object = new Object();
        printObject(object);

        int a = 0;
        printObject(a);

        int[] b = {0};
        printObject(b);

        boolean c = true;
        printObject(c);
    }

    private static void printObject(Object object) {
        System.out.println("内存地址（十进制）：" + object.hashCode());
        System.out.println(object.toString());  //className + @ + 16进制的HashCode(Memory Address)
    }

}
//输出
//内存地址：22307196
//java.lang.Object@154617c
//内存地址：0
//0
//内存地址：10568834
//[I@a14482
//内存地址：1231
//true