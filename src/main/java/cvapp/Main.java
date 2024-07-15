package cvapp;

import cvapp.repository.IDB;
import cvapp.repository.JsonDB;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        IDB db = new JsonDB("db.json");

        App app = new App(db);

        app.run();

    }
}
