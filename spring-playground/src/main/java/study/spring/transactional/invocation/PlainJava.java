package study.spring.transactional.invocation;

import org.springframework.transaction.annotation.Transactional;

public class PlainJava {

    static class Super {
        public void methodA() {
            System.out.println("Super.methodA");
            this.methodB();
        }

        public void methodB() {
            System.out.println("Super.methodB");
        }
    }

    static class Sub extends Super {

        Super sup = new Super();

        public void methodA() {
            System.out.println("Sub.methodA");
            sup.methodA();
        }

        public void methodB() {
            System.out.println("Sub.methodB");
            // trasactional.begin();
            sup.methodB();
            // transactional.commit();
        }
    }

    public static void main(String[] args) {
        Super object = new Sub();
        object.methodA();
        /*
        Sub.methodA
        Super.methodA
        Sub.methodB
        Super.methodB
         */
    }
}
