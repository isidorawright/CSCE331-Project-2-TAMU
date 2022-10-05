package edu.tamu.spinnstone;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
      try {
        Migration migration = new Migration(
            "csce331_904_kevin",
            "friendlyalpaca",
            "jdbc:postgresql://csce-315-db.engr.tamu.edu:5432/csce331_904_52"
          );

        migration.populate();
      }  catch(Exception e) {
        System.out.println(e);
        e.printStackTrace(System.out);
      }
    }
}
