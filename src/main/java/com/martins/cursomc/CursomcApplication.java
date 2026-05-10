package com.martins.cursomc;

import com.martins.cursomc.domain.*;
import com.martins.cursomc.domain.enums.TipoCliente;
import com.martins.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria(null,"Informática");
        Categoria cat2 = new Categoria(null,"Escritório");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

        Estado est1 = new Estado("SP", "São Paulo");
        Estado est2 = new Estado("RJ", "Rio de Janeiro");

        Cidade c1 = new Cidade(null, "Campinas", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est1);
        Cidade c3 = new Cidade(null,"Rio de Janeiro", est2);

        est1.getCidades().addAll((Arrays.asList(c1, c2)));
        est2.getCidades().addAll((Arrays.asList(c3)));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

        Cliente cli1 = new Cliente(null, "Maria de Souza", "maria@gmeil.com", "72435345353", TipoCliente.PESSOA_FISICA);
        cli1.getTelefones().addAll(Arrays.asList("2254-7892", "3219-9037"));

        Endereco e1 = new Endereco(null, "Correia Dutra", "103", "apto 801", "Catete", "20000-090", cli1, c1);
        Endereco e2 = new Endereco(null, "Barão de La Torre", "147", "sala 819", "Centro", "32897-100", cli1, c2);
        Endereco e3 = new Endereco(null, "Rua Assunção", "77", "sala 1817", "Botafogo", "28090-000", cli1, c3);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2, e3));

        clienteRepository.saveAll(Arrays.asList(cli1));
        enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));
    }
}
