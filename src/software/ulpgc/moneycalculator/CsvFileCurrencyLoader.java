package software.ulpgc.moneycalculator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class CsvFileCurrencyLoader implements CurrencyLoader {
    private final File file;

    public CsvFileCurrencyLoader(File file) {
        this.file = file;
    }

    @Override
    public List<Currency> load() {
        try {
            BufferedReader source = new BufferedReader(new FileReader(file));
            return source.lines()
                    .skip(1)
                    .filter(l -> !l.isBlank())
                    .map(l -> toCurrency(l))
                    .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Currency toCurrency(String line) {
        return toCurrency(line.split(";"));
    }

    private Currency toCurrency(String[] fields) {
        return new Currency(
                fields[1],
                fields[0]
        );
    }
}
