package com.example.mangastorecliente.Controler;

import com.example.mangastorecliente.Connection.ConnectionFactory;
import com.example.mangastorecliente.DAO.LoginDAO;
import com.example.mangastorecliente.DAO.MangaDAO;
import com.example.mangastorecliente.Model.Login;
import com.example.mangastorecliente.Model.Manga;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControleLoja implements Initializable {

    @FXML
    private Button button_AddCarrinho;

    @FXML
    private ChoiceBox<String> choiceBox_anime;

    @FXML
    private Label label_nomeLogin;

    @FXML
    private ListView<String> listView_listaMangas;

    @FXML
    private Label label_Confirmacao;
    @FXML
    private Button button_filtrar;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> listAnimes;
        
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "SELECT idCliente FROM logado";
            PreparedStatement statement = connection.prepareStatement(query);
            LoginDAO loginDAO = new LoginDAO();
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            Optional<Login> listlogin = loginDAO.findById(resultSet.getInt("idCliente"));
            Login login = listlogin.get();

            label_nomeLogin.setText(login.getNome());
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar prato no banco de dados", e);
        }

        try {
            listView_listaMangas.getItems().clear();
            MangaDAO mangaDAO = new MangaDAO();

            List<Manga> listMangas = mangaDAO.findAll();
            listAnimes = FXCollections.observableArrayList();

            for (Manga manga : listMangas) {
                StringBuilder sb = new StringBuilder();
                sb = sb.append("ID: ").append(manga.getId()).append(" - Anime:  ").append(manga.getAnime()).append(" - Edição : ").append(manga.getEdicao())
                        .append(" - Título : ").append(manga.getTitulo()).append(" - Preço : ").append(manga.getPreco());
                listView_listaMangas.getItems().add(sb.toString());
                if(!listAnimes.contains(manga.getAnime())) {
                    listAnimes.add(manga.getAnime());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        choiceBox_anime.setItems(null);

        choiceBox_anime.setItems(listAnimes);

        try {
            Connection connection = ConnectionFactory.getConnection();
            LoginDAO loginDAO = new LoginDAO();
            String query = "SELECT idCliente FROM logado";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            loginDAO.desLogado(resultSet.getInt("idCliente"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void mangaSelecionado(MouseEvent event) {
        String manga = listView_listaMangas.getSelectionModel().getSelectedItem();
        if(manga == null || manga.isEmpty()){
            label_Confirmacao.setText("Nenhum Mangá selecionado");
        }else{
            label_Confirmacao.setText("Mangá selecionado :" + manga.substring(3, 5));
        }
    }

    @FXML
    void filtrarMangas(ActionEvent event) {
        String anime = choiceBox_anime.getSelectionModel().getSelectedItem();

        if(!anime.isEmpty()) {
            try {
                listView_listaMangas.getItems().clear();
                MangaDAO mangaDAO = new MangaDAO();

                List<Manga> listMangas = mangaDAO.findAllByFilter(anime);
                for (Manga manga : listMangas) {
                    StringBuilder sb = new StringBuilder();
                    sb = sb.append("ID: ").append(manga.getId()).append(" - Anime:  ").append(manga.getAnime()).append(" - Edição : ").append(manga.getEdicao())
                            .append(" - Título : ").append(manga.getTitulo()).append(" - Preço : ").append(manga.getPreco());
                    listView_listaMangas.getItems().add(sb.toString());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Coloque um filtro por vez");
        }
    }

    public void mangaSelecionado(javafx.scene.input.MouseEvent mouseEvent) {
        String manga = listView_listaMangas.getSelectionModel().getSelectedItem();
        if(manga == null || manga.isEmpty()){
            label_Confirmacao.setText("Nenhum Mangá selecionado");
        }else{
            label_Confirmacao.setText("Mangá selecionado :" + manga.substring(3, 5));
        }
    }

}
