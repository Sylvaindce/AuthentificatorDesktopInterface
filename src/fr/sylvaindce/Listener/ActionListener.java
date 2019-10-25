package fr.sylvaindce.Listener;

import fr.sylvaindce.Dialog.addAccountDialog;
import fr.sylvaindce.Gui.Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionListener implements java.awt.event.ActionListener {

    private JFrame parent;

    public ActionListener(JFrame parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (((JButton) actionEvent.getSource()).getName()) {
            case "add":
                new addAccountDialog((Gui) parent);
                break;
        }
    }

}
