package br.com.locadora.clientes;

import br.com.locadora.inicializacao.BancoDeDados;
import br.com.locadora.veiculos.Veiculos;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Cadastros {

    private Scanner scanner;
    BancoDeDados bd;
    List<Clientes> clientes;
    List<Veiculos> veiculos;

    public Cadastros() {
        this.scanner = new Scanner(System.in);
        this.bd = new BancoDeDados();
        this.clientes = bd.buscaClientes();
        this.veiculos = bd.buscaVeiculos();
    }

    public void cadastroClientes() {
        clientes = bd.buscaClientes();
        System.out.print("Digita o nome completo: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        Clientes novoCliente = new Clientes(nome, cpf);
        if (clientes.size() == 0) {
            bd.adiciona(novoCliente);
            System.out.println("Cliente cadastrado com sucesso!");
        } else {
            verificaListaClientes(novoCliente);
        }

    }

    public void verificaListaClientes(Clientes novoCliente) {
        clientes = bd.buscaClientes();
        long count = clientes.stream()
                .filter(c -> c.getCpf().equals(novoCliente.getCpf()))
                .count();

        if(count > 0){
            System.out.println("Ja existe um cliente cadastrado com este CPF!");
        } else {
            bd.adiciona(novoCliente);
            System.out.println("Cliente cadastrado com sucesso!");
        }
    }

    public void cadastroVeiculos() {
        veiculos = bd.buscaVeiculos();
        System.out.print("Digite o modelo do veículo: ");
        String modelo = scanner.nextLine();
        System.out.print("Digite o ano de fabricação: ");
        String anoDeFabricacao = scanner.nextLine();
        System.out.print("Digite o valor da diária: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();
        Veiculos novoVeiculo = new Veiculos(anoDeFabricacao, modelo, valor);
        if(veiculos.size() == 0) {
            bd.adiciona(novoVeiculo);
            System.out.println("Veiculo cadastrado com sucesso!");
        } else {
            verificarListaDeVeiculos(novoVeiculo);
        }
    }

    private void verificarListaDeVeiculos(Veiculos novoVeiculo) {
        veiculos = bd.buscaVeiculos();
        List<Veiculos> collect = veiculos.stream()
                .filter(v -> v.getModelo().toLowerCase().equals(novoVeiculo.getModelo().toLowerCase())
                        && v.getAnoDeFabricacao().equals(novoVeiculo.getAnoDeFabricacao()))
                .collect(Collectors.toList());

        if(collect.isEmpty()){
            bd.adiciona(novoVeiculo);
            System.out.println("Veiculo cadastrado com sucesso!");
        }  else {
            System.err.println("Veiculo ja cadastrado!");
        }
    }

    public void adicionarVeiculosAFrota() {
        veiculos = bd.buscaVeiculos();
        if (veiculos.size() != 0) {
            System.out.println("------------------------------------------");
            System.out.println("Escolha dentre as opções abaixo.");
            for (int i = 0; i < veiculos.size(); i++) {
                System.out.println("[" + i + "] " + veiculos.get(i).getModelo() + ", possuí "
                        + veiculos.get(i).getQuantidadeDeVeiculosDisponiveis() + " unidade(s).");
            }
            System.out.print("Opção escolhida: ");
            int opcao = scanner.nextInt();
            System.out.println("---------------------------------------------");
            System.out.println("Quantas unidades deseja acrescentar? ");
            System.out.print("Digite o valor: ");
            int valor = scanner.nextInt();
            veiculos.get(opcao).adicionarQuantidadeParaFrota(valor);
            System.out.println("Operação realizada com sucesso.\nO veiculo " + veiculos.get(opcao).getModelo()
                    + " ficou com " + veiculos.get(opcao).getQuantidadeDeVeiculosDisponiveis() + " unidades.");
        } else {
            System.out.println("Nenhum veiculo cadastrado no sistema!");
        }
    }

    public BancoDeDados retornaBd() {
        return this.bd;
    }
}

