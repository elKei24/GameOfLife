package com.elkei.gol.gui.frames.about;

import com.elkei.gol.gui.ConstantsKt;
import com.elkei.gol.gui.res.GuiResources;

import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel versionLabel;
    private JTextPane helpText;

    public AboutDialog(Frame parent) {
        super(parent, GuiResources.Companion.getDefault().getStringOrKey(GuiResources.ABOUT_TITLE_KEY), true);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        versionLabel.setText(ConstantsKt.VERSION);

        pack();
        setResizable(false);
    }

    private void onOK() {
        // add your code here
        dispose();
    }
}
