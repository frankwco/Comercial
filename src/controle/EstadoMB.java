package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import banco.DAOGenerico;
import modelo.Estado;

@ManagedBean
@ViewScoped
public class EstadoMB {
	private Estado estado = new Estado();
	private List<Estado> estados = new ArrayList<>();
	private DAOGenerico<Estado> dao = new DAOGenerico<>(Estado.class);
	
	public EstadoMB(){
		estados = dao.buscarTodos();
	}

	public void inserir() {
		if (estado.getId() == null) {
			dao.salvar(estado);
		} else {
			dao.alterar(estado);
		}
		estado = new Estado();
		estados = dao.buscarTodos();
	}
	
	public void excluir(Long id){
		dao.excluir(id);
		estados = dao.buscarTodos();
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

}
