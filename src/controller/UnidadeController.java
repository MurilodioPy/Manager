package controller;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.Franquia;
import model.Pessoa;
import model.Unidade;
import model.DAO.FranquiaDao;
import model.DAO.PessoaDAO;
import model.DAO.UnidadeDAO;
import view.Main;

import static view.Main.exibirMenu;

public class UnidadeController {

    private UnidadeDAO unidadeDAO;

    public UnidadeController() {
        unidadeDAO = new UnidadeDAO();
    }

    public boolean cadastrarUnidade(Unidade unidade) {
        return unidadeDAO.cadastrarUnidade(unidade);
    }

    public Unidade buscarUnidade(int idUnidade) {
        return unidadeDAO.buscarUnidade(idUnidade);
    }

    public boolean atualizarUnidade(int id, String nome, String cidade, String endereco) {
        Unidade unidade = unidadeDAO.buscarUnidade(id);
        if (unidade == null) {
            return false; // Unidade não encontrada
        }

        unidade.setNome(nome);
        unidade.setCidade(cidade);
        unidade.setEnderecoUnidade(endereco);
        unidade.setDataModificacaoUnidade(LocalDateTime.now());

        return unidadeDAO.atualizarUnidade(unidade);
    }

    public boolean excluirUnidade(int idUnidade) {
        return unidadeDAO.excluirUnidade(idUnidade);
    }

