package ru.grishin.csv.search;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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
        boolean first = true;
        boolean fix = true;
        int index = -1;
        int maxLine = -1;
        String s1 = "";
        int counter = 0;
        Scanner inFile = new Scanner(new File(in));
        while(inFile.hasNext()) {
            s1 += inFile.nextLine() + "\r\n";
        }
        String[] value = s1.split("\r\n");
        for (int i1 = 0; i1 < value.length; i1++) {
            maxLine = i1;
        }
        while (counter <= maxLine) {
            if (first) {
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
                first = false;
            } else {
                String[] s = ColumnUtils.parse(value[counter]);
                if (s[index].equals(exp)) {
                    System.out.println(value[counter]);
                    result += value[counter] + "\r\n";
                    fix = false;
                }
            }
            counter++;
        }
        if (fix) {
            System.out.println("Параметр поиска - " + exp + ", не найден.");
        }
        return result.getBytes();
    }
}