package ru.grishin.csv.search;

import java.io.FileOutputStream;
import java.io.IOException;

public class Search {

    public static void main(String[] args) throws IOException, ParseException {
        try {
            ArgsParser parser = new ArgsParser();
            Args arguments = parser.parse(args);
            FileOutputStream fos = new FileOutputStream(arguments.getOut());
            try  {
                byte[] b = new Find(arguments.getCol(), arguments.getExp(), arguments.getIn()).generate();
                fos.write(b);
            } finally {
                fos.close();
            }
        }catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}