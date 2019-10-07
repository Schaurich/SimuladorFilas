/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IndiceDesempenho;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import simuladorfila.Evento;

/**
 *
 * @author kim
 */
public class Analitico {

    private int servidor;
    private int limiteFila;
    private double turno;
    private double cliente;
    private double processamento;
    private double lambida;
    private double mu;

    private double[] proporcao;
    // private List[] proporcao;
    private double[] probabilidade;
    private double[] populacao;
    private double[] vazao;
    private double[] utilizacao;
    private double[] tempoResposta;

    public Analitico(int servidor, int limiteFila, double turno, double cliente, double processamento) {
        this.cliente = cliente;
        this.limiteFila = limiteFila;
        this.servidor = servidor;
        this.turno = turno;
        this.processamento = processamento;
        this.lambida = cliente / turno;
        this.mu = processamento / turno;
        probabilidade = new double[limiteFila + 1];
        populacao = new double[limiteFila + 1];
        vazao = new double[limiteFila + 1];
        utilizacao = new double[limiteFila + 1];
        tempoResposta = new double[limiteFila + 1];
        proporcao = new double[limiteFila + 1];
        calcProporcao();
        calcProbabilidade();
        calcPopulacao();
        calcVazao();
        calcUtilizacao();
        calcTempoResposta();
    }

    private void calcProporcao() {
       // System.out.println("Proporção: ");
        double prop = 1;
        proporcao[0] = 1;
        for (int i = 1; i <= limiteFila; i++) {

            proporcao[i] = prop * (lambida / (mu * i));
            prop = prop * (lambida / (mu * i));
            // System.out.println(i + ") " + proporcao[i]);
        }
    }

    private void calcProbabilidade() {
       // System.out.println("Probabilidade: ");
        double total = 0;
        for (Double d : proporcao) {
            total += d;
        }
        for (int i = 0; i <= limiteFila; i++) {
            probabilidade[i] = proporcao[i] / total;
            // System.out.printf(i + ") " + probabilidade[i]*100+"\n");
        }
    }

    private void calcPopulacao() {
      //  System.out.println("População: ");
        for (int i = 0; i <= limiteFila; i++) {
            populacao[i] = probabilidade[i] * i;
            // System.out.println(i + ") " + populacao[i]);
        }
    }

    private void calcVazao() {
       // System.out.println("Vazão: ");
        for (int i = 0; i <= limiteFila; i++) {
            vazao[i] = populacao[i] * mu;
            // System.out.println(i + ") " + vazao[i]);
        }

    }

    private void calcUtilizacao() {
       // System.out.println("Utilização: ");
        for (int i = 0; i <= limiteFila; i++) {
            utilizacao[i] = (probabilidade[i]) * ((double) i / limiteFila);
            // System.out.println(i + ") " + utilizacao[i]);
        }
    }

    private void calcTempoResposta() {
        //System.out.println("Tempo de Resposta: ");
        for (int i = 0; i <= limiteFila; i++) {
            tempoResposta[i] = populacao[i] / vazao[i];
            //  System.out.println(i + ") " + tempoResposta[i]);
        }
    }

    public void resultados() {
        double popMedia = 0;
        double utilizacaoSistema = 0;
        double perda = lambida * proporcao[limiteFila];
        for (double b : utilizacao) {
            utilizacaoSistema += b;
        }
        for (double b : populacao) {
            popMedia += b;
        }
        
        System.out.println("População média: "+popMedia);
        System.out.println("Utilização do sistema: "+utilizacaoSistema*100);
        System.out.println("Perda de clientes por hora: "+perda+"\n\n\n\n");

    }
}
