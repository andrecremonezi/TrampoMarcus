package com.projects.com.br.myapplication.Model.Entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by c1284528 on 14/10/2015.
 */
public class Despesa implements Parcelable{
    private Long id;
    private String descricao;
    private Double valor;
    private String tipo;


    public Despesa(){
        super();
    }

    protected Despesa(Parcel imp) {
        super();
        readFromParcel(imp);
    }

    public static final Creator<Despesa> CREATOR = new Creator<Despesa>() {
        @Override
        public Despesa createFromParcel(Parcel in) {
            return new Despesa(in);
        }

        @Override
        public Despesa[] newArray(int size) {
            return new Despesa[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Despesa despesa = (Despesa) o;

        if (id != null ? !id.equals(despesa.id) : despesa.id != null) return false;
        if (descricao != null ? !descricao.equals(despesa.descricao) : despesa.descricao != null)
            return false;
        if (valor != null ? !valor.equals(despesa.valor) : despesa.valor != null) return false;
        return !(tipo != null ? !tipo.equals(despesa.tipo) : despesa.tipo != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (valor != null ? valor.hashCode() : 0);
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Despesa{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(descricao);
        dest.writeDouble(valor);
        dest.writeString(tipo);
    }


    private void readFromParcel(Parcel imp) {
        id           = imp.readLong();
        id           = id == -1 ? null : id;
        descricao  = imp.readString();
        valor        = imp.readDouble();
        tipo         = imp.readString();
    }


}
