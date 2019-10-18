import Gui.Gui;
import Tools.Algo;

public class ADI {
    public static void main(String[] args) {
        try {
            Gui adi_gui = new Gui();
            Algo adi_algo = new Algo();
        } catch (Exception e) {
            System.out.print("Cannot execute ADI");
        }
    }
}
