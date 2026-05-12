package com.martins.cursomc;

import com.martins.cursomc.domain.*;
import com.martins.cursomc.domain.enums.SituacaoPagamento;
import com.martins.cursomc.domain.enums.TipoCliente;
import com.martins.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

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
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

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
//
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Pedido ped1 = new Pedido(null, sdf.parse("01/01/2025 10:30"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("07/07/2025 21:30"), cli1, e3);

        Pagamento pagto1 = new PagamentoCartao(null, SituacaoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoBoleto(null, SituacaoPagamento.PENDENTE, ped2, sdf.parse("20/10/2026 00:00"), null);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped1));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
//
        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1, 2000.0);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.0, 2, 80.0);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.0, 1, 800.0);

        ped1.getItens().addAll((Arrays.asList(ip1, ip2)));
        ped2.getItens().addAll((Arrays.asList(ip3)));

        p1.getItens().addAll((Arrays.asList(ip1)));
        p2.getItens().addAll((Arrays.asList(ip3)));
        p3.getItens().addAll((Arrays.asList(ip2)));

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
    }
}
