package Model;

import java.time.LocalDate;

public class Pagamento {
private int id, idMemplan, percentualDesconto;
private double valor, valorPago;
private boolean statusPagamento;
private String mes;
private LocalDate dataPagamento;

public Pagamento(){}

public Pagamento(int id, int idMemplan, int percentualDesconto, double valor, double valorPago, boolean statusPagamento, String mes, LocalDate dataPagamento){
    this.id = id;
    this.idMemplan = idMemplan;
    this.percentualDesconto = percentualDesconto;
    this.valor = valor;
    this.valorPago = valorPago;
    this.statusPagamento = statusPagamento;
    this.mes = mes;
    this.dataPagamento = dataPagamento;
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMemplan() {
        return idMemplan;
    }

    public void setIdMemplan(int idMemplan) {
        this.idMemplan = idMemplan;
    }

    public int getPercentualDesconto() {
        return percentualDesconto;
    }

    public void setPercentualDesconto(int percentualDesconto) {
        this.percentualDesconto = percentualDesconto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public boolean isStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(boolean statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }




}
