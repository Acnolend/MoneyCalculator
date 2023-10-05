package software.ulpgc.moneycalculator;

import java.util.HashMap;
import java.util.Map;

public class Transformador {
    public static Map MapOfDivisa(String divisa) {
        String resultado = divisa.substring(363,divisa.length()-2).replace(" ","").replace("\"","");
        Map<String, Double> map = new HashMap<>();
        for(String str : resultado.substring(0,resultado.length()-1).split(",")) {
            String[] data = str.split(":");
            map.put(data[0],Double.parseDouble(data[1]));
        }
        return map;
    }
}
