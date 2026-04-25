package model;

import java.util.Date;
import model.Endereco;

public class Funcionario {

    private int id_fun;
    private String nome;
    private String cpf;
    private Date dataDeNascimento;
    private Endereco endereco;
    private Date dataDeAdmissao;
    private String genero;
    private String telefone;
    private String email;
    private Cargo cargo;

    private Funcionario(FuncionarioBuilder builder) {
        this.id_fun = builder.id_fun;
        this.nome = builder.nome;
        this.cpf = builder.cpf;
        this.dataDeNascimento = builder.dataDeNascimento;
        this.endereco = builder.endereco;
        this.dataDeAdmissao = builder.dataDeAdmissao;
        this.genero = builder.genero;
        this.telefone = builder.telefone;
        this.email = builder.email;
        this.cargo = builder.cargo;
    }

    // GETTERS

    public int getId_Fun() {
        return id_fun;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Date getDataDeAdmissao() {
        return dataDeAdmissao;
    }

    public String getGenero() {
        return genero;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public Cargo getCargo() {
        return cargo;
    }
 

    // BUILDER
    public static class FuncionarioBuilder {

        private int id_fun;
        private String nome;
        private String cpf;
        private Date dataDeNascimento;
        private Endereco endereco;
        private Date dataDeAdmissao;
        private String genero;
        private String telefone;
        private String email;
        private Cargo cargo;

        // CONSTRUTOR OBRIGATÓRIO
        public FuncionarioBuilder(String nome, String cpf, Date dataDeAdmissao, Cargo cargo) {
            this.nome = nome;
            this.cpf = cpf;
            this.dataDeAdmissao = dataDeAdmissao;
            this.cargo = cargo;
        }

        public FuncionarioBuilder id_fun(int id_fun) {
            this.id_fun = id_fun;
            return this;
        }

        public FuncionarioBuilder dataDeNascimento(Date dataDeNascimento) {
            this.dataDeNascimento = dataDeNascimento;
            return this;
        }

        public FuncionarioBuilder endereco(Endereco endereco) {
            this.endereco = endereco;
            return this;
        }

        public FuncionarioBuilder genero(String genero) {
            this.genero = genero;
            return this;
        }

        public FuncionarioBuilder telefone(String telefone) {
            this.telefone = telefone;
            return this;
        }

        public FuncionarioBuilder email(String email) {
            this.email = email;
            return this;
        }

        public Funcionario build() {
            return new Funcionario(this);
        }
    }
}