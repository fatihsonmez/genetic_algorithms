import javax.swing.*;
import java.util.Random;

/**
 * The type Nature.
 */
public class Nature {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        System.out.println("This is where everything is going to happen, as usual...");

        Evolution evolution = new Version1(40);
        Evolution evolution1 = new Version2(40);
        Evolution evolution2 = new Version3(40);
        DrawShapes drawShapes = new DrawShapes(evolution,evolution1,evolution2);

    }
}
