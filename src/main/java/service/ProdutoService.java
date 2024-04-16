package service;

import dao.ProdutoDAO;
import model.Produto;
import spark.Request;
import spark.Response;
import java.net.URISyntaxException;

public class ProdutoService {
    private ProdutoDAO produtoDAO;
    
    public ProdutoService() {
        this.produtoDAO = new ProdutoDAO();
    }
    
    public Object add(Request request, Response response) throws URISyntaxException {
        produtoDAO.connect();
        String nome = request.queryParams("nome");
        int lote = Integer.parseInt(request.queryParams("lote"));
        int quantidade = Integer.parseInt(request.queryParams("quantidade"));
        String categoria = request.queryParams("categoria");
        int funcionarioCpf = Integer.parseInt(request.queryParams("funcionario_cpf"));
        int estoqueId = Integer.parseInt(request.queryParams("estoque_id"));
        
        Produto produto = new Produto(nome, lote, quantidade, categoria, funcionarioCpf, estoqueId);
        
        produtoDAO.add(produto);
        
        response.status(201); // Created
        response.redirect("../index.html");
        
        int idMax = produtoDAO.getIdMax();
        
        produtoDAO.close();
        
        return idMax;
    }
    
    public Object get(Request request, Response response) throws URISyntaxException {
        produtoDAO.connect();
        
        int id = Integer.parseInt(request.params(":id"));
        
        Produto produto = produtoDAO.get(id);
        
        produtoDAO.close();
        
        response.header("Content-Type", "application/json");
        response.header("Content-Encoding", "UTF-8");
        
        if (produto != null) {
            return produto.toJson();
        } else {
            response.status(404); // Not Found
            response.redirect("/notfound.html");
            return null;
        }
    }
}
