package fr.sylvaindce;

import fr.sylvaindce.Gui.Gui;
import fr.sylvaindce.Tools.Algo;

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
