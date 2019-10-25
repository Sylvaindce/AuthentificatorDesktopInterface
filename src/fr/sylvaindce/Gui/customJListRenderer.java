package fr.sylvaindce.Gui;

import fr.sylvaindce.DataModel.AccountModel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class customJListRenderer extends JLabel implements ListCellRenderer<AccountModel> {

    @Override
    public Component getListCellRendererComponent(JList<? extends AccountModel> list, AccountModel accountModel, int index, boolean isSelected, boolean cellHasFocus) {
        this.setOpaque(true);
        this.setFont(Gui.mainFont);
        this.setPreferredSize(new Dimension(340, 100));
        this.setBackground(Color.WHITE);

        this.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Gui.light_grey_color), BorderFactory.createEmptyBorder(0, 0, 0, 8)));

        String html = "<html><body><div style='width: 240px; height: 60px; background: rgb(255,255,255); margin-left: 20px; text-align: left;' ><p style='color: rgb(38,38,38);'><b>" + accountModel.getDescription() + "</b></p><h1 style='color:rgb(55,126,242);'><b>" + accountModel.getOtp() + "</b></h1><p style='color: rgb(33, 33, 33);'><i>" + accountModel.getAccount() + "</i><p/></div></body></html>";

        this.setText(html);

        try {
            ImageIcon more_icon = new ImageIcon(this.getClass().getResource("/fr/sylvaindce/Res/Icons/more_vert-24px.png"));
            this.setIcon(more_icon);
            this.setHorizontalTextPosition(JLabel.LEFT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
}
