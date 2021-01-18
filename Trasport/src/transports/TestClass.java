package transports;

import transports.enumerators.Tmerce;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class TestClass {


    public static void main(String[] args) {
        Collection<Integer> c = new Vector<>();
         c.add(10);
         c.add(50);
         c.add(90);
         c.add(1);
         Iterator<Integer> it= c.iterator();
         Integer el= it.next();
         System.out.println(el);
         System.out.println(Tmerce.GASSOSO.equals(Tmerce.GASSOSO));
    }
}