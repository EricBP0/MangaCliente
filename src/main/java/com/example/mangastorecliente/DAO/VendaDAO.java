package com.example.mangastorecliente.DAO;

import com.example.mangastorecliente.Connection.ConnectionFactory;
import com.example.mangastorecliente.Model.Venda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VendaDAO implements IVendaDAO{
    @Override
    public Venda create(Venda venda) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "INSERT INTO venda (preco, itens, idComprador,situacao) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            System.out.println(venda.getNumero()+ "Create");
            statement.setDouble(1, venda.getPreco());
            statement.setInt(2, venda.getItens());
            statement.setInt(3, venda.getIdComprador());
            statement.setString(4, venda.getSituacao());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            long idGerado = resultSet.getLong(1);
            venda.setNumero(Math.toIntExact(idGerado));

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir venda no banco de dados", e);
        }
        return venda;
    }

    @Override
    public Venda update(Venda venda) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "UPDATE venda SET itens = ?, preco = ?, idComprador = ?, situacao = ? WHERE numero = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, venda.getItens());
            statement.setDouble(2, venda.getPreco());
            statement.setInt(3, venda.getIdComprador());
            statement.setString(4, venda.getSituacao());
            statement.setInt(5, venda.getNumero());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Erro ao atualizar venda : nenhum registro foi modificado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar venda no banco de dados", e);
        }
        return venda;
    }
    public Venda updateSituacao(Venda venda,String situacao) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "UPDATE venda SET situacao = ? WHERE numero = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,situacao);
            statement.setInt(2, venda.getNumero());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Erro ao atualizar venda : nenhum registro foi modificado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar venda no banco de dados", e);
        }
        return venda;
    }

    @Override
    public void delete(Venda venda) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "DELETE FROM venda WHERE numero = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, venda.getNumero());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro a excluir venda", e);
        }
    }

    @Override
    public List<Venda> findAll() {
        List<Venda> venda = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "SELECT * FROM venda";

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Venda vendaBuscada = new Venda(
                        resultSet.getInt("numero"),
                        resultSet.getInt("itens"),
                        resultSet.getDouble("preco"),
                        resultSet.getInt("idComprador"),
                        resultSet.getString("situacao"));
                venda.add(vendaBuscada);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro a buscar a vendas", e);
        }
        return venda;
    }

    @Override
    public Optional<Venda> findById(int id) {
        Optional<Venda> listVenda = Optional.empty();

        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "SELECT * FROM venda WHERE numero = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Venda vendaBuscada = new Venda(
                        resultSet.getInt("numero"),
                        resultSet.getInt("itens"),
                        resultSet.getDouble("preco"),
                        resultSet.getInt("idComprador"),
                        resultSet.getString("situacao"));
                listVenda = Optional.of(vendaBuscada);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar venda no banco de dados", e);
        }

        return listVenda;
    }
}
