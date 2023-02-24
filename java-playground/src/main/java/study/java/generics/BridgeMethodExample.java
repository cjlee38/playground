package study.java.generics;

public class BridgeMethodExample {

    public static void main(String[] args) {
        MyNode mn = new MyNode(5);
        Node n = mn;
        n.setData("Hello"); // 이 지점에서 ClassCastException이 발생한다.
//        Integer x = mn.data;
    }

    static class Node<T> {

        public T data;

        public Node(T data) { this.data = data; }

        public void setData(T data) {
            System.out.println("Node.setData");
            this.data = data;
        }
    }

    static class MyNode extends Node<Integer> {
        public MyNode(Integer data) { super(data); }

        public void setData(Integer data) {
            System.out.println("MyNode.setData");
            super.setData(data);
        }
    }
}
