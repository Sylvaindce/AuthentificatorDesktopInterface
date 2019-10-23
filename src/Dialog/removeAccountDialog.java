package Dialog;

import Gui.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class removeAccountDialog extends JDialog implements ActionListener {

    private final Gui parent;
    private final int index;

    public removeAccountDialog(Gui parent, int index) {
        super(parent, "Remove Account");
        this.parent = parent;
        this.index = index;

        this.setSize(new Dimension(300, 200));
        this.setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());


        JLabel text = new JLabel("<html>Do you want to remove the account<br/><i>" + Gui.getAccountModelList().get(index).getAccount() + "</i> ?</html>", JLabel.CENTER);
        text.setPreferredSize(new Dimension(300, 60));
        text.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        text.setFont(Gui.mainFont);
        text.setForeground(Gui.black_color);

        JButton yes_button = new JButton("YES");
        try {
            ImageIcon yes_icon = new ImageIcon(this.getClass().getResource("/Res/Icons/ok-32.png"));
            yes_button.setIcon(yes_icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        yes_button.addActionListener(this);
        yes_button.setName("yes_remove");
        yes_button.setOpaque(false);
        yes_button.setBorderPainted(false);

        JButton no_button = new JButton("NO");
        try {
            ImageIcon no_icon = new ImageIcon(this.getClass().getResource("/Res/Icons/cancel-32.png"));
            no_button.setIcon(no_icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        no_button.addActionListener(this);
        no_button.setName("no_remove");
        no_button.setOpaque(false);
        no_button.setBorderPainted(false);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 140));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20, 20, 20));
        panel.add(yes_button);
        panel.add(no_button);

        this.add(text, BorderLayout.PAGE_START);
        this.add(panel, BorderLayout.CENTER);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (((JButton) actionEvent.getSource()).getName()) {
            case "no_remove":
                break;
            case "yes_remove":
                this.parent.deleteDialogCallback(this.index);
                break;
        }
        this.setVisible(false);
        this.dispose();
    }
}
