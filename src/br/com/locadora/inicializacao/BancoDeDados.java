package br.com.locadora.inicializacao;

import br.com.locadora.clientes.Clientes;
import br.com.locadora.locacao.Locacao;
import br.com.locadora.veiculos.Veiculos;

import java.util.*;

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
    }

    public List<Clientes> buscaClientes(){
        return Collections.unmodifiableList(listaDeClientes);
    }

    public List<Veiculos> buscaVeiculos(){
        return Collections.unmodifiableList(listaDeVeiculos);
    }
}
