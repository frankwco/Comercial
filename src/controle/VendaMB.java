package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import banco.DAOGenerico;
import modelo.ItensVenda;
import modelo.Produto;
import modelo.Venda;

@ManagedBean
@ViewScoped
public class VendaMB {
	private Venda venda = new Venda();
	private ItensVenda itensVenda = new ItensVenda();
	private List<ItensVenda> listaItensVenda = new ArrayList<>();
	private List<Venda> listaVendas = new ArrayList<>();
	private DAOGenerico<Venda> daoVenda = new DAOGenerico<>(Venda.class);
	private DAOGenerico<ItensVenda> daoItensVenda = new DAOGenerico<>(ItensVenda.class);

	public void adicionarItem() {
		if (itensVenda.getProduto() != null) {
			itensVenda.setValorUnitario(itensVenda.getProduto().getValorVenda());
			itensVenda.setValotTotal(itensVenda.getQuantidade() * itensVenda.getValorUnitario());
			listaItensVenda.add(itensVenda);
			itensVenda = new ItensVenda();
		}
	}

	public void finalizarVenda() {
		daoVenda.salvar(venda);
		for (ItensVenda it : listaItensVenda) {
			it.setVenda(venda);
			daoItensVenda.salvar(it);
		}
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Venda Realizada Com Sucesso!!", ""));
	}

	public void removerItem(ItensVenda itemRemover) {
		listaItensVenda.remove(itemRemover);
	}

	public void novaVenda() {
		venda = new Venda();
		listaItensVenda = new ArrayList<>();
		itensVenda = new ItensVenda();
	}

}
