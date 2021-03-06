/*
 * "THE BEER-WARE LICENSE" (Revision 42):
 * Elias Keis (elias.keis@gmail.com) wrote this file. As long as you retain this
 * notice you can do whatever you want with this stuff. If we meet some day, and
 * you think this stuff is worth it, you can buy me a beer in return. Elias Keis
 */

package com.elkei.gol.gui.frames.newboard;

import com.elkei.gol.model.Coordinate;
import com.elkei.gol.model.UpdatingBoard;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.elkei.gol.gui.ConstantsKt.*;

public abstract class NewOrResizeBoardDialog extends JDialog {
    private JPanel outerPanel;
    private JButton buttonOK;
    private JButton buttonCancel;
    protected JSpinner xSpinner;
    protected JSpinner ySpinner;
    @Nullable
    private UpdatingBoard generatedBoard = null;

    public NewOrResizeBoardDialog(Frame parent) {
        super(parent, true);
        setContentPane(outerPanel);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        outerPanel.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        xSpinner.setModel(new SpinnerNumberModel(DEFAULT_BOARD_SIZE_X, MINIMUM_BOARD_SIZE_X, MAXIMUM_BOARD_SIZE_X, 1));
        ySpinner.setModel(new SpinnerNumberModel(DEFAULT_BOARD_SIZE_Y, MINIMUM_BOARD_SIZE_Y, MAXIMUM_BOARD_SIZE_Y, 1));

        pack();
        setResizable(false);
    }

    private void onOK() {
        generatedBoard = generateBoard(new Coordinate((int) xSpinner.getValue(), (int) ySpinner.getValue()));
        dispose();
    }

    @NotNull
    protected abstract UpdatingBoard generateBoard(Coordinate size);

    private void onCancel() {
        generatedBoard = null;
        dispose();
    }

    /**
     * Returns the board generated as result of this dialog.
     *
     * @return Board generated as result of this dialog, or null if the dialog is not yet finished or was cancelled
     */
    @Nullable
    public UpdatingBoard getGeneratedBoard() {
        return generatedBoard;
    }
}
