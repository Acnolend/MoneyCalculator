package software.ulpgc.moneycalculator.gui;



import software.ulpgc.moneycalculator.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Map;

public class ControlPane extends JFrame {
    CurrencyLoader currencyLoader = new CsvFileCurrencyLoader(new File("currencies.csv"));
    List<Currency> currencies = currencyLoader.load();
    String[] badges = NameOfCurrencies.nameOfCurriencies(currencies);
    private JTextField printOut;
    private JComboBox listBoxReference;
    private JComboBox listBoxTo;
    private JTextField amountBadge;

    public ControlPane() {
        setTitle("Money Calculator");
        JComponent lists = createSelectBadge();
        JComponent out = createTextField();
        JComponent buttoms = createButtom();
        JComponent amount = createAmountBadge();
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
        JPanel listPanel = new JPanel();
        listBoxReference = new JComboBox<String>(badges);
        listBoxTo = new JComboBox<String>(badges);
        listPanel.add(listBoxReference);
        listPanel.add(listBoxTo);
        return listPanel;
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
        JPanel buttoms = new JPanel();
        JButton convert = new JButton("Convertir!");
        convert.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if(amountBadge.getText().equals("")){
                            printOut.setText("¡NO HAS SELECCIONADO NINGUNA CANTIDAD!");
                        } else {
                            try {
                                Currency from = SearchObjectbyName.SearchObjectbyName(currencies, (String) listBoxReference.getSelectedItem());
                                Currency to = SearchObjectbyName.SearchObjectbyName(currencies, (String) listBoxTo.getSelectedItem());
                                ExchangeRate result = new ExchangeRate(from,to);
                                float amount = (float) (Math.round(Float.parseFloat(amountBadge.getText())*result.getURL() * 100d) / 100d);
                                printOut.setText(amountBadge.getText()+" "+(String) listBoxReference.getSelectedItem()+" ---> "
                                        +amount+" "+(String) listBoxTo.getSelectedItem());
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
        );
        buttoms.add(convert);
        return buttoms;
    }

}