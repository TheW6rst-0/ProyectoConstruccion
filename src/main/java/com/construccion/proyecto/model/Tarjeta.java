package com.construccion.proyecto.model;
import java.util.Date;
public class Tarjeta {
    private int idTarjeta;
    private String nombreTitular;
    private String numeroTarjeta;
    private String nip;
    private Date vencimiento;
    private double saldo;

    public Tarjeta(int idTarjeta, String nombreTitular, String numeroTarjeta, String nip, Date vencimiento, double saldo) {
        this.idTarjeta = idTarjeta;
        this.nombreTitular = nombreTitular;
        this.numeroTarjeta = numeroTarjeta;
        this.nip = nip;
        this.vencimiento = vencimiento;
        this.saldo = saldo;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void depositar(double monto) {
        if (monto > 0) {
            this.saldo += monto;
            System.out.println("Depósito realizado exitosamente. Nuevo saldo: " + saldo);
        } else {
            System.out.println("El monto a depositar debe ser positivo.");
        }
    }

    public void retirar(double monto) {
        if (monto > 0 && monto <= this.saldo) {
            this.saldo -= monto;
            System.out.println("Retiro realizado exitosamente. Nuevo saldo: " + saldo);
        } else if (monto > this.saldo) {
            System.out.println("Saldo insuficiente para realizar el retiro.");
        } else {
            System.out.println("El monto a retirar debe ser positivo.");
        }
    }
}
