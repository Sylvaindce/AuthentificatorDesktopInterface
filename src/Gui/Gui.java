package Gui;

import DataModel.AccountModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Gui extends JFrame {

        private static final SimpleDateFormat time_format = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss a");
        private static JLabel time_label = new JLabel("");
        private static DefaultListModel<AccountModel> accountModelList = new DefaultListModel<>();
        private static JList<AccountModel> accountModelJList = new JList<>(Gui.accountModelList);

    public Gui() {
        this.setTitle("A*uthentificator D*esktop I*nterface");
        this.setSize(400, 700);
        this.setBackground(new Color(224, 224, 224));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        AccountModel model1 = new AccountModel("decombe.sylvain@gmail.com", "Google", "1234567");
        Gui.accountModelList.addElement(model1);
        gui_initializer();
        this.setVisible(true);
    }

    private void gui_initializer() {
        JPanel main_container = new JPanel();
        main_container.setLayout(new BorderLayout());

        JPanel header = new JPanel();
        header.setBackground(new Color(255, 87, 34));
        header.setPreferredSize(new Dimension(400, 80));
        JButton test = new JButton("test");
        test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println(Gui.getAccountModelList().getElementAt(0).getAccount());
                Gui.getAccountModelList().getElementAt(0).setAccount("toto");
                System.out.println(Gui.getAccountModelList().getElementAt(0).getAccount());
                Gui.getAccountModelJList().updateUI();
            }
        });
        header.add(test);

        JPanel main_content = new JPanel(new BorderLayout());
        main_content.setPreferredSize(new Dimension(400, 600));
        main_content.setBackground(new Color(255, 255, 255));
        Gui.accountModelJList.setCellRenderer(new customJListRenderer());
        JScrollPane mainContentJScrollPane = new JScrollPane(Gui.accountModelJList);
        mainContentJScrollPane.setPreferredSize(new Dimension(400, 600));
        main_content.add(mainContentJScrollPane);

        JPanel footer = new JPanel(new FlowLayout());
        footer.setPreferredSize(new Dimension(400, 20));
        footer.setBackground(new Color(255, 87, 34));
        //footer.setLayout(new FlowLayout());
        footer.setAlignmentY(TOP_ALIGNMENT);
        //time_label.setPreferredSize(new Dimension(180, 20));
        time_label.setVerticalAlignment(JLabel.TOP);
        time_label.setVerticalTextPosition(JLabel.TOP);
        time_label.setForeground(Color.WHITE);
        footer.add(new JLabel("Sylvaindce", JLabel.LEFT));
        footer.add(time_label);

        main_container.add(header, BorderLayout.PAGE_START);
        main_container.add(main_content, BorderLayout.CENTER);
        main_container.add(footer, BorderLayout.PAGE_END);

        this.setContentPane(main_container);
    }

    public static void update_time(Date date) {
        time_label.setText(time_format.format(date));
    }


    public static DefaultListModel<AccountModel> getAccountModelList() {
        return Gui.accountModelList;
    }

    public static JList getAccountModelJList() {
        return Gui.accountModelJList;
    }

}
