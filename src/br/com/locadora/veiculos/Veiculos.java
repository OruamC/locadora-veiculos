package br.com.locadora.veiculos;

public class Veiculos {

    private String anoDeFabricacao;
    private String modelo;
    private double valorDiario;
    private int quantidadeDeVeiculosDisponiveis;

    public Veiculos(String anoDeFabricacao, String modelo, double valorDiario) {
        this.anoDeFabricacao = anoDeFabricacao;
        this.modelo = modelo;
        this.valorDiario = valorDiario;
        this.quantidadeDeVeiculosDisponiveis = 1;
    }

    public String getAnoDeFabricacao() {
        return anoDeFabricacao;
    }

    public String getModelo() {
        return modelo;
    }

    public double getValorDiario() {
        return valorDiario;
    }

    public int getQuantidadeDeVeiculosDisponiveis() {
        return quantidadeDeVeiculosDisponiveis;
    }

    public void adicionarQuantidadeParaFrota(int valor) {
        this.quantidadeDeVeiculosDisponiveis += valor;
    }

    public void locou() {
        this.quantidadeDeVeiculosDisponiveis--;
    }

    public void devolveu() {
        this.quantidadeDeVeiculosDisponiveis++;
    }
}
