package com.example.mangastorecliente;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LojaFX extends Application {

    public int idUsuario;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }


    public static void setStage(Stage stage) {
        LojaFX.stage = stage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginFX.class.getResource("Loja.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Loja");
        stage.setScene(scene);
        setStage(stage);
        stage.show();
    }
}
