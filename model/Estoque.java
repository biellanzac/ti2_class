import org.json.JSONObject;

public class Estoque implements JsonFormatter {
    private int id;
    private String nome;
    private String localizacao;

    public Estoque() {
        this(0, "", "");
    }

    public Estoque(int id, String nome, String localizacao) {
        super();
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return "Estoque{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", localizacao='" + localizacao + '\'' +
                '}';
    }

    @Override
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("id", this.getId());
        obj.put("nome", this.getNome());
        obj.put("localizacao", this.getLocalizacao());
        return obj;
    }
}
