package ru.grishin.csv.search;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        boolean fix = true;
        int index = -1;
        String s1 = new String(Files.readAllBytes(Paths.get(in)));
        String[] value = s1.split("\r\n");
        for (int j = 0; j< value.length; j++) {
            if (j == 0) {
                String[] columns = value[0].split(";");
                for (int i = 0; i < columns.length; i++) {
                    String[] columns2 = columns[i].split(" ");
                    if (col.equals(columns2[0])) {
                        index = i;
                        System.out.println(value[0]);
                        result += value[0] + "\r\n";
                        break;
                    }
                }
                if (index < 0) {
                    System.out.println("Столбец " + col + " указан не верно.");
                    break;
                }
            } else {
                String[] s = ColumnUtils.parse(value[j]);
                if (s[index].equals(exp)) {
                    System.out.println(value[j]);
                    result += value[j] + "\r\n";
                    fix = false;
                }
            }
        }
        if (fix) {
            System.out.println("Параметр поиска - " + exp + ", не найден.");
        }
        return result.getBytes();
    }
}