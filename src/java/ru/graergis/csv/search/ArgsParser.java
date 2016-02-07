package ru.graergis.csv.search;

import java.io.File;

public class ArgsParser {

    public Args parse(String[] args) throws ParseException {
        String in = "";
        String out = "";
        String enc = "";
        String col = "";
        String exp = "";
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-in": {
                    in = args[i + 1];
                    break;
                }
                case "-out": {
                    out = args[i + 1];
                    break;
                }
                case "-enc": {
                    enc = args[i + 1];
                    break;
                }
                case "-col": {
                    col = args[i + 1];
                    break;
                }
                case "-exp": {
                    exp = args[i + 1];
                    break;
                }
            }
        }
        if (!in.endsWith(".csv")){
            throw new ParseException("Неверное расширение входного файла.");
        }else { if ((new File(in)).exists()) {
        } else
            throw new ParseException("Указанный файл не найден.");
        }
        if (!out.endsWith(".csv")) {
            throw new ParseException("Неверное расширение выходного файла.");
        }
        if (!enc.endsWith("UTF-8")){
            throw new ParseException("Неверная кодировка файла.");
        }
        if (!col.endsWith("")){
            throw new ParseException("Неверное название столбца.");
        }
        if (!exp.endsWith("")){
            throw new ParseException("Некорректное значение.");
        }
        return new Args(in, out, enc, col, exp);
    }
}
