package Gui;

import DataModel.AccountModel;

import javax.swing.*;
import java.awt.*;

public class customJListRenderer extends JLabel implements ListCellRenderer<AccountModel> {

    @Override
    public Component getListCellRendererComponent(JList<? extends AccountModel> list, AccountModel accountModel, int index, boolean isSelected, boolean cellHasFocus) {
        String html = "<html><h3>" + accountModel.getDescription() + "<h3><br/><p>" + accountModel.getAccount() + "<p/></html>";
        setText(html);
        return this;
    }

}
