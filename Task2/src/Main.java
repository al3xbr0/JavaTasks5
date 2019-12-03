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
        dd.add(4, 15);
        dd.foreach(System.out::println);
        System.out.println(l.contains(15));
    }
}