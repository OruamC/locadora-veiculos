package br.com.locadora.locacao;

import br.com.locadora.clientes.Clientes;
import br.com.locadora.veiculos.Veiculos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Locacao {

    private Clientes clientes;
    private Veiculos veiculo;
    private LocalDate dataDeInicio;
    private DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Locacao(Clientes clientes, Veiculos veiculo) {
        this.clientes = clientes;
        this.veiculo = veiculo;
        this.dataDeInicio = LocalDate.now();
    }

    public Clientes getClientes() {
        return clientes;
    }

    public Veiculos getVeiculo() {
        return veiculo;
    }
}
