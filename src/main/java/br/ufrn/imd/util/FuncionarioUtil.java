package br.ufrn.imd.util;

import java.util.InputMismatchException;
import java.util.Objects;

import br.ufrn.imd.dao.FuncionarioDAO;
import br.ufrn.imd.exception.NegocioException;
import br.ufrn.imd.modelo.Funcionario;

/**
 * Classe de validações da entidade Funcionário
 * 
 * @author Karina e Maria Eduarda
 *
 */
public class FuncionarioUtil {
	private static FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

	public void validarFuncionario(Funcionario funcionario) throws NegocioException {
		validarPreenchimentoCamposFuncionario(funcionario);
		validarLoginUsuario(funcionario);

	}
	/**
	 * Validar se login já existe no banco de dados
	 * @param f funcionário que terá o login validado
	 * @throws NegocioException
	 */
	public void validarLoginUsuario(Funcionario f) throws NegocioException {
		// validar login de usuário
		Funcionario aux = funcionarioDAO.buscarFuncionarioPorLogin(f.getUsuario().getLogin());
		if (Objects.nonNull(aux)) {
			if (!f.getCpf().equals(aux.getCpf())) {
				throw new NegocioException("Login já pertece a outro usuário");
			}
		}
	}
	
	/**
	 * Validação geral do campos da entidade funcionário
	 * @param funcionario que terá seus campos validados
	 * @throws NegocioException
	 */
	public void validarPreenchimentoCamposFuncionario(Funcionario funcionario) throws NegocioException {
		if (!validarCPFFuncionario(funcionario.getCpf())) {
			throw new NegocioException("CPF inválido");
		}
		if(funcionario.getSalario() < 0) {
			throw new NegocioException("Sálario inválido");
		}
	}

	/**
	 * Verificar se CPF informado é válido
	 * 
	 * @param cpf informado pelo funcionário
	 * @return variável booleana que indica se o CPF é válido ou não
	 */
	public boolean validarCPFFuncionario(String cpf) {
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
				|| cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
				|| cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
				|| cpf.equals("99999999999") || (cpf.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); // converte no respectivo caractere numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}
	
	/**
	 * Imprime o cpf com pontos e traços
	 * @param CPF a ser impresso
	 * @return String que representa o cpf com pontos e traços
	 */
	public static String imprimeCPF(String CPF) {
		return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." + CPF.substring(6, 9) + "-"
				+ CPF.substring(9, 11));
	}

}
