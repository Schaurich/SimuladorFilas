/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorfila;

import java.util.ArrayList;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author kim
 */
public class Escalonador {

    private CopyOnWriteArrayList<Evento> lista;

    public Escalonador() {
        this.lista = new CopyOnWriteArrayList();
    }

    public void addEvento(Evento evento) {
        lista.add(evento);
    }

    public void removeEvento(Evento evento) {

        for (Evento event : lista) {
            //  System.out.println(event.getNome() + event.getTempo());
            if (event.getNome() == evento.getNome()) {
                if (event.getTempo() == evento.getTempo()) {
                    lista.remove(event);
                }
            }
        }
    }

    public CopyOnWriteArrayList<Evento> getLista() {
        return lista;
    }

    public Evento proximoEvento(ArrayList<Fila> filas) {
        Evento proximo = null;
        for (Evento evento : lista) {
            if (proximo == null) {
                proximo = evento;
            } else {

                if (evento.getTempo() < proximo.getTempo()) {
                    proximo = evento;
                }
            }
            for (Fila fila : filas) {
                if (fila.id == evento.getFila()) {
                    fila.atualizaTempoRelatorio(proximo.getTempo());
                    fila.tempoGlobal = proximo.getTempo();
                     }
            }
        }
        for (Evento event : lista) {
            if (event == proximo) {
                lista.remove(event);
            }
        }
        //System.out.println("Nome: "+proximo.getNome()+",Fila: "+proximo.getFila()+", Tempo: "+proximo.getTempo());
        return proximo;
    }

}
