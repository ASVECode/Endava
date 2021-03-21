package Core.Providers;

import Core.Contracts.IWriter;

public class ConsoleWriter implements IWriter {
    @Override
    public void write(String message) {
        System.out.print(message);
    }

    @Override
    public void writeLine(String message) {
        System.out.println(message);
    }
}
