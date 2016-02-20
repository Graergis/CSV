package ru.grishin.csv.search;

import ru.grishin.csv.search.exception.InvalidArgumentException;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

/*
Класс служит для получения и проверки входных параметров.
 */
public class ArgsParser {

    public Args parse(String[] args) throws InvalidArgumentException {
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
        if (!in.endsWith(".csv")) {
            throw new InvalidArgumentException("Неверное расширение входного файла.");
        } else {
            if ((new File(in)).exists()) {
                System.out.println("Используемый файл - " + in);
            } else
                throw new InvalidArgumentException("Указанный файл не найден.");
        }
        if (!out.endsWith(".csv")) {
            throw new InvalidArgumentException("Неверное расширение выходного файла.");
        }
        if (enc.equals("")) {
            throw new InvalidArgumentException("Не заполнен параметр enc.");
        } else {
            try {
                Charset.forName(enc);
            } catch (UnsupportedCharsetException e) {
                throw new InvalidArgumentException("Задана неподдерживаемая кодировка.");
            }
        }
        if (col.equals("")) {
            throw new InvalidArgumentException("Не заполнен параметр col.");
        }
        if (exp.equals("")) {
            throw new InvalidArgumentException("Не заполнен параметр exp.");
        }
        return new Args(in, out, enc, col, exp);
    }
}