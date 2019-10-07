/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorfila;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kim
 */
public class Fila {

    int id;
    int tamanho;
    int quantAtual;
    int servidor;
    double entradaMax;
    double entradaMin;
    double saidaMax;
    double saidaMin;
    double[] relatorio;
    double tempoGlobal;
    HashMap<Integer,Double> roteamentoSaida;

    public Fila(int id, int tamanho, int servidor, double entradaMin, double entradaMax, double saidaMin, double saidaMax) {
        this.id = id;
        this.quantAtual = 0;
        this.tamanho = tamanho;
        this.servidor = servidor;
        this.entradaMax = entradaMax;
        this.entradaMin = entradaMin;
        this.saidaMax = saidaMax;
        this.saidaMin = saidaMin;
        this.relatorio = new double[tamanho + 1];
        this.tempoGlobal = 0;
    }

    public int getId() {
        return id;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getQuantAtual() {
        return quantAtual;
    }

    public void setQuantAtual(int quantAtual) {
        this.quantAtual = quantAtual;
    }

    public void chegada(GeradorAleatorio ger, Escalonador esc, Evento evento) {
        double tempoChegada;
        double tempoSaida;
        // atualizaTempoRelatorio(evento.getTempo());

        if (quantAtual < tamanho) {

            quantAtual++;

            if (quantAtual <= servidor) {
                tempoSaida = ger.gerarNumAleatorio(saidaMin, saidaMax) + evento.getTempo();
                Evento saida = new Evento("Saida", tempoSaida, this.id);
                Evento entrada = new Evento("Chegada", tempoSaida, this.id + 1);
                esc.addEvento(saida);
                esc.addEvento(entrada);
            }

        }

        tempoChegada = ger.gerarNumAleatorio(entradaMin, entradaMax) + tempoGlobal;
        Evento chegada = new Evento("Chegada", tempoChegada, id);
        esc.addEvento(chegada);

    }

    public void saida(GeradorAleatorio ger, Escalonador esc, Evento evento) {

        quantAtual--;

        if (quantAtual >= servidor) {
            double tempo = ger.gerarNumAleatorio(saidaMin, saidaMax) + tempoGlobal;
            Evento saida = new Evento("Saida", tempo, id);
            Evento entrada = new Evento("Chegada", tempo, id + 1);
            //System.out.println("Entrada fila: "+id);
            esc.addEvento(saida);
            esc.addEvento(entrada);
        }

    }

    public void imprimeRelatorio() {
        System.out.println("Relatório da fila " + id + " " + ":");
        System.out.println("Tempo global: " + tempoGlobal);
        for (int i = 0; i < relatorio.length; i++) {
            System.out.println("Tempo com " + i + " pessoas na fila: " + relatorio[i]);
        }
        System.out.println("");
    }

    public void atualizaTempoRelatorio(double proximo) {
        double total = proximo - tempoGlobal;
        // System.out.println("tempo Proximo :"+proximo+", tempo global: "+ tempoGlobal);
        relatorio[quantAtual] += total;
    }
}
