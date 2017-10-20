package com.mk.diy.bigbigweb.test.util;

class Test {
//    public static void main(String[] args) {
//        int sum = 0;
//        int i;
//        long index = 0;
//        for (i = 0; sum != 10; i++) {
//            sum = 3 * i + 3;
//            index++;
//        }
//        System.out.println(i-1);
//        int m2 = 3 * (i-1);
//        System.out.println(m2);
//        int m1 = 3 * (i-1) +3;
//        System.out.println(sum);
//        System.out.println(m1);
//        System.out.println(index);
//        int m = Integer.MIN_VALUE;
//        int z = Integer.MAX_VALUE;
//        int x = z + 1 ;
//        int h = 3 * z ;
//        System.out.println( m );
//        System.out.println( z );
//        System.out.println( x );
//        System.out.println( h );
//    }
    public static int k=0;
    public static Test t1=new Test("t1");
    public static Test t2=new Test("t2");
    public static Test t3=new Test("t3");
    public static int i=print("i");
    public static int n=99;
    public int j=print("j");
    public int m = print("m");
    static {
        print("静态块");
    }
    public Test(String str){
        System.out.println((++k)+":"+str+"  i="+i+" n="+n);
        ++i;
        ++n;
    }
    {
        print("构造块");
    }
    public static int print(String str){
        System.out.println((++k)+":"+str+"  i="+i+" n="+n);
        ++n;
        return ++i;
    }
    public static void main(String[] args) {
        System.out.println(1);
        // TODO Auto-generated method stub
        Test t=new Test("init");
    }

}