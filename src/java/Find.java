package searchCSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Find {

    private final String col;
    private final String exp;
    private final String in;

    public Find(String col, String exp, String in) {
        this.col = col;
        this.exp = exp;
        this.in = in;
    }

    public byte[] generate() throws IOException {
        String result = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(in))) {
            int index = -1;
            int index2 = -1;
            boolean first = true;
            String line;
            while ((line = reader.readLine()) != null) {
                if (first) {
                    String[] columns = line.split(";");
                    for (int i = 0; i < columns.length; i++) {
                        if (col.equals(columns[i])) {
                            index = i;
                            break;
                        }
                    }
                    if (index < 0) {
                        System.out.println("Столбец " + col + " указан не верно.");
                        break;
                    }
                    System.out.println(line);
                    result += line + "\r\n";
                    first = false;
                } else {
                    String[] columns = line.split(";");
                    if (columns[index].equals(exp)) {
                        System.out.println(line);
                        result += line + "\r\n";
                        index2 = 4;
                    }
                }
            }
            if (index < 0) {
                index2 = 4;
            }
            if (index2 < 0) {
            System.out.println("Параметр поиска - " + exp + ", не найден.");
            }
        }
        return result.getBytes();
    }
}
