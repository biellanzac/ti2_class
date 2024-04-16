import org.json.JSONObject;

public class Produto implements JsonFormatter {
    private int id;
    private String nome;
    private int lote;
    private int quantidade;
    private String categoria;
    private String funcionarioCpf;
    private int estoqueId;

    public Produto() {
        this(0, "", 0, 0, "", "", 0);
    }

    public Produto(int id, String nome, int lote, int quantidade, String categoria, String funcionarioCpf, int estoqueId) {
        super();
        this.id = id;
        this.nome = nome;
        this.lote = lote;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.funcionarioCpf = funcionarioCpf;
        this.estoqueId = estoqueId;
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

    public int getLote() {
        return lote;
    }

    public void setLote(int lote) {
        this.lote = lote;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFuncionarioCpf() {
        return funcionarioCpf;
    }

    public void setFuncionarioCpf(String funcionarioCpf) {
        this.funcionarioCpf = funcionarioCpf;
    }

    public int getEstoqueId() {
        return estoqueId;
    }

    public void setEstoqueId(int estoqueId) {
        this.estoqueId = estoqueId;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", lote=" + lote +
                ", quantidade=" + quantidade +
                ", categoria='" + categoria + '\'' +
                ", funcionarioCpf='" + funcionarioCpf + '\'' +
                ", estoqueId=" + estoqueId +
                '}';
    }

    @Override
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("id", this.getId());
        obj.put("nome", this.getNome());
        obj.put("lote", this.getLote());
        obj.put("quantidade", this.getQuantidade());
        obj.put("categoria", this.getCategoria());
        obj.put("funcionarioCpf", this.getFuncionarioCpf());
        obj.put("estoqueId", this.getEstoqueId());
        return obj;
    }
}
