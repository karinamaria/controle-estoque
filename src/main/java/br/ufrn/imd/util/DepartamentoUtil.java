package br.ufrn.imd.util;

import br.ufrn.imd.dao.DepartamentoDAO;
import br.ufrn.imd.exception.NegocioException;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DepartamentoUtil {
	public static DepartamentoDAO dao = new DepartamentoDAO();
	
	public boolean verificarTextFieldDepartamento(TextField campoNome, Label labelErroNome) {
		boolean retorno = true;
		
		try {
			validarNomeDepartamento(campoNome.getText());
		}catch(NegocioException ne) {
			retorno=false;
			labelErroNome.setText(ne.getMessage());
		}catch(NullPointerException e) {
			retorno=false;
		}
		return retorno;
	}
	
	private void validarNomeDepartamento(String nome) throws NegocioException {
		if(dao.findByNome(nome) != null) {
			throw new NegocioException("Departamento já cadastrado");
		}
	}
}
