package dao;

import java.sql.*;

import model.Produto;

public class ProdutoDAO extends Banco implements DAO<Produto> {

    @Override
    public Produto get(int id) {
        Produto produto = null;
        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM produto WHERE id = " + id;
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("lote"),
                        rs.getInt("quantidade"),
                        rs.getString("categoria"),
                        rs.getString("funcionario_cpf"),
                        rs.getInt("estoque_id")
                );
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produto;
    }

    @Override
    public void add(Produto produto) {
        try {
            Statement st = connection.createStatement();
            String sql = "INSERT INTO produto (nome, lote, quantidade, categoria, funcionario_cpf, estoque_id) VALUES ('" +
                    produto.getNome() + "', " +
                    produto.getLote() + ", " +
                    produto.getQuantidade() + ", '" +
                    produto.getCategoria() + "', '" +
                    produto.getFuncionarioCpf() + "', " +
                    produto.getEstoqueId() +
                    ")";
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Produto produto) {
        try {
            Statement st = connection.createStatement();
            String sql = "UPDATE produto SET " +
                    "nome = '" + produto.getNome() + "', " +
                    "lote = " + produto.getLote() + ", " +
                    "quantidade = " + produto.getQuantidade() + ", " +
                    "categoria = '" + produto.getCategoria() + "', " +
                    "funcionario_cpf = '" + produto.getFuncionarioCpf() + "', " +
                    "estoque_id = " + produto.getEstoqueId() +
                    " WHERE id = " + produto.getId();
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Produto produto) {
        try {
            Statement st = connection.createStatement();
            String sql = "DELETE FROM produto WHERE id = " + produto.getId();
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Produto[] getAll() {
        Produto[] produtos = null;
        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM produto";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                rs.last();
                produtos = new Produto[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    produtos[i] = new Produto(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getInt("lote"),
                            rs.getInt("quantidade"),
                            rs.getString("categoria"),
                            rs.getString("funcionario_cpf"),
                            rs.getInt("estoque_id")
                    );
                }
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    @Override
    public int getIdMax() {
        int id = 0;
        try {
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT MAX(id) AS max_id FROM produto";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                id = rs.getInt("max_id");
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
