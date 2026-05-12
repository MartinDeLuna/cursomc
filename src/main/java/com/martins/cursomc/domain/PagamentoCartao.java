package com.martins.cursomc.domain;

import com.martins.cursomc.domain.enums.SituacaoPagamento;
import jakarta.persistence.Entity;

@Entity
public class PagamentoCartao extends Pagamento{
    private Integer quantidadeParcelas;

    public PagamentoCartao(){
    }

    public PagamentoCartao(Integer id, SituacaoPagamento situacaoPagamento, Pedido pedido, Integer quantidadeParcelas) {
        super(id, situacaoPagamento, pedido);
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public Integer getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(Integer quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }
}
