package ru.grishin.csv.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RowSplitter {

    private final static String SPLITTER = "\r\n";

    private Map<String, String> map = new HashMap<>();

    public List<String> split(String source) {
        List<String> result = new ArrayList<>();
        String[] strings = source.split(SPLITTER);
        for (int i = 0; i < strings.length; i++) {
            String s = strings[i].trim();
            String s1 = strings[i];
            if (!s.endsWith(";")) {
                s += strings[i + 1];
                s1 += SPLITTER + strings[i + 1];
                i++;
            } else {
                String[] dd = s.split("\"");
                if (dd.length > 1) {
                    if (!";".equals(dd[dd.length - 1])) {
                        s += strings[i + 1];
                        s1 += SPLITTER + strings[i + 1];
                        i++;
                    }
                }
            }
            result.add(s);
            map.put(s, s1);
        }
        return result;
    }

    public String getSource(String s) {
        return map.get(s);
    }
}