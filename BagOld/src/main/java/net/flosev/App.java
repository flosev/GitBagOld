package net.flosev;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: vaha
 * Date: 9/23/13
 * Time: 7:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class App {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.println(new Date(System.currentTimeMillis() - 1_000_000_000L));
        System.out.print(MyTrain.d);



    }
}
