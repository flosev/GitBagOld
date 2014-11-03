package net.flosev;

/**
 * Created with IntelliJ IDEA.
 * User: виталик
 * Date: 09.11.13
 * Time: 17:03
 * To change this template use File | Settings | File Templates.
 */
import org.springframework.context.support.LiveBeansView;
public class MyTrain {
   public MyTrain() {
          int j=6;
    }
    public static int d=6;


    public static void main(String[] args){
      LiveBeansView dd= new LiveBeansView() {
      };

        dd.toString();
        System.out.print(dd);
        /*System.out.print(QuizAllController.PAGE_OK);*/

    }
}
