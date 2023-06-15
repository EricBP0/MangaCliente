package com.example.mangastorecliente;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CadastroFX extends Application {
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        CadastroFX.stage = stage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Cadastro.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Cadastro");
        setStage(stage);
        stage.show();
    }
}
