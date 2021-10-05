package ir.maktab;


import java.sql.SQLException;

public class Main {

    public static void main(String[] args)  {

        try {
            Auto auto = new Auto();
            auto.showMenu();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
