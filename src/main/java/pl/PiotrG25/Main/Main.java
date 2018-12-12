package pl.PiotrG25.Main;

import pl.PiotrG25.sql.SQL;

public class Main {
    public static void main(String[] args) {
        SQL sql = new SQL();
        sql.insert().into("name", "\'n\'").into("price", "1.0").execute();
    }
}
