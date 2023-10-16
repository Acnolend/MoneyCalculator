package software.ulpgc.moneycalculator.gui;



import software.ulpgc.moneycalculator.Recolector;
import software.ulpgc.moneycalculator.Transformador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import static software.ulpgc.moneycalculator.CollectBadge.badges;

public class ControlPane extends JFrame {
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
        setSize(300,200);
        setMinimumSize(new Dimension(300,200));
        setVisible(true);
    }

    private JComponent createSelectBadge() {
        JPanel listPanel = new JPanel();
        listBoxReference = new JComboBox<String>(badges());
        listBoxTo = new JComboBox<String>(badges());
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
                        String result = null;
                        if(amountBadge.getText().equals("")){
                            printOut.setText("¡NO HAS SELECCIONADO NINGUNA CANTIDAD!");
                        } else {
                            try {
                                result = Recolector.getURL("https://v6.exchangerate-api.com/v6/0d36e3f2d88ea6b941227aac/latest/"+listBoxReference.getSelectedItem());
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            Map map = Transformador.MapOfDivisa(result);
                            double newAmount = (double) map.get(listBoxTo.getSelectedItem()) * Double.parseDouble(amountBadge.getText());
                            printOut.setText(amountBadge.getText()+" "+listBoxReference.getSelectedItem()+" ---> "
                                    +newAmount+" "+listBoxTo.getSelectedItem());
                        }
                    }
                }
        );
        buttoms.add(convert);
        return buttoms;
    }

}