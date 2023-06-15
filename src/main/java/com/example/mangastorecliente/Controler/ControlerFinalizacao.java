package com.example.mangastorecliente.Controler;

import com.example.mangastorecliente.FinalizacaoFX;
import com.example.mangastorecliente.LojaFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControlerFinalizacao {

    @FXML
    private Button button_finalizar;

    @FXML
    private Button button_voltar;

    @FXML
    private Label label_valorFinal;

    @FXML
    void finalizarCompra(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Compra finalizada com sucesso!");
    }

}
