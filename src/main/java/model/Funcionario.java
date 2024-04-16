import org.json.JSONObject;

public class Funcionario implements JsonFormatter {
    private String cpf;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private int idade;
    private float salario;
    private String supervisor;

    public Funcionario() {
        this("", "", "", "", "", 0, 0.0f, "");
    }

    public Funcionario(String cpf, String nome, String sobrenome, String email, String senha, int idade, float salario, String supervisor) {
        super();
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
        this.salario = salario;
        this.supervisor = supervisor;
    }

    // Getters and Setters

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", idade=" + idade +
                ", salario=" + salario +
                ", supervisor='" + supervisor + '\'' +
                '}';
    }

    @Override
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("cpf", this.getCpf());
        obj.put("nome", this.getNome());
        obj.put("sobrenome", this.getSobrenome());
        obj.put("email", this.getEmail());
        obj.put("senha", this.getSenha());
        obj.put("idade", this.getIdade());
        obj.put("salario", this.getSalario());
        obj.put("supervisor", this.getSupervisor());
        return obj;
    }
}
