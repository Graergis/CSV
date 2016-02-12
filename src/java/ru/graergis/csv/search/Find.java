package ru.graergis.csv.search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Find {

    private final String col;
    private final String exp;
    private final String in;
    public static int index;

    public Find(String col, String exp, String in) {
        this.col = col;
        this.exp = exp;
        this.in = in;
    }

    public byte[] generate() throws IOException {
        String result = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(in))) {
            int index1 = -1;
            boolean first = true;
            String line;
            index = -1;
            while ((line = reader.readLine()) != null) {
                if (first) {
                    String[] columns = line.split(";");
                    for (int i = 0; i < columns.length; i++) {
                        String[] columns2 = columns[i].split(" ");
                        if (col.equals(columns2[0])) {
                            index = i;
                            if ("Integer".equals(columns2[1])) { //Проверка соответствия типа искомого к типу столбца.
                                try {
                                    new Integer(exp);
                                } catch (NumberFormatException e) {
                                    System.out.println("Неверный формат строки для столбца Integer.");
                                    index1 = 2; //Предотвращает печать названия столбцов, в случае неверного формата для столбца.
                                }
                            }
                            if ("Float".equals(columns2[1])) { //Проверка соответствия типа искомого к типу столбца.
                                try {
                                    new Float(exp);
                                } catch (NumberFormatException e) {
                                    System.out.println("Неверный формат строки для столбца Float.");
                                    index1 = 2; //Предотвращает печать названия столбцов, в случае неверного формата для столбца.
                                }
                            }
                            if ("String".equals(columns2[1])) { //Проверка соответствия типа искомого к типу столбца.
                                if (exp.matches("(?i).*[a-zа-я].*")) {
                                    index1 = -1;
                                } else {
                                    System.out.println("Неверный формат строки для столбца String.");
                                    index1 = 2; //Предотвращает печать названия столбцов, в случае неверного формата для столбца.
                                }
                            }
                            if ("Date".equals(columns2[1])) { //Проверка соответствия типа искомого к типу столбца.
                                try {
                                    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                                    format.parse(exp);
                                } catch (Exception e) {
                                    System.out.println("Неверный формат строки для столбца Date.");
                                    index1 = 2; //Предотвращает печать названия столбцов, в случае неверного формата для столбца.
                                }
                            }
                            break;
                        }
                    }
                    if (index < 0) {
                        System.out.println("Столбец " + col + " указан не верно.");
                        break;
                    }
                    if (index1 < 0) {
                        System.out.println(line);
                        result += line + "\r\n";
                    }
                    first = false;
                }
            }
        }
        return result.getBytes();
    }
}