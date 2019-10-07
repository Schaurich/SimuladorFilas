/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorfila;

import IndiceDesempenho.Analitico;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author kim
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        menu(s);
    }

    public static void menu(Scanner s) {
        int opcao = 0;
        do {
            System.out
                    .println("\n\n### Sistema de Simulação e metodos analiticos ###");
            System.out.println("\n                  =========================");
            System.out.println("                  |     1 - Simulação     |");
            System.out.println("                  |     2 - Anaitico      |");
            System.out.println("                  |     0 - Sair          |");
            System.out.println("                  =========================\n");

            opcao = s.nextInt();
            System.out.print("\n");
            switch (opcao) {
                case 1:
                    simulacao(s);
                    break;
                case 2:
                    analitico(s);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    public static void analitico(Scanner s) {
        int servidor;
        int limiteFila;
        double turno;
        double cliente;
        double processamento;

        System.out.println("Quantos servidores?");
        servidor = s.nextInt();
        System.out.println("Qual o limite da fila?");
        limiteFila = s.nextInt();
        System.out.println("Qual o turno de atendimento?");
        turno = s.nextDouble();
        System.out.println("Qual a quantidade de clientes atendidos?");
        cliente = s.nextDouble();
        System.out.println("Quantos clientes são atendido por turno?");
        processamento = s.nextDouble();

        Analitico an = new Analitico(servidor, limiteFila, turno, cliente, processamento);// servidor, limiteFila, turno, cliente e processamento
        an.resultados();
    }

    public static void simulacao(Scanner s) {
        int numGerados = 100000;
        int filasAdicionadas = 0;
        int qntFilas = 0;
        double inicio = 2;
        GeradorAleatorio ger = new GeradorAleatorio();
        Escalonador esc = new Escalonador();
        System.out.println("Quantas filas vão ter?");
        qntFilas = s.nextInt();
        ArrayList<Fila> filas = new ArrayList();

        while (qntFilas > filas.size()) {
            filas.add(addFilas(s, filasAdicionadas));
            filasAdicionadas++;
        }
        Evento chegada = new Evento("Chegada", inicio, 0);
        esc.addEvento(chegada);

        while (ger.getFinalProg() < numGerados) {

            Evento atual = esc.proximoEvento(filas);
            for (Fila fila : filas) {
                if (fila.id == atual.getFila()) {
                    if (atual.getNome().equalsIgnoreCase("Saida")) {

                        fila.saida(ger, esc, atual);
                        //System.out.println("fila.saida :"+fila.getId());

                    } else {
                        if (atual.getNome().equalsIgnoreCase("Chegada")) {

                            fila.chegada(ger, esc, atual);
                            //System.out.println("fila.chegada :"+fila.getId());
                        } else {
                            System.out.println("fudeu");
                            System.out.println(atual.getNome());
                        }
                    }
                }
            }
        }
        for (Fila fila : filas) {
            fila.imprimeRelatorio();
        }
    }

    public static Fila addFilas(Scanner s, int filas) {

        int servidores = 1;
        int maxFila = 10;
        int entradaMax = 4;
        int entradaMin = 2;
        int saidaMax = 5;
        int saidaMin = 3;

        if (filas <= 0) {
            System.out.println("Qual o tamanho da fila?");
            maxFila = s.nextInt();
            System.out.println("Quantos servidores?");
            servidores = s.nextInt();
            System.out.println("Valor minimo do numero aleatorio de chegada:");
            entradaMin = s.nextInt();
            System.out.println("Valor maximo do numero aleatorio de chegada:");
            entradaMax = s.nextInt();
            System.out.println("Valor minimo do numero aleatorio de saida:");
            saidaMin = s.nextInt();
            System.out.println("Valor maximo do numero aleatorio de saida:");
            saidaMax = s.nextInt();
        } else {
            System.out.println("Qual o tamanho da fila?");
            maxFila = s.nextInt();
            System.out.println("Quantos servidores?");
            servidores = s.nextInt();
            System.out.println("Valor minimo do numero aleatorio de saida:");
            saidaMin = s.nextInt();
            System.out.println("Valor maximo do numero aleatorio de saida:");
            saidaMax = s.nextInt();
        }

        Fila fila = new Fila(filas, maxFila, servidores, entradaMin, entradaMax, saidaMin, saidaMax);
        return fila;
    }
}
