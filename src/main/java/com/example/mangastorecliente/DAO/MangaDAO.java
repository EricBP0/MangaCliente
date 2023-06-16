package com.example.mangastorecliente.DAO;

import com.example.mangastorecliente.Connection.ConnectionFactory;
import com.example.mangastorecliente.Model.Manga;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MangaDAO implements IMangaDAO{


    @Override
    public Manga create(Manga manga) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "INSERT INTO manga (anime, edicao, quantidade, titulo, preco) VALUES (?, ? , ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            System.out.println(manga.getTitulo()+ "Create");
            statement.setString(1, manga.getAnime());
            statement.setString(2, manga.getEdicao());
            statement.setInt(3, manga.getQuantidade());
            statement.setString(4, manga.getTitulo());
            statement.setDouble(5, manga.getPreco());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            long idGerado = resultSet.getLong(1);
            manga.setId(Math.toIntExact(idGerado));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir manga no banco de dados", e);
        }
        return manga;
    }

    @Override
    public Manga update(Manga manga) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "UPDATE manga SET anime = ?, edicao = ?, titulo = ?, preco = ?, quantidade = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, manga.getAnime());
            statement.setString(2, manga.getEdicao());
            statement.setString(3, manga.getTitulo());
            statement.setDouble(4, manga.getPreco());
            statement.setInt(5, manga.getQuantidade());
            statement.setInt(6, manga.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Erro ao atualizar Manga: nenhum registro foi modificado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar Manga no banco de dados", e);
        }
        return manga;
    }

    @Override
    public void delete(Manga login) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "DELETE FROM manga WHERE id = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, login.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro a excluir o Manga", e);
        }
    }

    @Override
    public List<Manga> findAll() {
        List<Manga> listaManga = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "SELECT * FROM manga";

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Manga manga = new Manga(
                        resultSet.getInt("id"),
                        resultSet.getString("anime"),
                        resultSet.getString("edicao"),
                        resultSet.getString("titulo"),
                        resultSet.getInt("quantidade"),
                        resultSet.getDouble("preco"));
                listaManga.add(manga);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro a buscar a mangas", e);
        }
        return listaManga;
    }

    @Override
    public Optional<Manga> findById(int id) {
        Optional<Manga> listaManga = Optional.empty();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "SELECT * FROM manga WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Manga manga = new Manga(
                        resultSet.getInt("id"),
                        resultSet.getString("anime"),
                        resultSet.getString("edicao"),
                        resultSet.getString("titulo"),
                        resultSet.getInt("quantidade"),
                        resultSet.getDouble("preco"));
                listaManga = Optional.of(manga);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar manga no banco de dados", e);
        }

        return listaManga;
    }
    public List<Manga> findAllByFilter(String filter) {
        List<Manga> listaManga = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "SELECT * FROM manga WHERE anime = ? ";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,  filter);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Manga manga = new Manga(
                        resultSet.getInt("id"),
                        resultSet.getString("anime"),
                        resultSet.getString("edicao"),
                        resultSet.getString("titulo"),
                        resultSet.getInt("quantidade"),
                        resultSet.getDouble("preco"));
                listaManga.add(manga);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro a buscar a mangas", e);
        }
        return listaManga;
    }
    public List<Manga> findAllByFilterASC() {
        List<Manga> listaManga = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "SELECT * FROM manga ORDER BY preco ASC";

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Manga manga = new Manga(
                        resultSet.getInt("id"),
                        resultSet.getString("anime"),
                        resultSet.getString("edicao"),
                        resultSet.getString("titulo"),
                        resultSet.getInt("quantidade"),
                        resultSet.getDouble("preco"));
                listaManga.add(manga);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro a buscar a mangas", e);
        }
        return listaManga;
    }
    public List<Manga> findAllByFilterDESC() {
        List<Manga> listaManga = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "SELECT * FROM manga ORDER BY preco DESC";

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Manga manga = new Manga(
                        resultSet.getInt("id"),
                        resultSet.getString("anime"),
                        resultSet.getString("edicao"),
                        resultSet.getString("titulo"),
                        resultSet.getInt("quantidade"),
                        resultSet.getDouble("preco"));
                listaManga.add(manga);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro a buscar a mangas", e);
        }
        return listaManga;
    }

}
