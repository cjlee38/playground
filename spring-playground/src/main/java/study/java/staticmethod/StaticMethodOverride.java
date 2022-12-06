
package study.java.staticmethod;

public class StaticMethodOverride {
    public static void main(String[] args) {
        Parent whoAreU = new Child();
        whoAreU.myInstantMethod();
        whoAreU.myStaticMethod();
        Child.myStaticMethod();
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

    public static void myStaticMethod() {
        System.out.println("Child.myStaticMethod");
    }

    @Override
    public void myInstantMethod() {
        System.out.println("Child.myInstantMethod");
    }
}
