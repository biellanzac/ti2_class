package service;

import dao.FornecedorDAO;
import model.Fornecedor;
import spark.Request;
import spark.Response;
import java.net.URISyntaxException;

public class FornecedorService {
    private FornecedorDAO fornecedorDAO;
    
    public FornecedorService() {
        this.fornecedorDAO = new FornecedorDAO();
    }
    
    public Object add(Request request, Response response) throws URISyntaxException {
        fornecedorDAO.connect();
        String nome = request.queryParams("nome");
        String endereco = request.queryParams("endereco");
        String celular = request.queryParams("celular");
        String categoria = request.queryParams("categoria");
        
        Fornecedor fornecedor = new Fornecedor(nome, endereco, celular, categoria);
        
        fornecedorDAO.add(fornecedor);
        
        response.status(201); // Created
        response.redirect("../index.html");
        
        int idMax = fornecedorDAO.getIdMax();
        
        fornecedorDAO.close();
        
        return idMax;
    }
    
    public Object get(Request request, Response response) throws URISyntaxException {
        fornecedorDAO.connect();
        
        int id = Integer.parseInt(request.params(":id"));
        
        Fornecedor fornecedor = fornecedorDAO.get(id);
        
        fornecedorDAO.close();
        
        response.header("Content-Type", "application/json");
        response.header("Content-Encoding", "UTF-8");
        
        if (fornecedor != null) {
            return fornecedor.toJson();
        } else {
            response.status(404); // Not Found
            response.redirect("/notfound.html");
            return null;
        }
    }
}
