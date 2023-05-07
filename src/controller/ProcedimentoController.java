package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.Consulta;
import model.Procedimento;
import model.DAO.ProcedimentoDAO;

public class ProcedimentoController {

    public static void cadastrarProcedimento(String nome, Consulta consulta, Date diaHorario, String estado,
            double valor, String laudo) {
        ProcedimentoDAO.cadastrarProcedimento(nome, consulta, diaHorario, estado, valor, laudo);
    }

    public static void atualizarProcedimento(int id, String nome, Consulta consulta, Date diaHorario, String estado,
            double valor, String laudo) {
        ProcedimentoDAO.atualizarProcedimento(id, nome, consulta, diaHorario, estado, valor, laudo);
    }

    public static void removerProcedimento(int id) {
        ProcedimentoDAO.removerProcedimento(id);
    }

    public static Procedimento buscarProcedimento(int id) {
        return ProcedimentoDAO.buscarProcedimento(id);
    }

    public static Procedimento[] listarProcedimentos() {
        return ProcedimentoDAO.listarProcedimentos();
    }

    public static Procedimento[] listarProcedimentosPorConsulta(Consulta consulta) {
        return ProcedimentoDAO.listarProcedimentosPorConsulta(consulta);
    }
    public static void menuProcedimento() {
		try (Scanner scanner = new Scanner(System.in)) {
			int opcao = 0;

			do {
				System.out.println("====== Menu Procedimento ======");
				System.out.println("1 - Cadastrar Procedimento");
				System.out.println("2 - Atualizar Procedimento");
				System.out.println("3 - Remover Procedimento");
				System.out.println("4 - Buscar Procedimento");
				System.out.println("5 - Listar Procedimentos");
				System.out.println("6 - Listar Procedimentos por Consulta");
				System.out.println("0 - Sair");
				System.out.print("Digite uma opção: ");
				opcao = scanner.nextInt();
				
				switch(opcao) {
				case 1:
					 System.out.println("== Cadastrar Procedimento ==");

	                    // Ler informações do usuário
	                    System.out.print("Nome: ");
	                    String nome = scanner.nextLine();
	                    System.out.print("ID da Consulta: ");
	                    int idConsulta = scanner.nextInt();
	                    Consulta consulta = ConsultaController.buscarConsulta(idConsulta);
	                    System.out.print("Dia/Horário (dd/MM/yyyy hh:mm): ");
	                    String dataConsultaStr = scanner.nextLine();
	                    Date diaHorario = null;
	                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	                    try {
	                        diaHorario = dateFormat.parse(dataConsultaStr);
	                    } catch (ParseException e) {
	                        System.out.println("Data/hora inválida!");
	                        return;
	                    }
	                    System.out.print("Estado: ");
	                    String estado = scanner.nextLine();
	                    System.out.print("Valor: ");
	                    double valor = scanner.nextDouble();
	                    System.out.print("Laudo: ");
	                    String laudo = scanner.nextLine();

	                    // Chamar o método cadastrarProcedimento do controller
	                    ProcedimentoController.cadastrarProcedimento(nome, consulta, diaHorario, estado, valor, laudo);
					break;
				case 2:
					 System.out.println("== Atualizar Procedimento ==");

	                    // Ler informações do usuário
	                    System.out.print("ID do Procedimento: ");
	                    int id = scanner.nextInt();
	                    System.out.print("Novo Nome: ");
	                    String novoNome = scanner.nextLine();
	                    System.out.print("ID da Consulta: ");
	                    int novoIdConsulta = scanner.nextInt();
	                    Consulta novoConsulta = ConsultaController.buscarConsulta(novoIdConsulta);
	                    System.out.print("Dia/Horário (dd/MM/yyyy hh:mm): ");
	                    String dataConsultaStr1 = scanner.nextLine();
	                    Date novoDiaHorario = null;
	                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	                    try {
	                    	novoDiaHorario = dateFormat1.parse(dataConsultaStr1);
	                    } catch (ParseException e) {
	                        System.out.println("Data/hora inválida!");
	                        return;
	                    }
	                    System.out.print("Novo Estado: ");
	                    String novoEstado = scanner.nextLine();
	                    System.out.print("Novo Valor: ");
	                    double novoValor = scanner.nextDouble();
	                    System.out.print("Novo Laudo: ");
	                    String novoLaudo = scanner.nextLine();

	                    // Chamar o método atualizarProcedimento do controller
	                    ProcedimentoController.atualizarProcedimento(id, novoNome, novoConsulta, novoDiaHorario, novoEstado, novoValor, novoLaudo);
	                   
					break;
				case 3:
                    System.out.println("== Remover Procedimento ==");

                    // Ler informações do usuário
                    System.out.print("ID do Procedimento: ");
                    int idProcedimento = scanner.nextInt();

                    // Chamar o método removerProcedimento do controller
                    ProcedimentoController.removerProcedimento(idProcedimento);
                    
					break;
				case 4:
					System.out.println("== Buscar Procedimento ==");

                    // Ler informações do usuário
                    System.out.print("ID do Procedimento: ");
                    int idBusca = scanner.nextInt();

                    // Chamar o método buscarProced
                    Procedimento procedimento = ProcedimentoController.buscarProcedimento(idBusca);
                    if (procedimento != null) {
                        System.out.println("Procedimento encontrado:");
                        System.out.println(procedimento.toString());
                    } else {
                        System.out.println("Procedimento não encontrado.");
                    }
					break;
				case 5:
	                System.out.println("== Listar Procedimentos ==");

	                // Chamar o método listarProcedimentos do controller
	                Procedimento[] procedimentos = ProcedimentoController.listarProcedimentos();

	                if (procedimentos.length > 0) {
	                    for (Procedimento p : procedimentos) {
	                        System.out.println(p.toString());
	                    }
	                } else {
	                    System.out.println("Não há procedimentos cadastrados.");
	                }
	                
					break;
				case 6:
	                System.out.println("== Listar Procedimentos por Consulta ==");

	                // Ler informações do usuário
	                System.out.print("ID da Consulta: ");
	                int idConsultaBusca = scanner.nextInt();
	                Consulta consultaBusca = ConsultaController.buscarConsulta(idConsultaBusca);

	                if (consultaBusca != null) {
	                    // Chamar o método listarProcedimentosPorConsulta do controller
	                    Procedimento[] procedimentosConsulta = ProcedimentoController.listarProcedimentosPorConsulta(consultaBusca);

	                    if (procedimentosConsulta.length > 0) {
	                        for (Procedimento p : procedimentosConsulta) {
	                            System.out.println(p.toString());
	                        }
	                    } else {
	                        System.out.println("Não há procedimentos cadastrados para essa consulta.");
	                    }
	                } else {
	                    System.out.println("Consulta não encontrada.");
	                }
	                break;
	            case 0:
	                System.out.println("Saindo do sistema...");
	                break;
	            default:
	                System.out.println("Opção inválida.");
				}
				
				
			} while (opcao != 0);
		}
    }
}
