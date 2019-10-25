package fr.sylvaindce.Gui;

import fr.sylvaindce.DataModel.AccountModel;
import fr.sylvaindce.Tools.Tools;
import fr.sylvaindce.Dialog.removeAccountDialog;
import fr.sylvaindce.Listener.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Gui extends JFrame implements MouseListener {

        private static final SimpleDateFormat time_format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        private static JLabel time_label = new JLabel("");
        private static DefaultListModel<AccountModel> accountModelList;
        private static JList<AccountModel> accountModelJList;
        private ActionListener actionL;

        public static final Color primary_color = new Color(62, 131, 240);
        public static final Color dark_color = new Color(48, 100, 210);
        public static final Color light_color = new Color(159, 193, 248);
        public static final Color light_grey_color = new Color(238, 238, 238);
        public static final Color light_grey_2_color = new Color(224, 224, 224);
        public static final Color dark_grey_color = new Color(117, 117,117);
        public static final Color black_color = new Color(33,33,33);

        public static Font mainFont;

    public Gui() {
        Gui.accountModelList = new DefaultListModel<>();
        Gui.accountModelJList = new JList<>(Gui.accountModelList);
        this.actionL = new ActionListener(this);

        try {
            Gui.mainFont = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/fr/sylvaindce/Res/Fonts/Roboto-Regular.ttf"));
            Gui.mainFont = Gui.mainFont.deriveFont(12f);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setTitle("A*uthentificator D*esktop I*nterface");
        this.setSize(360, 700);
        this.setBackground(light_grey_2_color);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        gui_initializer();
        Tools.loadAccountFromSystem();
        this.setVisible(true);
    }

    private void gui_initializer() {
        Border emptyBorder = BorderFactory.createEmptyBorder();

        JPanel main_container = new JPanel();
        main_container.setLayout(new BorderLayout());

        main_container.add(this.init_header(emptyBorder), BorderLayout.PAGE_START);
        main_container.add(this.init_main_content(emptyBorder), BorderLayout.CENTER);
        main_container.add(this.init_footer(), BorderLayout.PAGE_END);

        this.setContentPane(main_container);
    }

    private JPanel init_header(Border emptyBorder) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(primary_color);
        header.setPreferredSize(new Dimension(360, 106));


        JPanel header_top = new JPanel();
        header_top.setPreferredSize(new Dimension(360, 76));
        header_top.setBackground(Gui.primary_color);

        JLabel title = new JLabel("<html><p>( a d i )</p></html>");
        try {
            ImageIcon app_icon = new ImageIcon(this.getClass().getResource("/fr/sylvaindce/Res/Icons/hat-wizard-solid.png"));
            title.setIcon(app_icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setVerticalTextPosition(JLabel.BOTTOM);
        title.setFont(Gui.mainFont);
        title.setForeground(Color.WHITE);
        header_top.add(title);
        header.add(header_top, BorderLayout.PAGE_START);

        JPanel header_bottom = new JPanel(new BorderLayout());
        header_bottom.setPreferredSize(new Dimension(360, 30));
        header_bottom.setBorder(new EmptyBorder(0, 0, 6, 20));
        header_bottom.setBackground(Gui.primary_color);
        JButton add = new JButton();
        try {
            ImageIcon add_icon = new ImageIcon(this.getClass().getResource("/fr/sylvaindce/Res/Icons/round_add_circle_outline_white_24dp.png"));
            add.setIcon(add_icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        add.setName("add");
        add.setPreferredSize(new Dimension(20, 20));
        add.setFont(Gui.mainFont);
        add.setBackground(Gui.primary_color);
        add.setForeground(Color.WHITE);
        add.setBorder(emptyBorder);
        add.setOpaque(true);
        add.setBorderPainted(false);
        add.addActionListener(this.actionL);
        header_bottom.add(add, BorderLayout.LINE_END);
        header.add(header_bottom, BorderLayout.PAGE_END);

        return header;
    }

    private JPanel init_main_content(Border emptyBorder) {
        JPanel main_content = new JPanel(new BorderLayout());
        main_content.setPreferredSize(new Dimension(360, 574));
        main_content.setBackground(light_grey_2_color);
        Gui.accountModelJList.setCellRenderer(new customJListRenderer());
        Gui.accountModelJList.setBackground(light_grey_2_color);
        Gui.accountModelJList.addMouseListener(this);
        JScrollPane mainContentJScrollPane = new JScrollPane(Gui.accountModelJList);
        mainContentJScrollPane.setBorder(emptyBorder);
        mainContentJScrollPane.setBackground(light_grey_2_color);
        mainContentJScrollPane.setPreferredSize(new Dimension(400, 564));
        main_content.add(mainContentJScrollPane);
        return main_content;
    }

    private JPanel init_footer() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setPreferredSize(new Dimension(360, 26));
        footer.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        footer.setBackground(primary_color);
        footer.setAlignmentY(TOP_ALIGNMENT);
        time_label.setForeground(Color.WHITE);

        JLabel credential = new JLabel("Sylvaindce");
        credential.setForeground(Color.WHITE);

        try {
            ImageIcon add_icon = new ImageIcon(this.getClass().getResource("/fr/sylvaindce/Res/Icons/github-brands.png"));
            credential.setIcon(add_icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        footer.add(credential, BorderLayout.LINE_START);
        footer.add(time_label, BorderLayout.LINE_END);
        return footer;
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

    public void addDialogCallback(AccountModel account) {
        Gui.accountModelList.addElement(account);
        Tools.updateOTP();
        Tools.saveAccountInSystem();
    }

    public void deleteDialogCallback(int index) {
        Gui.accountModelList.remove(index);
        Gui.accountModelJList.updateUI();
        Tools.saveAccountInSystem();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (300 < mouseEvent.getX()) {
            Gui.accountModelJList.setSelectedIndex(Gui.accountModelJList.locationToIndex(mouseEvent.getPoint()));
            new removeAccountDialog(this, Gui.accountModelJList.getSelectedIndex());
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {}

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}

    @Override
    public void mouseExited(MouseEvent mouseEvent) {}

}