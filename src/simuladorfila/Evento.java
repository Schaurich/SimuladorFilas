/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorfila;

/**
 *
 * @author kim
 */
public class Evento {

    private String nome;
    private double tempo;
    private int fila;

    public Evento(String nome, double tempo, int fila) {
        this.nome = nome;
        this.tempo = tempo;
        this.fila = fila;
    }

    public int getFila() {
        return fila;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getTempo() {
        return tempo;
    }

    public void setTempo(double tempo) {
        this.tempo = tempo;
    }

}
