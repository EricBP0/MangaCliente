package com.example.mangastorecliente.Controler;

import com.example.mangastorecliente.Connection.ConnectionFactory;
import com.example.mangastorecliente.DAO.LoginDAO;
import com.example.mangastorecliente.DAO.VendaDAO;
import com.example.mangastorecliente.Model.Login;
import com.example.mangastorecliente.Model.Venda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControlerFinalizacao implements Initializable {

    @FXML
    private Button button_finalizar;

    @FXML
    private Button button_voltar;

    @FXML
    private Label label_valorFinal;
    @FXML
    private TextArea textFild_endereco;

    @FXML
    private TextField textFild_numeroCartao;

    @FXML
    private TextField textFils_dataValidade;

    @FXML
    private TextField textfild_cvv;

    private Login comprador;

    @FXML
    void finalizarCompra(ActionEvent event) {

        if (textfild_cvv.getText() == null || textFild_endereco.getText() == null || textFild_numeroCartao.getText() == null || textFils_dataValidade.getText() == null) {
            JOptionPane.showMessageDialog(null, "Você deve inserir todos os dados de pagamento");
        } else {
            try (Connection connection = ConnectionFactory.getConnection()) {

                String query = "UPDATE venda SET situacao = 'pago' WHERE situacao = 'aberto'";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.executeUpdate();

                JOptionPane.showMessageDialog(null, "Compra finalizada com sucesso!");

                String queryVendas = "UPDATE venda SET situacao = 'cancelado' WHERE situacao = 'aberto'";
                PreparedStatement statementVendas = connection.prepareStatement(queryVendas);
                statementVendas.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
