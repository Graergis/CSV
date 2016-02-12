package ru.graergis.csv.search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ColumnUtils{
    private final String exp;
    private final String in;

    public ColumnUtils(String exp, String in) {
        this.exp = exp;
        this.in = in;
    }

    public String search() {
        String s = "";
        boolean spec = false;
        boolean print = true;
        String line;
        int i2 = -1;
        String test = "\";";
        String result1 = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(in))) {
            while ((line = reader.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    if (spec) {
                        if (';' == c) {
                            spec = false;
                            if (s.equals(exp)) {
                                if (i2 == Find.index) { //Проверка соотношения строки в определенном столбце.
                                    System.out.println(line);
                                    result1 += line + "\r\n";
                                    print = false; //Предотвращает вывод на экран об неудачном поиске,в случаи выполнения результата.
                                }
                            }
                            else s += c;
                            if (s.endsWith(test)){ //Предотвращает продолжение сбора строки, если "спец" строка заканчивается на """;.
                                s = "";
                                i2++;
                            }
                        } else
                        if ('\"' == c) {
                            s += c;
                            spec = false;
                        } else
                        if (s.equals(exp)) {
                            if (i2 == Find.index) { //Проверка соотношения строки в определенном столбце.
                                System.out.println(line);
                                result1 += line + "\r\n";
                                print = false; //Предотвращает вывод на экран об неудачном поиске,в случаи выполнения результата.
                            }
                        } else {
                            s += c;
                        }
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
                                    if (i2 == Find.index) { //Проверка соотношения строки в определенном столбце.
                                        System.out.println(line);
                                        result1 += line + "\r\n";
                                        print = false; //Предотвращает вывод на экран об неудачном поиске,в случаи выполнения результата.
                                    }
                                }
                                s = "";
                                i2++;
                                spec = false;
                            }
                        }
                    }
                }
                i2 = 0; //обнуления счетчика столбцов в строке.
            }
            if (print) {
                System.out.println("Параметр поиска - " + exp + ", не найден.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result1;
    }
}
