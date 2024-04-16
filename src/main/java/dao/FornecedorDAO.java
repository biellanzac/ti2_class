package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Fornecedor;

public class FornecedorDAO extends Banco implements DAO<Fornecedor> {

    @Override
    public Fornecedor get(int id) {
        Fornecedor fornecedor = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM fornecedor WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                fornecedor = new Fornecedor(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("endereco"),
                    resultSet.getString("celular"),
                    resultSet.getString("categoria")
                );
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fornecedor;
    }

    @Override
    public void add(Fornecedor fornecedor) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO fornecedor (nome, endereco, celular, categoria) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, fornecedor.getNome());
            preparedStatement.setString(2, fornecedor.getEndereco());
            preparedStatement.setString(3, fornecedor.getCelular());
            preparedStatement.setString(4, fornecedor.getCategoria());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Fornecedor fornecedor) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE fornecedor SET nome = ?, endereco = ?, celular = ?, categoria = ? WHERE id = ?");
            preparedStatement.setString(1, fornecedor.getNome());
            preparedStatement.setString(2, fornecedor.getEndereco());
            preparedStatement.setString(3, fornecedor.getCelular());
            preparedStatement.setString(4, fornecedor.getCategoria());
            preparedStatement.setInt(5, fornecedor.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Fornecedor fornecedor) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM fornecedor WHERE id = ?");
            preparedStatement.setInt(1, fornecedor.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Fornecedor[] getAll() {
        Fornecedor[] fornecedores = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM fornecedor");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();
            fornecedores = new Fornecedor[resultSet.getRow()];
            resultSet.beforeFirst();
            int index = 0;
            while (resultSet.next()) {
                fornecedores[index] = new Fornecedor(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("endereco"),
                    resultSet.getString("celular"),
                    resultSet.getString("categoria")
                );
                index++;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fornecedores;
    }

    @Override
    public int getIdMax() {
        int maxId = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(id) AS max_id FROM fornecedor");
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
