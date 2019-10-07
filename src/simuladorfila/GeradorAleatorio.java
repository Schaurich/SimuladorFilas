/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorfila;

import static java.lang.Math.random;
import java.text.DecimalFormat;

/**
 *
 * @author kim
 */
public class GeradorAleatorio {

    private double finalProg;
    private int mod;
    private int multiplicador;
    private int c;
    // private int qntNum;
    private double ultimoNum;

    public GeradorAleatorio() {
        this.mod = 65498; // mod = M
        this.multiplicador = 7; // multiplicador = A
        // this.qntNum = qntNum; // quantidade de numeros gerados
        this.c = 7;
        this.ultimoNum = 0;

    }

    public double gerarNumAleatorio(double min, double max) {
        if (ultimoNum == 0) {
            ultimoNum = geradorSeed();
        }
        double num = (((multiplicador * ultimoNum)) + c) % mod;
        ultimoNum = num;
        num = num / mod;
        double teste = (max - min) * num + min;
        finalProg++;
        return teste;
    }

    public double getFinalProg() {
        return finalProg;
    }

    public int geradorSeed() {
        long n = System.currentTimeMillis();
        char[] digitos = String.valueOf(n).toCharArray();
        char a = digitos[digitos.length - 1];
        char b = digitos[digitos.length - 2];
        char c = digitos[digitos.length - 3];

        StringBuilder sb = new StringBuilder();
        sb.append(c);
        sb.append(b);
        sb.append(a);
        String seed = sb.toString();
        int seedFinal = Integer.parseInt(seed);
        return seedFinal;
    }

}

/* DecimalFormat df = new DecimalFormat();
 df.applyPattern("#,##0.0000");
 System.out.println(df.format(teste));*/
