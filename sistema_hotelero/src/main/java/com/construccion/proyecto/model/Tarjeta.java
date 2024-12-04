package com.construccion.proyecto.model;
import java.util.Date;
/**
 * Clase que representa una tarjeta de pago, con atributos como el nombre del titular,
 * el número de tarjeta, el NIP, la fecha de vencimiento y el saldo disponible.
 * Incluye métodos para depositar y retirar dinero de la tarjeta.
 */

public class Tarjeta {
    private int idTarjeta;
    private String nombreTitular;
    private String numeroTarjeta;
    private String nip;
    private Date vencimiento;
    private double saldo;
    /**
     * Constructor que inicializa los atributos de la tarjeta.
     *
     * @param idTarjeta El identificador único de la tarjeta.
     * @param nombreTitular El nombre del titular de la tarjeta.
     * @param numeroTarjeta El número de la tarjeta.
     * @param nip El NIP de la tarjeta.
     * @param vencimiento La fecha de vencimiento de la tarjeta.
     * @param saldo El saldo disponible en la tarjeta.
     */

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
    /**
     * Realiza un depósito a la tarjeta, sumando el monto al saldo actual. Si el monto es positivo,
     * se realiza el depósito; si no, se muestra un mensaje de error.
     *
     * @param monto El monto a depositar en la tarjeta.
     */

    public void depositar(double monto) {
        if (monto > 0) {
            this.saldo += monto;
            System.out.println("Depósito realizado exitosamente. Nuevo saldo: " + saldo);
        } else {
            System.out.println("El monto a depositar debe ser positivo.");
        }
    }
    /**
     * Realiza un retiro de dinero de la tarjeta. Si el monto es menor o igual al saldo disponible,
     * se realiza el retiro; si no, se muestra un mensaje de error indicando saldo insuficiente.
     *
     * @param monto El monto a retirar de la tarjeta.
     */

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
