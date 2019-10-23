import Gui.Gui;
import Tools.Algo;
import Tools.Tools;

public class ADI {

    public static void main(String[] args) {
        try {
            new Gui();
            new Algo();
        } catch (Exception e) {
            System.out.print("Cannot execute ADI");
        }
    }
}
