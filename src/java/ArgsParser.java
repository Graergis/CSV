package searchCSV;

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
                    if (!in.endsWith(".csv")){
                        throw new ParseException("Неверное расширение входного файла");
                    }
                    break;
                }
                case "-out": {
                    out = args[i + 1];
                    if (!out.endsWith(".csv")) {
                        throw new ParseException("Неверное расширение выходного файла");
                    }
                    break;
                }
                case "-enc": {
                    enc = args[i + 1];
                    if (!enc.endsWith("UTF-8")){
                        throw new ParseException("Неверная кодировка файла");
                    }
                    break;
                }
                case "-col": {
                    col = args[i + 1];
                    if (!col.endsWith("")){
                        throw new ParseException("Неверное название столбца");
                    }
                    break;
                }
                case "-exp": {
                    exp = args[i + 1];
                    if (!exp.endsWith("")){
                        throw new ParseException("Некорректное значение");
                    }
                    break;
                }
            }
        }
        return new Args(in, out, enc, col, exp);
    }
}
