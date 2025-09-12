package br.cesul.lanchebuilder.model;

import org.bson.types.ObjectId;

import java.text.NumberFormat;
import java.util.Locale;

public class Lanche {
    private ObjectId oid;
    private long pedidoId;
    private String pao;
    private String carne;
    private boolean tomate;
    private boolean alface;
    private double valor;

    private String valorFormatado;

    public Lanche(){}

    public Lanche(Builder builder){
        this.pedidoId = builder.pedidoId;
        this.pao = builder.pao;
        this.carne = builder.carne;
        this.tomate = builder.tomate;
        this.alface = builder.alface;
        setValor(builder.valor);
    }

    public String getPedido() {
        return String.format("Pedido %s: %s, %s, %s, %s",
                pedidoId,
                pao,
                carne,
                tomate ? "Com tomate" : "Sem tomate",
                alface ? "Com alface" : "Sem alface"
        );
    }

    public long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(long id) {
        this.pedidoId = id;
    }

    public ObjectId getOid() {
        return oid;
    }

    public String getPao() {return pao;}

    public double getValor(){return valor;}

    public String getValorFormatado(){return valorFormatado;}

    public void setValor(double valor){
        this.valor = valor;
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        this.valorFormatado = nf.format(valor);
    }

    public void setPao(String pao) {
        this.pao = pao;
    }

    public String getCarne() {
        return carne;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }

    public boolean isTomate() {
        return tomate;
    }

    public void setTomate(boolean tomate) {
        this.tomate = tomate;
    }

    public boolean isAlface() {
        return alface;
    }

    public void setAlface(boolean alface) {
        this.alface = alface;
    }

    // Builder interno
    public static class Builder {
        private String pao;
        private String carne;
        private boolean tomate;
        private boolean alface;
        private double valor;
        private long pedidoId;

        public Builder id(long id){
            this.pedidoId = id;
            return this;
        }
        public Builder comPao(String pao) {
            this.pao = pao;
            return this;
        }

        public Builder comCarne(String carne) {
            this.carne = carne;
            return this;
        }

        public Builder comTomate(boolean tomate) {
            this.tomate = tomate;
            return this;
        }

        public Builder comAlface(boolean alface) {
            this.alface = alface;
            return this;
        }
        public Builder custo(double valor){
            this.valor = valor;
            return this;
        }

        public Lanche build() {
            return new Lanche(this);
        }
    }
}

