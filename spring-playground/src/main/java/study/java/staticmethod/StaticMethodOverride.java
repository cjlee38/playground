
package study.java.staticmethod;

import java.util.Random;

public class StaticMethodOverride {

    /**
     * static method를 override(=hiding) 했을 때 바이트코드에서 어떻게 변화하는지 살펴본다.
     *
     * command : `javac StaticMethodOverride.java && javap -c -verbose StaticMethodOverride.class`
     */
    public static void main(String[] args) {
        /*
        static method를 호출하면, invokestatic이 동작한다.
        instant method를 호출하면, invokevirtual이 동작한다.
         */
        Parent parent = new Parent();
        Child child = new Child();
        Parent.myStaticMethod();
        parent.myStaticMethod();
        parent.myInstantMethod();
        child.myInstantMethod(5);




//        Child child = new Child();
//        parent.myStaticMethod();
//        Parent.myStaticMethod();
//        child.myStaticMethod(); // child의 myMethod를 주석처리한 경우에도 동작한다.
//        Child.myStaticMethod(); // 또한, 바이트코드의 결과 또한 주석처리했음에도 동일하다.
        /**
         * public static void main(java.lang.String[]);
         *     Code:
         *        0: new           #2                  // class study/java/staticmethod/Parent
         *        3: dup
         *        4: invokespecial #3                  // Method study/java/staticmethod/Parent."<init>":()V
         *        7: astore_1
         *        8: new           #4                  // class study/java/staticmethod/Child
         *       11: dup
         *       12: invokespecial #5                  // Method study/java/staticmethod/Child."<init>":()V
         *       15: astore_2
         *       16: aload_1
         *       17: pop
         *       18: invokestatic  #6                  // Method study/java/staticmethod/Parent.myMethod:()V
         *       21: invokestatic  #6                  // Method study/java/staticmethod/Parent.myMethod:()V
         *       24: aload_2
         *       25: pop
         *       26: invokestatic  #7                  // Method study/java/staticmethod/Child.myMethod:()V
         *       29: invokestatic  #7                  // Method study/java/staticmethod/Child.myMethod:()V
         *       32: return
         */
    }

}

class Parent {


    public static void myStaticMethod() {
        System.out.println("Parent.myStaticMethod");
    }

    public void myInstantMethod() {
        System.out.println("Parent.myInstantMethod");
    }

}

class Child extends Parent {


    public static void myMethod() {
        System.out.println("Child.myMethod");
    }

    public void myInstantMethod(int num) {
        System.out.println("Child.myInstantMethod" + num);
    }
}
