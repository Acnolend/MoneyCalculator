package software.ulpgc.moneycalculator.gui;



import software.ulpgc.moneycalculator.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;


public class ControlPane extends JFrame {
    CurrencyLoader currencyLoader = new CsvFileCurrencyLoader(new File("currencies.csv"));
    Currency[] currencies = currencyLoader.load().toArray(Currency[]::new);
    private JTextField printOut;
    private final JComboBox listBoxReference = new JComboBox<>(currencies);
    private final JComboBox listBoxTo = new JComboBox<>(currencies);
    private JTextField amountBadge;

    public ControlPane() {
        setTitle("Money Calculator");
        JComponent lists = createSelectBadge();
        JComponent out = createTextField();
        JComponent amount = createAmountBadge();
        JComponent buttoms = createButtom();
        JSplitPane firstPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, lists,amount);
        JSplitPane secondPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, firstPanel,out);
        JSplitPane thirdPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, secondPanel,buttoms);
        getContentPane().add(thirdPanel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,200);
        setMinimumSize(new Dimension(600,200));
        setVisible(true);
    }

    private JComponent createSelectBadge() {
        return new SelectBadge(listBoxReference,listBoxTo);
    }

    private JComponent createAmountBadge() {
        amountBadge = new JTextField();
        return amountBadge;
    }

    private JComponent createTextField() {
        printOut = new JTextField("Aqui se retornara el resultado al convertir");
        printOut.setEditable(false);
        return printOut;
    }


    private JComponent createButtom() {
        ConfirmButtonActionListener confirmButtonActionListener = new ConfirmButtonActionListener(
                amountBadge,printOut,listBoxReference,listBoxTo);
        return new ConfirmButton(confirmButtonActionListener);
    }
}