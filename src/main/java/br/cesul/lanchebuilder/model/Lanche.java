package br.cesul.lanchebuilder.model;

import org.bson.types.ObjectId;

public class Lanche {
    private ObjectId oid;
    private String pao;
    private String carne;
    private boolean tomate;
    private boolean alface;

    public Lanche(String pao, String carne, boolean tomate, boolean alface){
        this.pao = pao;
        this.carne = carne;
        this.tomate = tomate;
        this.alface = alface;
    }

    @Override
    public String toString() {
        return "Hambúrguer: " + pao + ", Carne: " + carne +
                (tomate ? ", com tomate" : ", sem tomate") +
                (alface ? ", com alface." : ", sem alface.");
    }

    public ObjectId getOid() {
        return oid;
    }

    public String getPao() {
        return pao;
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

        public Lanche build() {
            return new Lanche(pao, carne, tomate, alface);
        }
    }
}