    public List<Unidade> listarUnidades() {
        return unidadeDAO.listarUnidades();
    }

//    public static void cadastrarUnidadesAleatorias() {
//        UnidadeController controller = new UnidadeController(); // criando uma instância do controlador
//        for (int i = 0; i < 10; i++) {
//            Unidade unidade = Unidade.gerarUnidadeAleatoria();
//            controller.cadastrarUnidade(unidade);
//        }
//    }
    public static void menuUnidade() throws ParseException {
        try (Scanner sc = new Scanner(System.in)) {
            UnidadeController unidadeController = new UnidadeController();
            System.out.println("\n==============================");
            System.out.println("       MENU DE UNIDADES");
            System.out.println("==============================");
            boolean sair = false;
            PessoaController pessoaController = new PessoaController();
            System.out.println("Digite o seu ID:");
            int idPermissao = sc.nextInt();
            boolean permissao = false;
            sc.nextLine(); // para consumir a quebra de linha deixada pelo nextInt

            Pessoa pessoa = pessoaController.buscarPessoaPorId(idPermissao);

            if (Main.pessoa != null && Main.pessoa.getId() == idPermissao) {
                pessoa = Main.pessoa;
            }

            if (pessoa.getTipoUsuario().equals("DonoFranquia")) {
                permissao = true;
            }
            while (!sair) {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1. Cadastrar Unidade");
                System.out.println("2. Editar Unidade");
                System.out.println("3. Buscar Unidade");
                System.out.println("4. Excluir Unidade");
                System.out.println("5. Listar Unidades");
                System.out.println("0. Sair");

                try {
                    int opcao = sc.nextInt();
                    sc.nextLine(); // para consumir a quebra de linha deixada pelo nextInt

                    switch (opcao) {
                        case 1:// CADASTRAR
                            if (!permissao) {
                                System.out.println("Acesso não autorizado.");
                            } else {
                                System.out.print("Digite o nome da unidade: ");
                                String nome = sc.nextLine();
                                System.out.print("Digite a cidade da unidade: ");
                                String cidade = sc.nextLine();
                                System.out.print("Digite o endereço da unidade: ");
                                String endereco = sc.nextLine();

                                System.out.print("Digite o ID do Responsável: ");
                                int idResponsavel = sc.nextInt();
                                sc.nextLine(); // Limpar o buffer do teclado

                                System.out.print("Digite o ID da franquia: ");
                                int idFranquia = sc.nextInt();
                                sc.nextLine(); // Limpar o buffer do teclado

                                FranquiaDao franquiaDao = new FranquiaDao();
                                Franquia franquia = franquiaDao.buscarFranquia(idFranquia);
                                PessoaDAO pessoadao = new PessoaDAO();
                                Pessoa pessoaResponsavel = pessoadao.buscarPorId(idResponsavel);

                                if (franquia == null) {
                                    System.out.println("Franquia não encontrada.");
                                } else {
                                    Unidade unidade = new Unidade();
                                    unidade.setNome(nome);
                                    unidade.setCidade(cidade);
                                    unidade.setEndereco(endereco);
                                    unidade.setResponsavel(pessoaResponsavel);
                                    unidade.setFranquia(franquia);
                                    unidade.setDataCriacao(LocalDateTime.now());
                                    unidade.setDataModificacao(LocalDateTime.now());

                                    boolean cadastrado = unidadeController.cadastrarUnidade(unidade);

                                    if (cadastrado) {
                                        System.out.println("Unidade cadastrada com sucesso.");
                                    } else {
                                        System.out.println("Não foi possível cadastrar a unidade.");
                                    }
                                }
                            }
                            break;
                        case 2:// EDITAR
                            if (!permissao) {
                                System.out.println("Acesso não autorizado.");
                            } else {
                                System.out.print("Digite o ID da unidade que deseja editar: ");
                                int idUnidade = sc.nextInt();
                                sc.nextLine(); // para consumir a quebra de linha deixada pelo nextInt
                                Unidade unidade = unidadeController.buscarUnidade(idUnidade);

                                if (unidade == null) {
                                    System.out.println("Unidade não encontrada.");
                                } else {
                                    System.out.print("Digite o novo nome da unidade (atual: " + unidade.getNome() + "): ");
                                    String novoNome = sc.nextLine();
                                    System.out.print("Digite a nova cidade da unidade (atual: " + unidade.getCidadeUnidade()
                                            + "): ");
                                    String novaCidade = sc.nextLine();
                                    System.out.print("Digite o novo endereço da unidade (atual: "
                                            + unidade.getEnderecoUnidade() + "): ");
                                    String novoEndereco = sc.nextLine();

                                    boolean atualizado = unidadeController.atualizarUnidade(idUnidade, novoNome, novaCidade,
                                            novoEndereco);
                                    if (atualizado) {
                                        System.out.println("Unidade atualizada com sucesso.");
                                    } else {
                                        System.out.println("Não foi possível atualizar a unidade.");
                                    }
                                }
                            }
                            break;
                        case 3:// BUSCAR
                            System.out.print("Digite o ID da unidade que deseja buscar: ");
                            int idUnidade = sc.nextInt();
                            sc.nextLine(); // para consumir a quebra de linha deixada pelo nextInt
                            Unidade unidade = unidadeController.buscarUnidade(idUnidade);

                            if (unidade == null) {
                                System.out.println("Unidade não encontrada.");
                            } else {
                                System.out.println(unidade);
                            }
                            break;
                        case 4:// EXCLUIR
                            if (!permissao) {
                                System.out.println("Acesso não autorizado.");
                            } else {
                                System.out.print("Digite o ID da unidade que deseja excluir: ");
                                int idUnidade1 = sc.nextInt();
                                sc.nextLine(); // para consumir a quebra de linha deixada pelo nextInt
                                boolean excluido = unidadeController.excluirUnidade(idUnidade1);

                                if (excluido) {
                                    System.out.println("Unidade excluída com sucesso.");
                                } else {
                                    System.out.println("Não foi possível excluir a unidade.");
                                }
                            }

                            break;
                        case 5:// LISTAR
                            List<Unidade> unidades = unidadeController.listarUnidades();

                            if (unidades.isEmpty()) {
                                System.out.println("Não há unidades cadastradas.");
                            } else {
                                System.out.println("Unidades cadastradas:\n");
                                for (Unidade unidadepic : unidades) {
                                    System.out.println(unidadepic);
                                    System.out.println("\n------------------------------\n");
                                }
                            }
                            break;
                        case 0:
                            System.out.println("Saindo do menu de unidades...");
                            sair = true;
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                            break;
                    }
                    exibirMenu();
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Tente novamente.");
                    sc.nextLine(); // para consumir a entrada inválida
                }
            }
        }
    }

}
