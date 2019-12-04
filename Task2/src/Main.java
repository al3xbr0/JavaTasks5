import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        LinkedList<Integer> l = new LinkedList<>();
        l.add(5);
        l.add(8);
        l.add(7);
        l.add(1);
        l.add(4, 15);
        l.forEach(System.out::println);
        System.out.println(l.contains(6));

        System.out.println("-");

        DoubleLinkedList<Integer> dd = new DoubleLinkedList<Integer>();
        dd.add(5);
        dd.add(8);
        dd.add(7);
        dd.add(1);
        dd.add(3);
        dd.add(5, 15);
        dd.foreach(System.out::println);
        System.out.println(dd.contains(15));

        System.out.println("-");

        MyQueue<Integer> q = new MyQueue<>();
        q.add(5);
        q.add(2);
        q.add(0);
        System.out.println(q.peek());
    }
}