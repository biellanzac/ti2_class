package service;

import dao.FuncionarioDAO;
import model.Funcionario;
import spark.Request;
import spark.Response;
import java.net.URISyntaxException;

public class FuncionarioService {
    private FuncionarioDAO funcionarioDAO;
    
    public FuncionarioService() {
        this.funcionarioDAO = new FuncionarioDAO();
    }
    
    public Object add(Request request, Response response) throws URISyntaxException {
        funcionarioDAO.connect();
        String cpf = request.queryParams("cpf");
        String nome = request.queryParams("nome");
        String sobrenome = request.queryParams("sobrenome");
        String email = request.queryParams("email");
        String senha = request.queryParams("senha");
        int idade = Integer.parseInt(request.queryParams("idade"));
        float salario = Float.parseFloat(request.queryParams("salario"));
        String supervisor = request.queryParams("supervisor");
        
        Funcionario funcionario = new Funcionario(cpf, nome, sobrenome, email, senha, idade, salario, supervisor);
        
        funcionarioDAO.add(funcionario);
        
        response.status(201); // Created
        response.redirect("../index.html");
        
        int idMax = funcionarioDAO.getIdMax();
        
        funcionarioDAO.close();
        
        return idMax;
    }
    
    public Object get(Request request, Response response) throws URISyntaxException {
        funcionarioDAO.connect();
        
        String cpf = request.params(":cpf");
        
        Funcionario funcionario = funcionarioDAO.get(cpf);
        
        funcionarioDAO.close();
        
        response.header("Content-Type", "application/json");
        response.header("Content-Encoding", "UTF-8");
        
        if (funcionario != null) {
            return funcionario.toJson();
        } else {
            response.status(404); // Not Found
            response.redirect("/notfound.html");
            return null;
        }
    }
}
