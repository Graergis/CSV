package ru.grishin.csv.search;

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
            boolean first = true;
            boolean fix = true;
            String line;
            int index = -1;
            while ((line = reader.readLine()) != null) {
                if (first) {
                    String[] columns = line.split(";");
                    for (int i = 0; i < columns.length; i++) {
                        String[] columns2 = columns[i].split(" ");
                        if (col.equals(columns2[0])) {
                            index = i;
                            Check.check(columns2[1], exp);
                            System.out.println(line);
                            result += line + "\r\n";
                            break;
                        }
                    }
                    if (index < 0) {
                        System.out.println("Столбец " + col + " указан не верно.");
                        break;
                    }
                    first = false;
                } else {
                    String[] s = ColumnUtils.parse(line);
                    if (s[index].equals(exp)) {
                        System.out.println(line);
                        result += line + "\r\n";
                        fix = false;
                    }
                }
            }
            if (fix) {
                System.out.println("Параметр поиска - " + exp + ", не найден.");
            }
        }
        return result.getBytes();
    }
}