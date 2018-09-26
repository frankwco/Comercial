package controle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import modelo.Funcionario;
import util.RetornaUsuarioLogado;

@ManagedBean
@SessionScoped
public class PrincipalMB {
	Funcionario funcionario;
	
	public PrincipalMB() {
		funcionario = RetornaUsuarioLogado.get();
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
}



