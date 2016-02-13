package ru.grishin.csv.search;

public class Args {
    private final String in;
    private final String out;
    private final String enc;
    private final String col;
    private final String exp;

    public Args(String in, String out, String enc, String col, String exp) {
        this.in = in;
        this.out = out;
        this.enc = enc;
        this.col = col;
        this.exp = exp;
    }

    public String getIn() {
        return in;
    }

    public String getOut() {
        return out;
    }

    public String getCol() {
        return col;
    }

    public String getExp() {
        return exp;
    }

}