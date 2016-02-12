package ru.grishin.csv.search;

import java.io.FileOutputStream;
import java.io.IOException;

public class Search {

    public static void main(String[] args) throws IOException, ParseException {
        try {
            ArgsParser parser = new ArgsParser();
            Args arguments = parser.parse(args);
            try (FileOutputStream fos = new FileOutputStream(arguments.getOut())) {
                byte[] b = new Find(arguments.getCol(), arguments.getExp(), arguments.getIn()).generate();
                fos.write(b);
            }
            new ColumnUtils(arguments.getExp(), arguments.getIn()).search();
        }catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
