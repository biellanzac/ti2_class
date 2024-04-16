package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Funcionario;

public class FuncionarioDAO extends Banco implements DAO<Funcionario> {

    @Override
    public Funcionario get(int id) {
        Funcionario funcionario = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM funcionario WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                funcionario = new Funcionario(
                    resultSet.getString("cpf"),
                    resultSet.getString("nome"),
                    resultSet.getString("sobrenome"),
                    resultSet.getString("email"),
                    resultSet.getString("senha"),
                    resultSet.getInt("idade"),
                    resultSet.getFloat("salario"),
                    resultSet.getString("supervisor")
                );
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionario;
    }

    @Override
    public void add(Funcionario funcionario) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO funcionario (cpf, nome, sobrenome, email, senha, idade, salario, supervisor) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, funcionario.getCpf());
            preparedStatement.setString(2, funcionario.getNome());
            preparedStatement.setString(3, funcionario.getSobrenome());
            preparedStatement.setString(4, funcionario.getEmail());
            preparedStatement.setString(5, funcionario.getSenha());
            preparedStatement.setInt(6, funcionario.getIdade());
            preparedStatement.setFloat(7, funcionario.getSalario());
            preparedStatement.setString(8, funcionario.getSupervisor());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Funcionario funcionario) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE funcionario SET nome = ?, sobrenome = ?, email = ?, senha = ?, idade = ?, salario = ?, supervisor = ? WHERE cpf = ?");
            preparedStatement.setString(1, funcionario.getNome());
            preparedStatement.setString(2, funcionario.getSobrenome());
            preparedStatement.setString(3, funcionario.getEmail());
            preparedStatement.setString(4, funcionario.getSenha());
            preparedStatement.setInt(5, funcionario.getIdade());
            preparedStatement.setFloat(6, funcionario.getSalario());
            preparedStatement.setString(7, funcionario.getSupervisor());
            preparedStatement.setString(8, funcionario.getCpf());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Funcionario funcionario) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM funcionario WHERE cpf = ?");
            preparedStatement.setString(1, funcionario.getCpf());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Funcionario[] getAll() {
        Funcionario[] funcionarios = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM funcionario");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();
            funcionarios = new Funcionario[resultSet.getRow()];
            resultSet.beforeFirst();
            int index = 0;
            while (resultSet.next()) {
                funcionarios[index] = new Funcionario(
                    resultSet.getString("cpf"),
                    resultSet.getString("nome"),
                    resultSet.getString("sobrenome"),
                    resultSet.getString("email"),
                    resultSet.getString("senha"),
                    resultSet.getInt("idade"),
                    resultSet.getFloat("salario"),
                    resultSet.getString("supervisor")
                );
                index++;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }

    @Override
    public int getIdMax() {
        int maxId = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(id) AS max_id FROM funcionario");
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
