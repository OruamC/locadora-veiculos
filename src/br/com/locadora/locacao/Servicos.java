package br.com.locadora.locacao;

import br.com.locadora.clientes.Clientes;
import br.com.locadora.inicializacao.BancoDeDados;
import br.com.locadora.veiculos.Veiculos;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Servicos {

    private BancoDeDados bd;
    Scanner scanner = new Scanner(System.in);
    List<Clientes> listaClientes;
    List<Veiculos> listaVeiculos;
    Map<String, Locacao> listaLocacoesAtivas;

    public void locar(BancoDeDados bd) {
        this.bd = bd;
        listaClientes = bd.listaClientesParaLocacao();
        listaVeiculos = bd.listaVeiculosParaLocacao();

        if(listaClientes.isEmpty() || listaVeiculos.isEmpty()){
            System.out.println("Informações incompletas para seguir com a operação!");
        } else {
            System.out.println("Selecione abaixo para locação: (Obs.: Mostrando apenas clientes que não possuem locação pendente");
            for (int i = 0; i < listaClientes.size(); i++) {
                System.out.println("[" + i + "]" + listaClientes.get(i).getNome());
            }
            System.out.print("Opção desejada: ");
            int opcaoCliente = scanner.nextInt();

            System.out.println("------------------------------------------------");
            System.out.println("Selecione abaixo para locação: (Obs.: Mostrando apenas clientes que não possuem locação pendente");
            for (int i = 0; i < listaVeiculos.size(); i++) {
                System.out.println("[" + i + "]" + listaVeiculos.get(i).getModelo());
            }
            System.out.print("Opção desejada: ");
            int opcaoVeiculo = scanner.nextInt();

            System.out.println("------------------------------------------------");
            System.out.println("Quantas diárias deseja?");
            System.out.print("Opção desejada: ");
            int quantidadeDeDias = scanner.nextInt();

            Locacao novaLocacao = new Locacao(listaClientes.get(opcaoCliente), listaVeiculos.get(opcaoVeiculo), quantidadeDeDias);
            bd.novaLocacao(novaLocacao);
            System.out.println("Locação realizada com sucesso!");
            System.out.println("Valor total da locação: " + novaLocacao.valorTotalDaLocacao() + ". Após isso, multa de 5% por dia de atraso.");
        }
    }

    public void buscarTodasLocaoesNoSistema(BancoDeDados bd){
        this.bd = bd;
        listaLocacoesAtivas = bd.mostrarTodasAsLocacoes();
        System.out.print("Deseja visualizar a lista completa? [1 - Sim/2 -Não] ");
        Integer opcao = scanner.nextInt();
        scanner.nextLine();
        switch (opcao){
            case 1:
                for(Map.Entry<String, Locacao> locacao : listaLocacoesAtivas.entrySet()){
                    System.out.println(locacao.getValue());
                }
                break;
            case 2:
                System.out.print("Digite o CPF que deseja realizar a busca: ");
                String cpf = scanner.nextLine();
                boolean validacao = listaLocacoesAtivas.containsKey(cpf);
                if(validacao){
                    System.out.println(listaLocacoesAtivas.get(cpf));
                } else {
                    System.out.println("Não existe locação ativa para o CPF informado!");
                }
                break;
        }
    }

    public void devolver(BancoDeDados bd) {
        this.bd = bd;
        this.listaLocacoesAtivas = bd.mostrarTodasAsLocacoes();
        if(listaLocacoesAtivas.size() > 0){
            System.out.print("Digite o CPF: ");
            String cpf = scanner.next();
            if(listaLocacoesAtivas.containsKey(cpf)) {
                System.out.print("Digite a data de devolução: [Formato: dd/MM/yyyy] "); // em um sistema real usaria LocalDate.now()
                String dataDevolução = scanner.next();
                listaLocacoesAtivas.get(cpf).calculaValorAPagar(dataDevolução);
                listaLocacoesAtivas.remove(cpf);
            } else {
                System.out.println("Não existe locação para o CPF informado!");
            }
        } else {
            System.out.println("Não existem locações ativas!");
        }
    }
}
