package ru.grishin.csv.search;

import ru.grishin.csv.search.exception.FindException;
import ru.grishin.csv.search.exception.InvalidArgumentException;

import java.io.FileOutputStream;
import java.io.IOException;

/*
Основной класс программы.
 */
public class Search {

    public static void main(String[] args) throws IOException, InvalidArgumentException {
        try {
            ArgsParser parser = new ArgsParser();
            Args arguments = parser.parse(args);
            try (FileOutputStream fos = new FileOutputStream(arguments.getOut())) {
                Find find = new Find(arguments.getCol(), arguments.getExp(), arguments.getIn(), arguments.getEnc());
                fos.write(find.generate());
            }
        } catch (InvalidArgumentException | FindException e) {
            System.out.println(e.getMessage());
        }
    }
}