package ru.grishin.csv.search;

import ru.grishin.csv.search.exception.ParseException;

import java.io.FileOutputStream;
import java.io.IOException;

public class Search {

    public static void main(String[] args) throws IOException, ParseException {
        try {
            ArgsParser parser = new ArgsParser();
            Args arguments = parser.parse(args);
            try (FileOutputStream fos = new FileOutputStream(arguments.getOut())) {
                Find find = new Find(arguments.getCol(), arguments.getExp(), arguments.getIn());
                fos.write(find.generate());
                fos.close();
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}