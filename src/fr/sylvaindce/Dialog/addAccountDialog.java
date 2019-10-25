package fr.sylvaindce.Dialog;

import fr.sylvaindce.DataModel.AccountModel;
import fr.sylvaindce.Gui.Gui;
import fr.sylvaindce.Tools.Tools;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class addAccountDialog extends JDialog implements ActionListener, FocusListener {

    private final Gui parent;
    private static final String[] text_placeholder = {"utilisateur@example.com", "My Google Account", "abcd efgh ijkl mnop"};
    private JTextField account_field = new JTextField(addAccountDialog.text_placeholder[0]);
    private JTextField desc_field = new JTextField(addAccountDialog.text_placeholder[1]);
    private JTextField key_field = new JTextField(addAccountDialog.text_placeholder[2]);
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
            ImageIcon add_icon = new ImageIcon(this.getClass().getResource("/fr/sylvaindce/Res/Icons/round_check_circle_outline_white_24dp.png"));
            confirm_button.setIcon(add_icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        confirm_button.addActionListener(this);
        header.add(confirm_button, BorderLayout.LINE_END);
        confirm_button.setName("add");
        confirm_button.setBackground(Gui.primary_color);
        confirm_button.setPreferredSize(new Dimension(24, 24));
        confirm_button.setOpaque(true);
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
        this.account_field.setName("0");
        this.account_field.setForeground(Gui.dark_grey_color);
        this.account_field.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Gui.dark_grey_color));
        this.account_field.setPreferredSize(new Dimension(300, 30));
        this.account_field.addFocusListener(this);

        form_account.add(this.account_field, BorderLayout.PAGE_END);

        JPanel form_desc = new JPanel(new BorderLayout());
        form_desc.setPreferredSize(new Dimension(300, 80));
        form_desc.setBorder(new EmptyBorder(10,10,10,10));
        form_desc.setBackground(Color.WHITE);
        JLabel desc_label = new JLabel("Description", JLabel.LEFT);
        desc_label.setForeground(Gui.black_color);
        form_desc.add(desc_label, BorderLayout.PAGE_START);
        this.desc_field.setName("1");
        this.desc_field.setForeground(Gui.dark_grey_color);
        this.desc_field.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Gui.dark_grey_color));
        this.desc_field.setPreferredSize(new Dimension(300, 30));
        this.desc_field.addFocusListener(this);
        form_desc.add(this.desc_field, BorderLayout.PAGE_END);

        JPanel form_key = new JPanel(new BorderLayout());
        form_key.setPreferredSize(new Dimension(300, 80));
        form_key.setBorder(new EmptyBorder(10,10,10,10));
        form_key.setBackground(Color.WHITE);
        JLabel key_label = new JLabel("Key", JLabel.LEFT);
        key_label.setForeground(Gui.black_color);
        form_key.add(key_label, BorderLayout.PAGE_START);
        this.key_field.setName("2");
        this.key_field.setForeground(Gui.dark_grey_color);
        this.key_field.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Gui.dark_grey_color));
        this.key_field.setPreferredSize(new Dimension(300, 30));
        this.key_field.addFocusListener(this);
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
        if (this.account_field.getText().equals(addAccountDialog.text_placeholder[0]) || !Tools.isEmailValid(this.account_field.getText())) {
            return false;
        }
        if (this.desc_field.getText().length() < 2 ) {
            return false;
        }
        if (this.key_field.getText().equals(addAccountDialog.text_placeholder[2]) || this.key_field.getText().length() < 4) {
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

    @Override
    public void focusGained(FocusEvent e) {
        try {
            if (((JTextField)e.getSource()).getText().equals(addAccountDialog.text_placeholder[Integer.parseInt(((JTextField)e.getSource()).getName())])) {
                ((JTextField)e.getSource()).setText("");
            }
        } catch (NumberFormatException err) {
            err.printStackTrace();
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        try {
            if (((JTextField)e.getSource()).getText().equals("")) {
                ((JTextField)e.getSource()).setText(addAccountDialog.text_placeholder[Integer.parseInt(((JTextField)e.getSource()).getName())]);
            }
        } catch (NumberFormatException err) {
            err.printStackTrace();
        }
    }
}
