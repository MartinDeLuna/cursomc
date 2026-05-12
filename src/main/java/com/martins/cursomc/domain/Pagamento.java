package com.martins.cursomc.domain;

import com.martins.cursomc.domain.enums.SituacaoPagamento;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "PAGAMENTO")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {

    @Id
    private Integer id;
    private Integer situacaoPagamento;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId
    private Pedido pedido;

    public Pagamento() {
    }

    public Pagamento(Integer id, SituacaoPagamento situacaoPagamento, Pedido pedido) {
        this.id = id;
        this.situacaoPagamento = situacaoPagamento.getCodigo();
        this.pedido = pedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SituacaoPagamento getSituacaoPagamento() {
        return SituacaoPagamento.toEnum(situacaoPagamento);
    }

    public void setSituacaoPagamento(SituacaoPagamento situacaoPagamento) {
        this.situacaoPagamento = situacaoPagamento.getCodigo();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
