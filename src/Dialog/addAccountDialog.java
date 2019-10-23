package Dialog;

import DataModel.AccountModel;
import Gui.Gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addAccountDialog extends JDialog implements ActionListener {

    private final Gui parent;
    private JTextField account_field = new JTextField("utilisateur@example.com");
    private JTextField desc_field = new JTextField("Google");
    private JTextField key_field = new JTextField("abcd efgh ijkl mnop");
    private static JLabel error_label = new JLabel("<html><u>Error, please fill in all required fields correctly</u></html>", JLabel.CENTER);


    public addAccountDialog(Gui parent) {
        super(parent, "Add Account");
        this.parent = parent;
        this.setSize(new Dimension(300, 400));
        this.setBackground(Gui.light_grey_color);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Gui.primary_color);
        header.setPreferredSize(new Dimension(300, 40));
        header.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        JButton confirm_button = new JButton();
        try {
            ImageIcon add_icon = new ImageIcon(this.getClass().getResource("/Res/Icons/round_check_circle_outline_white_24dp.png"));
            confirm_button.setIcon(add_icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        confirm_button.addActionListener(this);
        header.add(confirm_button, BorderLayout.LINE_END);
        confirm_button.setName("add");
        confirm_button.setPreferredSize(new Dimension(24, 24));
        confirm_button.setOpaque(false);
        confirm_button.setBorderPainted(false);

        JPanel form = new JPanel();
        form.setBackground(Gui.light_grey_color);
        form.setPreferredSize(new Dimension(300, 360));

        JPanel form_account = new JPanel(new BorderLayout());
        form_account.setPreferredSize(new Dimension(300, 80));
        form_account.setBackground(Color.WHITE);
        form_account.setBorder(new EmptyBorder(10,10,10,10));
        JLabel account_label = new JLabel("Account", JLabel.LEFT);
        account_label.setForeground(Gui.black_color);
        form_account.add(account_label, BorderLayout.PAGE_START);
        this.account_field.setForeground(Gui.dark_grey_color);
        this.account_field.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Gui.dark_grey_color));
        this.account_field.setPreferredSize(new Dimension(300, 30));

        form_account.add(this.account_field, BorderLayout.PAGE_END);

        JPanel form_desc = new JPanel(new BorderLayout());
        form_desc.setPreferredSize(new Dimension(300, 80));
        form_desc.setBorder(new EmptyBorder(10,10,10,10));
        form_desc.setBackground(Color.WHITE);
        JLabel desc_label = new JLabel("Description", JLabel.LEFT);
        desc_label.setForeground(Gui.black_color);
        form_desc.add(desc_label, BorderLayout.PAGE_START);
        this.desc_field.setForeground(Gui.dark_grey_color);
        this.desc_field.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Gui.dark_grey_color));
        this.desc_field.setPreferredSize(new Dimension(300, 30));
        form_desc.add(this.desc_field, BorderLayout.PAGE_END);

        JPanel form_key = new JPanel(new BorderLayout());
        form_key.setPreferredSize(new Dimension(300, 80));
        form_key.setBorder(new EmptyBorder(10,10,10,10));
        form_key.setBackground(Color.WHITE);
        JLabel key_label = new JLabel("Key", JLabel.LEFT);
        key_label.setForeground(Gui.black_color);
        form_key.add(key_label, BorderLayout.PAGE_START);
        this.key_field.setForeground(Gui.dark_grey_color);
        this.key_field.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Gui.dark_grey_color));
        this.key_field.setPreferredSize(new Dimension(300, 30));
        form_key.add(this.key_field, BorderLayout.PAGE_END);

        form.add(form_account);
        form.add(form_desc);
        form.add(form_key);

        addAccountDialog.error_label.setForeground(Gui.black_color);
        addAccountDialog.error_label.setFont(Gui.mainFont);
        addAccountDialog.error_label.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        addAccountDialog.error_label.setVisible(false);

        this.add(header, BorderLayout.PAGE_START);
        this.add(form, BorderLayout.CENTER);
        this.add(error_label, BorderLayout.PAGE_END);
        this.setVisible(true);
    }

    private Boolean checkAnswer() {
        if (this.account_field.getText().equals("utilisateur@example.com") || this.account_field.getText().equals("")) {
            return false;
        }
        if (this.desc_field.getText().equals("Google") || this.desc_field.getText().equals("")) {
            return false;
        }
        if (this.key_field.getText().equals("abcd efgh ijkl mnop") || this.desc_field.getText().equals("") || this.key_field.getText().length() < 4) {
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (this.checkAnswer()) {
            this.parent.addDialogCallback(new AccountModel(this.account_field.getText(), this.desc_field.getText(), this.key_field.getText()));
            this.setVisible(false);
            this.dispose();
        }
        else {
            addAccountDialog.error_label.setVisible(true);
        }
    }

}
