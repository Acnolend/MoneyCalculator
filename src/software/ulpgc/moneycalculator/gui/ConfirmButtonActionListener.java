package software.ulpgc.moneycalculator.gui;

import software.ulpgc.moneycalculator.Currency;
import software.ulpgc.moneycalculator.ExchangeRate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmButtonActionListener implements ActionListener {
    private final JTextField amountBadge;
    private final JTextField printOut;
    private final JComboBox listBoxReference;
    private final JComboBox listBoxTo;

    public ConfirmButtonActionListener(JTextField amountBadge, JTextField printOut, JComboBox listBoxReference, JComboBox listBoxTo) {
        this.amountBadge = amountBadge;
        this.printOut = printOut;
        this.listBoxReference = listBoxReference;
        this.listBoxTo = listBoxTo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(amountBadge.getText().equals("")){
            printOut.setText("¡NO HAS SELECCIONADO NINGUNA CANTIDAD");
        } else {
            try {
                actionPerformed((Currency) listBoxReference.getSelectedItem(), (Currency) listBoxTo.getSelectedItem());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void actionPerformed(Currency from, Currency to) throws Exception {
        actionPerformed(new ExchangeRate(from,to));
    }

    private void actionPerformed(ExchangeRate exchangeRate) throws Exception {
        String cantidad = amountBadge.getText();
        String nameFrom = exchangeRate.getFrom().getName();
        String nameTo = exchangeRate.getTo().getName();
        float amount = (float) (Math.round(Float.parseFloat(cantidad)*exchangeRate.getURL() * 100d) / 100d);
        printOut.setText(cantidad+" "+nameFrom+" ---> "+amount+" "+nameTo);
    }
    
}
