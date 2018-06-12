package math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Leonardo/Beatriz/Luiz
 */
public class Polinomio {

    public static void main(String[] args) {

        Scanner leitura = new Scanner(System.in);
        System.out.print("Informe a quantidade de pontos:");
        int pontos = leitura.nextInt();
        
         ArrayList<Double> numerosX = new ArrayList<>(); 
        double mOriginal[][] = new double[pontos][pontos + 1];//linha / coluna
        double mEscalonada[][] = new double[pontos][pontos + 1];//linha / coluna
        String vetor[];

        for (int lin = 0; lin < pontos; lin++) { //linha
            System.out.println("Informe o(s) ponto(s) cartesiano(s), ex 2;3 : ");
            String texto = leitura.next();
            
            String replace = texto.replace(",",".");

            vetor = replace.split(";");  //perguntar sobre os espaços  (";|;\\s");
            double x = Double.parseDouble(vetor[0]);
            double y = Double.parseDouble(vetor[1]);
            numerosX.add(x);

            int expoente = pontos;            //DEIXAR O PONTO EM QUE O X É ZERO NA ULTIMA LINHA
            for (int col = 0; col < pontos + 1; col++) {//colunas
                expoente--;
                mOriginal[lin][col] = Math.pow(x, expoente);
            }

            mOriginal[lin][pontos] = y; //a última coluna - Y
        }

        mostraMatriz(mOriginal, pontos);

        //Escalonamento
        double elemento1;
        double matriz[][];
        for (int rep = 0; rep < pontos; rep++) {
            matriz = (rep == 0) ? mOriginal : mEscalonada;

            //Processa linha principal (a linha que vai ser transformada em 1)
            for (int linha = 0; linha < pontos; linha++) {
                if (linha == rep) {
                    if (matriz[linha][rep] == 0) {
                        double linhaTemp;

                        for (int j = 0; j <= pontos; j++) {
                            linhaTemp = matriz[linha][j];
                            matriz[linha][j] = matriz[pontos - 1][j];
                            matriz[pontos - 1][j] = linhaTemp;
                        }
                    }
                    elemento1 = matriz[linha][rep];

                    if (elemento1 != 1) {    //if (mEscalonada[linha][coluna] != 0 && elemento1 != 1)
                        for (int coluna = rep; coluna < pontos + 1; coluna++) {
                            mEscalonada[linha][coluna] = matriz[linha][coluna] / elemento1;
                        }
                    } else {
                        for (int coluna = rep; coluna < pontos + 1; coluna++) {
                            mEscalonada[linha][coluna] = matriz[linha][coluna];
                        }
                    }
                }
            }

            //Processa linhas secundárias (o restante das linhas que seram transformadas em 0)
            for (int linha = 0; linha < pontos; linha++) {
                if (linha != rep) {
                    elemento1 = matriz[linha][rep];

                    if (elemento1 != 0) {
                        for (int coluna = rep; coluna < pontos + 1; coluna++) {
                            mEscalonada[linha][coluna] = matriz[linha][coluna] + (-elemento1 * mEscalonada[rep][coluna]);
                        }
                    } else {
                        for (int coluna = rep; coluna < pontos + 1; coluna++) {
                            mEscalonada[linha][coluna] = matriz[linha][coluna];
                        }
                    }
                }
                 }
                mostraMatriz(mEscalonada, pontos);
           
        }

        System.out.print("Função(");
        int expoente = pontos;            //DEIXAR O PONTO EM QUE O X É ZERO NA ULTIMA LINHA
            for (int linha = 0; linha < pontos-1; linha++) {//colunas
                expoente--;
                System.out.print(mEscalonada[linha][pontos] + "x^" + expoente + " + ");
            }
            System.out.print(mEscalonada[pontos-1][pontos]);

         System.out.print(", " + Collections.min(numerosX) + ", " + Collections.max(numerosX) + ") ");
        
    }

    public static void mostraMatriz(double m[][], int pontos) {
        for (int l = 0; l < pontos; l++) {
            for (int c = 0; c < pontos + 1; c++) {
                System.out.printf("\t" + String.format("%.2f", m[l][c]));
            }
            System.out.println();
        }
        System.out.println();
    }
}
