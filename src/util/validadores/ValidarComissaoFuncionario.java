package util.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validarComissaoFuncionario")
public class ValidarComissaoFuncionario implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object com) throws ValidatorException {
		Double comissao = (Double) com;
		if(comissao>10) {
			FacesMessage msg = 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"", "Comissão Maior que 10!!");			
			throw new ValidatorException(msg);
		}
	}

}
