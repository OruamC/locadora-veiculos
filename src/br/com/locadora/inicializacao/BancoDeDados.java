package br.com.locadora.inicializacao;

import br.com.locadora.clientes.Clientes;
import br.com.locadora.locacao.Locacao;
import br.com.locadora.veiculos.Veiculos;

import java.util.*;
import java.util.stream.Collectors;

public class BancoDeDados {

    private List<Clientes> listaDeClientes = new ArrayList<>();
    private List<Veiculos> listaDeVeiculos = new ArrayList<>();
    private Map<String, Locacao> locacoes = new HashMap<>();

    public void adiciona(Clientes cliente){
        listaDeClientes.add(cliente);
    }

    public void adiciona(Veiculos veiculo) {
        listaDeVeiculos.add(veiculo);
    }

    public void novaLocacao(Locacao locacao){
        locacoes.put(locacao.getClientes().getCpf(), locacao);
        locacao.getClientes().locou();
        locacao.getVeiculo().locou();
    }

    public List<Clientes> buscaClientes(){
        return Collections.unmodifiableList(listaDeClientes);
    }

    public List<Veiculos> buscaVeiculos(){
        return Collections.unmodifiableList(listaDeVeiculos);
    }

    public List<Clientes> listaClientesParaLocacao() {
        return listaDeClientes.stream()
                .filter(c -> c.isPossuiLocacao() == false)
                .collect(Collectors.toList());
    }

    public List<Veiculos> listaVeiculosParaLocacao() {
        return listaDeVeiculos.stream()
                .filter(v -> v.getQuantidadeDeVeiculosDisponiveis() > 0)
                .collect(Collectors.toList());
    }

    public Map<String, Locacao> mostrarTodasAsLocacoes() {
        return locacoes;
    }
}
