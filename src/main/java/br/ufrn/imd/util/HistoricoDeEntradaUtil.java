package br.ufrn.imd.util;

import java.util.Random;

import br.ufrn.imd.dao.HistoricoEntradaDAO;

/**
 * Classe de validações da entidade HistoricoDeEntrada
 * 
 * @author Karina e Maria Eduarda
 *
 */
public class HistoricoDeEntradaUtil {
	private static HistoricoEntradaDAO historicoEntradaDAO = new HistoricoEntradaDAO();
	
	public static int gerarNumero() {
		Random gerador = new Random();
		int gerarNumero = 0;
		
		do {
			gerarNumero = gerador.nextInt();
		}while(historicoEntradaDAO.FindByNumero(gerarNumero) != null && gerarNumero < 0);
		
		return gerarNumero;
	}
}
