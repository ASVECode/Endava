package Core.Contracts;

import java.util.List;

public interface IReader {
    public List<String> readFile(String fileName);
    public String readLine();
}
