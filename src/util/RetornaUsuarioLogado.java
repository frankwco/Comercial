package util;

import org.springframework.security.core.context.SecurityContextHolder;

import banco.DAOGenerico;
import modelo.Funcionario;

public class RetornaUsuarioLogado {
	
	public static Funcionario get() {
		DAOGenerico<Funcionario> dao = 
				new DAOGenerico<>(Funcionario.class);
		Funcionario func = new Funcionario();
		String nomeUsuario=SecurityContextHolder.getContext()
				.getAuthentication().getName();
		func = dao.buscarCondicao("email='"+nomeUsuario+"'")
							.get(0);
		return func;
	}

}
