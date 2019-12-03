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

        DLL<Integer> dd = new DLL<Integer>();
        dd.add(5);
        dd.add(8);
        dd.add(7);
        dd.add(1);
        dd.add(3);
        dd.add(5, 15);
        dd.foreach(System.out::println);
        System.out.println(dd.contains(15));

        System.out.println("-");

        QueuE<Integer> q = new QueuE<>();
        q.add(5);
        q.add(2);
        q.add(0);
        System.out.println(q.peek());
    }
}