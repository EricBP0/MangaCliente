package com.example.mangastorecliente.Controler;

import com.example.mangastorecliente.CadastroFX;
import com.example.mangastorecliente.DAO.LoginDAO;
import com.example.mangastorecliente.LoginFX;
import com.example.mangastorecliente.LojaFX;
import com.example.mangastorecliente.Model.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.ImageView;

public class ControleLogin implements Initializable {

    @FXML
    private Button button_Cadastrar;

    @FXML
    private Button button_login;

    @FXML
    public TextField textfild_login;

    @FXML
    private TextField textfild_senha;

    @FXML
    private ImageView imagem_hiroshi;

    @FXML
    private ImageView imagem_manga;

    @FXML
    void entrarTelaCadastro(ActionEvent ae) {
        button_Cadastrar.setOnMouseClicked((event ->{
            System.out.println("Entrar Tela Cadastro");
            CadastroFX cadastroFX = new CadastroFX();
            LoginFX.getStage().close();
            try{
                cadastroFX.start(new Stage());
            } catch (Exception e){
                Logger.getLogger(ControleCadastro.class.getName())
                        .log(Level.SEVERE,null, e);
            }
        }));
    }

    @FXML
    void entrarTelaLoja(ActionEvent ae) {
        LoginDAO loginDAO = new LoginDAO();
        try {
            if(textfild_login.getText().isEmpty() || textfild_senha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Usuário e senha devem ser preenchidos!");
            }
            if (loginDAO.checkLogin(textfild_login.getText(), textfild_senha.getText())) {
                button_login.setOnMouseClicked((event -> {
                    System.out.println("Entrar Central");
                    LojaFX lojaFX = new LojaFX();
                    LoginFX.getStage().close();
                    try {
                        lojaFX.start(new Stage());
                    } catch (Exception e) {
                        Logger.getLogger(ControleCadastro.class.getName())
                                .log(Level.SEVERE, null, e);
                    }
                }));
            } else {
                JOptionPane.showMessageDialog(null, "Usuario ou Senha incorreto!");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image imagemLogin;
        Image imagemHiroshi;

        try {
            imagemLogin = new Image(new FileInputStream("src/main/java/com/example/mangastorecliente/Drawble/ImagemVariosMangás.jpg"));
            imagemHiroshi = new Image(new FileInputStream("src/main/java/com/example/mangastorecliente/Drawble/hiroshi.png"));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        imagem_manga.setImage(imagemLogin);
        imagem_hiroshi.setImage(imagemHiroshi);
    }
}

