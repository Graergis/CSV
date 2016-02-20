package ru.grishin.csv.search;

import ru.grishin.csv.search.exception.FindException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/*
Класс служит для поиска выражения в csv файле.
 */
public class Find {

    private final String col;
    private final String exp;
    private final String in;
    private final String enc;

    public Find(String col, String exp, String in, String enc) {
        this.col = col;
        this.exp = exp;
        this.in = in;
        this.enc = enc;
    }

    public byte[] generate() throws IOException, FindException {
        boolean fix = true;
        int index = -1;
        String s1 = new String(Files.readAllBytes(Paths.get(in)), enc);
        RowSplitter rowSplitter = new RowSplitter();
        List<String> rows = rowSplitter.split(s1);
        List<String> list = new ArrayList<>();
        for (int j = 0; j < rows.size(); j++) {
            if (j == 0) {
                String[] columns = rows.get(j).split(";");
                for (int i = 0; i < columns.length; i++) {
                    String[] columns2 = columns[i].split(" ");
                    if (col.equals(columns2[0])) {
                        Check.checkType(columns2[1], exp);
                        index = i;
                        System.out.println(rows.get(j));
                        list.add(rowSplitter.getSource(rows.get(j)));
                        break;
                    }
                }
                if (index < 0) {
                    throw new FindException("Столбец " + col + " указан не верно.");
                }
            } else {
                String[] s = CellSplitter.split(rows.get(j));
                if (s[index].equals(exp)) {
                    System.out.println(rows.get(j));
                    list.add(rowSplitter.getSource(rows.get(j)));
                    fix = false;
                }
            }
        }
        if (fix) {
            throw new FindException("Параметр поиска - " + exp + ", не найден.");
        }
        StringBuilder builder = new StringBuilder();
        for (String s : list) {
            builder.append(s).append("\r\n");
        }
        return builder.toString().getBytes();
    }
}