package study.java.reference;

import java.lang.ref.WeakReference;

public class Weak {

    static class Referent {
        private int value;

        public Referent(int value) {
            this.value = value;
        }
    }

    /*
    java -verbose:gc Weak.java
    // heap size에 대한 추가 설정이 필요할 듯 하다.
     */
    public static void main(String[] args) {
        Referent referent = new Referent(100);
        WeakReference<String> reference = new WeakReference(referent);
        System.gc();
        referent = null;
        System.out.println("null");
        System.gc();
    }
}
