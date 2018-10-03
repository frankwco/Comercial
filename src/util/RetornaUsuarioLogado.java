package util;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import banco.DAOGenerico;
import modelo.Funcionario;

public class RetornaUsuarioLogado {

	public static Funcionario get() {
		DAOGenerico<Funcionario> dao = new DAOGenerico<>(Funcionario.class);
		Funcionario func = new Funcionario();
		String nomeUsuario="";
		
		Authentication authentication = (Authentication) 
				SecurityContextHolder.getContext().getAuthentication(); 
		if(authentication != null){
			Object obj = authentication.getPrincipal();
			if (obj instanceof UserDetails) {
				nomeUsuario = ((UserDetails)obj).getUsername();
			} else {
				nomeUsuario = obj.toString();
			}		
		}
		
	List<Funcionario> lf = dao.buscarCondicao("email='" + nomeUsuario + "'");
		if(lf.size()>0) {
			func = lf.get(0);
		}
		return func;
	}

}
