package br.ufrn.imd.dao;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ufrn.imd.modelo.Departamento;
import br.ufrn.imd.modelo.Endereco;
import br.ufrn.imd.modelo.Funcionario;
import br.ufrn.imd.modelo.Papel;
import br.ufrn.imd.modelo.Usuario;
import br.ufrn.imd.util.UsuarioUtil;

public class teste{

	public static void main(String[] args) {
		Departamento d = new Departamento();
		d.setNome("Manutenção");
		
		Funcionario f = new Funcionario();
		f.setNome("Ana");
		f.setEmail("ana@email.com");
		f.setDataNascimento(new Date());
		f.setTelefone("dmsdms");
		
		Endereco e = new Endereco();
		e.setRua("Rua asas");
		e.setBairro("sdsd");
		e.setCidade("ss cc");
		e.setCep("Cep 5959");
		e.setPais("Brasil");
		e.setNumero(50);
		e.setComplemento("ssds");
		
		UsuarioUtil usuario = new UsuarioUtil();
		Usuario u = new Usuario();
		u.setLogin("ana");
		try {
			u.setSenha(usuario.criptografarSenha("teste"));
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		u.setPermissao(Papel.GERENTE);
		
		f.setEndereco(e);
		
		f.setUsuario(u);
		
		List<Funcionario> fs = new ArrayList<Funcionario>();
		fs.add(f);
		
		d.setFuncionarios(fs);
		
		f.setDepartamento(d);
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.salvar(f);

		
	}

}