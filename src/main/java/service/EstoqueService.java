package service;

import dao.EstoqueDAO;
import model.Estoque;
import spark.Request;
import spark.Response;
import java.net.URISyntaxException;

public class EstoqueService {
    private EstoqueDAO estoqueDAO;
    
    public EstoqueService() {
        this.estoqueDAO = new EstoqueDAO();
    }
    
    public Object add(Request request, Response response) throws URISyntaxException {
        estoqueDAO.connect();
        String nome = request.queryParams("nome");
        String localizacao = request.queryParams("localizacao");
        
        Estoque estoque = new Estoque(nome, localizacao);
        
        estoqueDAO.add(estoque);
        
        response.status(201); // Created
        response.redirect("../index.html");
        
        int idMax = estoqueDAO.getIdMax();
        
        estoqueDAO.close();
        
        return idMax;
    }
    
    public Object get(Request request, Response response) throws URISyntaxException {
        estoqueDAO.connect();
        
        int id = Integer.parseInt(request.params(":id"));
        
        Estoque estoque = estoqueDAO.get(id);
        
        estoqueDAO.close();
        
        response.header("Content-Type", "application/json");
        response.header("Content-Encoding", "UTF-8");
        
        if (estoque != null) {
            return estoque.toJson();
        } else {
            response.status(404); // Not Found
            response.redirect("/notfound.html");
            return null;
        }
    }
}
