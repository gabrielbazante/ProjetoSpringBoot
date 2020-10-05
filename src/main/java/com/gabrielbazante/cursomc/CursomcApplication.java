package com.gabrielbazante.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gabrielbazante.cursomc.domain.Categoria;
import com.gabrielbazante.cursomc.domain.Cidade;
import com.gabrielbazante.cursomc.domain.Cliente;
import com.gabrielbazante.cursomc.domain.Endereco;
import com.gabrielbazante.cursomc.domain.Estado;
import com.gabrielbazante.cursomc.domain.Produto;
import com.gabrielbazante.cursomc.domain.enums.TipoCliente;
import com.gabrielbazante.cursomc.repositories.CategoriaRepository;
import com.gabrielbazante.cursomc.repositories.CidadeRepository;
import com.gabrielbazante.cursomc.repositories.ClienteRepository;
import com.gabrielbazante.cursomc.repositories.EnderecoRepository;
import com.gabrielbazante.cursomc.repositories.EstadoRepository;
import com.gabrielbazante.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto (null, "Computador", 2000.00);
		Produto p2 = new Produto (null, "Impressora", 800.00);
		Produto p3 = new Produto (null, "Mouse", 80.00);
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Pernambuco");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Recife", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Olinda", est1 );
		
		est1.getCidades().addAll(Arrays.asList(c1, c3));
		est2.getCidades().addAll(Arrays.asList(c2));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Gabriel Bazante", "gabrielbazante7@gmail.com", "000.000.000-00", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("+55 81 9-9784-4512", "+55 81 9-9999-9999"));
		
		Endereco e1 = new Endereco(null, "Rua Barauna", "46", "Casa", "Alto do Mandu", "52071-030", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Doutor Vicente Meira", "82", "Apto 1303", "Espinheiro", "52020-130", cli1, c1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		
	}

}
