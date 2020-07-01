package br.com.locadora.inicializacao;

import br.com.locadora.clientes.Cadastros;
import br.com.locadora.clientes.Clientes;
import br.com.locadora.locacao.Servicos;

import java.util.Scanner;

public class Inicializacao {

    public static void main(String[] args) {

        Cadastros cc = new Cadastros();
        Servicos sv = new Servicos();
        Scanner scanner = new Scanner(System.in);
        int inicializacao = 0;

        while (inicializacao >= 0 && inicializacao <= 6) {
            System.out.println("--------------------------------------------");
            System.out.println("Escolha dentre uma das opções abaixo:");
            System.out.println("[0] - Sair");
            System.out.println("[1] - Cadastrar um Cliente.");
            System.out.println("[2] - Cadastrar um Veiculo.");
            System.out.println("[3] - Adicionar veiculos a frota.");
            System.out.println("[4] - Criar uma nova locação.");
            System.out.println("[5] - Buscar todas as locações ou específica por CPF.");
            System.out.println("[6] - Devolver locação.");
            System.out.print("Digite: ");
            inicializacao = scanner.nextInt();
            System.out.println("--------------------------------------------");

            switch (inicializacao) {
                case 0:
                    System.out.println("Obrigado!");
                    inicializacao = -1;
                    break;
                case 1:
                    cc.cadastroClientes();
                    break;
                case 2:
                    cc.cadastroVeiculos();
                    break;
                case 3:
                    cc.adicionarVeiculosAFrota();
                    break;
                case 4:
                    sv.locar(cc.retornaBd());
                    break;
                case 5:
                    sv.buscarTodasLocaoesNoSistema(cc.retornaBd());
                    break;
                case 6:
                    sv.devolver(cc.retornaBd());
                    break;
            }
        }
    }
}
