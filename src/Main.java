import com.company.DbFunctions;

import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DbFunctions db = DbFunctions.getInstance();
        Connection conn = db.connect_to_db("gestion_biblio", "postgres", "Abdo@2023");
        db.createTable(conn ,"Etudiant");
        db.closeConnection();

    }


}