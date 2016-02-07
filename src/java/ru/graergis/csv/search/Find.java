package ru.graergis.csv.search;

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
            boolean spec = false;
            String line;
            String s = "";
            while ((line = reader.readLine()) != null) {
                if (first) {
                    String[] columns = line.split(";");
                    for (int i = 0; i < columns.length; i++) {
                        String[] columns2 = columns[i].split(" ");
                        if (col.equals(columns2[0])) {
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
                    for (char c : line.toCharArray()) {
                        if (spec) {
                            if (';' == c) {
                                spec = false;
                            }
                            if ('\"' == c) {
                                spec = false;
                            }
                            if (s.equals(exp)) {
                                System.out.println(line);
                                result += line + "\r\n";
                                index2 = 4;
                                s = "";
                                spec = false;
                            } else
                                s += c;
                        } else {
                            if ('\"' == c) {
                                spec = true;
                            } else {
                                if (';' != c) {
                                    s += c;
                                } else {
                                    if (s.startsWith("\"")) {
                                        s = s.substring(1, s.length() - 1);
                                    }
                                    if (s.equals(exp)) {
                                        System.out.println(line);
                                        result += line + "\r\n";
                                        index2 = 4;
                                    }
                                    s = "";
                                    spec = false;
                                }
                            }
                        }
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