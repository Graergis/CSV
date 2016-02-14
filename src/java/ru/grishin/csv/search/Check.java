package ru.grishin.csv.search;

import ru.grishin.csv.search.exception.CheckException;

import java.text.SimpleDateFormat;

public class Check {

    public static void check(String col, String exp) {
        if ("Integer".equals(col)) {
            try {
                new Integer(exp);
            } catch (NumberFormatException e) {
                throw new CheckException("Неверный формат строки для столбца Integer.");
            }
        }
        if ("Float".equals(col)) {
            try {
                new Float(exp);
            } catch (NumberFormatException e) {
                throw new CheckException("Неверный формат строки для столбца Float.");
            }
        }
        if ("Date".equals(col)) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                format.parse(exp);
            } catch (Exception e) {
                throw new CheckException("Неверный формат строки для столбца Date.");
            }
        }
    }
}