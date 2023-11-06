package software.ulpgc.moneycalculator.gui;

import javax.swing.*;

public class SelectBadge extends JPanel {
    private final JComboBox<String> listBoxReference;
    private final JComboBox<String> listBoxTo;

    public SelectBadge(JComboBox<String> listBoxReference, JComboBox<String> listBoxTo){
        this.listBoxReference = listBoxReference;
        this.listBoxTo = listBoxTo;
        initializeSelectBadge();
    }

    private void initializeSelectBadge() {
        this.add(listBoxReference);
        this.add(listBoxTo);
    }



}
