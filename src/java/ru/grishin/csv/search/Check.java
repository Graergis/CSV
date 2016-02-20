package ru.grishin.csv.search;

import ru.grishin.csv.search.exception.CheckTypeException;

import java.text.SimpleDateFormat;

public class Check {

    public static void checkType(String type, String exp) {
        if ("Integer".equals(type)) {
            try {
                new Integer(exp);
            } catch (NumberFormatException e) {
                throw new CheckTypeException("Неверный формат строки для столбца Integer.");
            }
        }
        if ("Float".equals(type)) {
            try {
                new Float(exp);
            } catch (NumberFormatException e) {
                throw new CheckTypeException("Неверный формат строки для столбца Float.");
            }
        }
        if ("Date".equals(type)) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                format.parse(exp);
            } catch (Exception e) {
                throw new CheckTypeException("Неверный формат строки для столбца Date.");
            }
        }
    }
}