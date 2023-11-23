package software.ulpgc.moneycalculator;

import java.io.IOException;

public interface Command {
    String execute() throws IOException;
}
