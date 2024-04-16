package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Estoque;

public class EstoqueDAO extends Banco implements DAO<Estoque> {

    @Override
    public Estoque get(int id) {
        Estoque estoque = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM estoque WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                estoque = new Estoque(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("localizacao")
                );
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estoque;
    }

    @Override
    public void add(Estoque estoque) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO estoque (nome, localizacao) VALUES (?, ?)");
            preparedStatement.setString(1, estoque.getNome());
            preparedStatement.setString(2, estoque.getLocalizacao());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Estoque estoque) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE estoque SET nome = ?, localizacao = ? WHERE id = ?");
            preparedStatement.setString(1, estoque.getNome());
            preparedStatement.setString(2, estoque.getLocalizacao());
            preparedStatement.setInt(3, estoque.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Estoque estoque) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM estoque WHERE id = ?");
            preparedStatement.setInt(1, estoque.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Estoque[] getAll() {
        Estoque[] estoques = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM estoque");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();
            estoques = new Estoque[resultSet.getRow()];
            resultSet.beforeFirst();
            int index = 0;
            while (resultSet.next()) {
                estoques[index] = new Estoque(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("localizacao")
                );
                index++;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estoques;
    }

    @Override
    public int getIdMax() {
        int maxId = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(id) AS max_id FROM estoque");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                maxId = resultSet.getInt("max_id");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxId;
    }
}
