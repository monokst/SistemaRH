
package model;

public class Cargo {

    private int id_cargo;
    private String nome;
    private String funcao;
    private double salarioBase;
    
    //facilita o uso do controller
    public Cargo(){}

    public Cargo(int id_cargo, String nome, String funcao, double salarioBase) {
        this.id_cargo = id_cargo;
        this.nome = nome;
        this.funcao = funcao;
        this.salarioBase = salarioBase;
    }
   

    public int getId_cargo() {
        return id_cargo;
    }

    public String getNome() {
        return nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }
    
    

}
