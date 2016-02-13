package ru.grishin.csv.search;

import java.util.ArrayList;
import java.util.List;

public class ColumnUtils {

    public static String[] parse(String line)  {
        String s = "";
        boolean spec = false;
        String test = "\";";
        List result2 = new ArrayList<>();
        for (char c : line.toCharArray()) {
            if ("\"".equals(s)) {
                spec = true;
                s = s.substring(0, s.length() -1);
            }
            if (spec){
                s += c;
                if (s.endsWith(test)) {
                    s = s.substring(0, s.length() -2);
                    s = s.replace("\"\"", "\"");
                    result2.add(s);
                    s = "";
                    spec = false;
                }
            }  else {
                if (';' != c) {
                    s += c;
                } else {
                    if (s.startsWith("\"")) {
                        s = s.substring(1, s.length() - 1);
                    }
                    result2.add(s);
                    s = "";
                    spec = false;
                }
            }
        }
        return (String[]) result2.toArray(new String[result2.size()]);
    }
}