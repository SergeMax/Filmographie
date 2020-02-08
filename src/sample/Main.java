package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        BDDManager bdd = new BDDManager();
        bdd.start();
        //bdd.lire("src/BDDFilmotheque.sql");

     //   bdd.lire("src/sample/BDDFilm.sql");

        System.out.println(bdd.ask("SELECT * FROM DVDTHEQUE.Film;").get(0).toString());




       bdd.stop();

    }


    public static void main(String[] args) {
        launch(args);

    }
}
