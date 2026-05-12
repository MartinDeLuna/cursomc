package com.martins.cursomc.domain.enums;

import jakarta.persistence.Enumerated;

public enum SituacaoPagamento {
    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private int codigo;
    private String descricao;

    private SituacaoPagamento(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static SituacaoPagamento toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (SituacaoPagamento situacaoPagamento : SituacaoPagamento.values()) {
            if (cod.equals(situacaoPagamento.getCodigo())) {
                return situacaoPagamento;
            }
        }
        throw new IllegalArgumentException("Situação de Pagamento inválida (" + cod + ")");
    }
}