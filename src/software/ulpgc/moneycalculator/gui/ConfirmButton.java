package software.ulpgc.moneycalculator.gui;

import javax.swing.*;

public class ConfirmButton extends JPanel {
    private final JButton convert = new JButton("Convertir");

    public ConfirmButton(ConfirmButtonActionListener actionListener){
        initializeConfirmButton(actionListener);
    }

    private void initializeConfirmButton(ConfirmButtonActionListener actionListener) {
        convert.addActionListener(actionListener);
        this.add(convert);
    }
}
