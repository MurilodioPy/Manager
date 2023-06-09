package model;

import java.time.LocalDateTime;
import java.util.Random;

public class Medico extends Pessoa {

    private int crm;
    private String especialidade;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    private Pessoa pessoa;

    public Medico(int id, String nome, String endereco, String cpf, String telefone, String login, String senha,
            int crm, LocalDateTime dataCriacao, LocalDateTime dataModificacao, String especialidade) {
        super(nome, endereco, cpf, telefone, login, senha, especialidade, dataCriacao, dataModificacao);
        this.crm = crm;
        this.especialidade = especialidade;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }

    public Medico(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Medico() {
    }

    public int getCrm() {
        return crm;
    }

    public void setCrm(int novoCrm) {
        this.crm = novoCrm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public static Medico gerarMedicoAleatorio() {
        String[] especialidades = {"Cardiologia", "Neurologia", "Ortopedia", "Pediatria", "Dermatologia"};
        String[] nomes = {"Ana", "Maria", "João", "Pedro", "Luiza", "Gabriel", "Lucas", "Juliana", "Renata", "Thiago"};
        String[] sobrenomes = {"Silva", "Souza", "Fernandes", "Almeida", "Costa", "Pereira", "Santos", "Lima", "Mendes", "Nascimento"};
        String[] logins = {"ana.silva", "joao.pereira", "lucia.costa", "renata.mendes", "thiago.nascimento"};
        String[] senhas = {"123456", "senha123", "senha1234", "senha12345", "senha123456"};

        Random random = new Random();

        String nome = String.format("%s %s", nomes[random.nextInt(nomes.length)], sobrenomes[random.nextInt(sobrenomes.length)]);
        String endereco = String.format("Rua %d, Bairro %d", random.nextInt(9999) + 1, random.nextInt(99) + 1);
        String cpf = String.format("%03d.%03d.%03d-%02d", random.nextInt(1000), random.nextInt(1000), random.nextInt(1000), random.nextInt(100));
        String telefone = String.format("(%02d) %05d-%04d", random.nextInt(99) + 1, random.nextInt(99999) + 1, random.nextInt(9999) + 1);
        String login = String.format("%s%d", logins[random.nextInt(logins.length)], random.nextInt(99) + 1);
        String senha = senhas[random.nextInt(senhas.length)];
        String especialidade = String.format("%s", especialidades[random.nextInt(especialidades.length)]);
        int crm = random.nextInt(999999);

        LocalDateTime dataCriacao = LocalDateTime.now();
        LocalDateTime dataModificacao = LocalDateTime.now();

        Medico medico = new Medico(0, nome, endereco, cpf, telefone, login, senha, crm, dataModificacao, dataCriacao, especialidade);

        return medico;
    }

    @Override
    public String toString() {
        return "\n Nome: " + getNome()
                + "\n ID: " + getId()
                + "\n Endereco: " + getEndereco()
                + "\n CPF: " + getCpf()
                + "\n Telefone : " + getTelefone()
                + "\n Login: " + getLogin()
                + "\n Senha: " + getSenha()
                + "\n Tipo de usuário: " + "Médico"
                + "\n CRM: " + getCrm()
                + "\n Especialidade: " + getEspecialidade()
                + "\n Data de Criação: " + dataCriacao
                + "\n Data de modificação: " + dataModificacao
                + "\n ------------------------------------------------";
    }
}
