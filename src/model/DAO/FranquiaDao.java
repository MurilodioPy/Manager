package model.DAO;

import java.util.Arrays;
import java.util.Date;

import model.Franquia;
import model.Pessoa;

public class FranquiaDao {

	private static Franquia[] franquias = new Franquia[100];
	private static int count = 0;

	public boolean cadastrarFranquia(int id, String nome, String cnpj, String cidade, String endereco, Pessoa responsavelId) {

	    if (count >= franquias.length) {
	        franquias = Arrays.copyOf(franquias, franquias.length + 100);
	    }
	    Date dataCriacao = new Date();
	    Franquia novaFranquia = new Franquia(nome, cnpj, cidade, endereco, responsavelId, dataCriacao, dataCriacao);
	    franquias[count] = novaFranquia;
	    count++;
	    return true;
	}

    public boolean editarFranquia(int id, String cnpj, String novoNome, String novaCidade, String novoEndereco) {
        Franquia franquia = buscarFranquia(id);
        if (franquia == null) {
            return false; // Franquia não encontrada
        }
        franquia.setCnpj(cnpj);
        franquia.setNome(novoNome);
        franquia.setCidade(novaCidade);
        franquia.setEndereco(novoEndereco);
        franquia.setDataModificacao(new Date());

        for (int i = 0; i < FranquiaDao.count; i++) {
            if (FranquiaDao.franquias[i].getId() == id) {
                FranquiaDao.franquias[i] = franquia;
                return true;
            }
        }
        return true;
    }

    public boolean excluirFranquia(int idRemove) {
        for (int i = 0; i < FranquiaDao.count; i++) {
            if (FranquiaDao.franquias[i].getId() == idRemove) {
                FranquiaDao.franquias[i] = null;
                for (int j = i; j < FranquiaDao.count - 1; j++) {
                    FranquiaDao.franquias[j] = FranquiaDao.franquias[j + 1];
                }
                FranquiaDao.count--;
                return true;
            }
        }
        return false;
    }

    public Franquia buscarFranquia(int id) {
        for (int i = 0; i < FranquiaDao.count; i++) {
            if (FranquiaDao.franquias[i].getId() == id) {
                return FranquiaDao.franquias[i];
            }
        }
        return null;
    }
    
    public Franquia[] listarFranquias() {
        Franquia[] listaFranquias = new Franquia[FranquiaDao.count];
        int count = 0;
        for (int i = 0; i < FranquiaDao.franquias.length; i++) {
            Franquia franquia = FranquiaDao.franquias[i];
            if (franquia != null) {
                listaFranquias[count] = franquia;
                count++;
            }
        }
        return Arrays.copyOf(listaFranquias, count);
    }

}
