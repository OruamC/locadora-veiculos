package br.com.locadora.locacao;

import br.com.locadora.clientes.Clientes;
import br.com.locadora.veiculos.Veiculos;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Locacao {

    private Clientes clientes;
    private Veiculos veiculo;
    private LocalDate dataDeInicio;
    private LocalDate dataMaximaParaDevolução;
    private LocalDate dataDevolucao;
    private Double valorTotalAPagar;
    private int quantidadeDeDiasDeLocacao;
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("#,###.00");

    public Locacao(Clientes clientes, Veiculos veiculo, int quantidadeDeDiasDeLocacao) {
        this.clientes = clientes;
        this.veiculo = veiculo;
        this.dataDeInicio = LocalDate.now();
        this.quantidadeDeDiasDeLocacao = quantidadeDeDiasDeLocacao;
        this.dataMaximaParaDevolução = LocalDate.now().plusDays(quantidadeDeDiasDeLocacao);
    }

    public Locacao(Clientes clientes, Veiculos veiculo, int quantidadeDeDiasDeLocacao, LocalDate date) {
        this.clientes = clientes;
        this.veiculo = veiculo;
        this.dataDeInicio = date;
        this.quantidadeDeDiasDeLocacao = quantidadeDeDiasDeLocacao;
        this.dataMaximaParaDevolução = date.plusDays(quantidadeDeDiasDeLocacao);
    }

    public Clientes getClientes() {
        return clientes;
    }

    public Veiculos getVeiculo() {
        return veiculo;
    }

    public Double valorTotalDaLocacao() {
        return veiculo.getValorDiario() * quantidadeDeDiasDeLocacao;
    }

    @Override
    public String toString() {
        return clientes.getNome() + " locou o modelo " + veiculo.getModelo() + " no dia "
                + dataDeInicio.format(formatador) + " para devolução no dia " + dataMaximaParaDevolução.format(formatador);
    }

    public void calculaValorAPagar(String dataDevolução) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataDevolucao = LocalDate.parse(dataDevolução, formatter);
        Period quantidadeDeDiasReaisLocados = Period.between(dataMaximaParaDevolução, dataDevolucao);
        clientes.devolveu();
        veiculo.devolveu();
        if (quantidadeDeDiasReaisLocados.isNegative()) {
            valorTotalAPagar = veiculo.getValorDiario() * (quantidadeDeDiasDeLocacao - (quantidadeDeDiasReaisLocados.getDays() * -1));
            System.out.println("Resumo da Locação:\nMoledo do Veículo: " + veiculo.getModelo() + "\nData de Inicio: "
                    + dataDeInicio.format(formatador) + "\nData de Devolução: " + dataDevolucao.format(formatador)
                    + "\nTotal de Dias: " + (Period.between(dataDeInicio, dataDevolucao).getDays()) + "\nCom valor diário: R$ "
                    + decimalFormat.format(veiculo.getValorDiario()) + "\n---------------------------------------------------");
            System.out.println("Valor total a pagar: R$ " + decimalFormat.format(valorTotalAPagar));
        } else if (quantidadeDeDiasReaisLocados.isZero()) {
            valorTotalAPagar = valorTotalDaLocacao();
            System.out.println("Resumo da Locação:\nMoledo do Veículo: " + veiculo.getModelo() + "\nData de Inicio: "
                    + dataDeInicio.format(formatador) + "\nData de Devolução: " + dataDevolucao.format(formatador)
                    + "\nTotal de Dias: " + (Period.between(dataDeInicio, dataDevolucao).getDays()) + "\nCom valor diário: R$ "
                    + decimalFormat.format(veiculo.getValorDiario()) + "\n---------------------------------------------------");
            System.out.println("Valor total a pagar: R$ " + decimalFormat.format(valorTotalAPagar));
        } else {
            double multa = (veiculo.getValorDiario() * 0.10 * quantidadeDeDiasReaisLocados.getDays()) + veiculo.getValorDiario() * quantidadeDeDiasReaisLocados.getDays();
            valorTotalAPagar = valorTotalDaLocacao() + multa;
            System.out.println("Resumo da Locação:\nModelo do Veículo: " + veiculo.getModelo() + "\nData de Inicio: "
                    + dataDeInicio.format(formatter) + "\nData Máxima para devolução: " + dataMaximaParaDevolução.format(formatador)
                    + "\nData Real de Devolução: " + dataDevolucao.format(formatador) + "\nResultando em " + quantidadeDeDiasDeLocacao
                    + " de dia(s) combinado(s) de locação + " + quantidadeDeDiasReaisLocados.getDays()
                    + " dia(s) de atraso.\n---------------------------------------------------");
            System.out.println("Valor total a pagar: R$ " + decimalFormat.format(valorTotalAPagar));
        }
    }
}
