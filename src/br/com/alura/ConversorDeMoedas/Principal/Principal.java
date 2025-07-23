package br.com.alura.ConversorDeMoedas.Principal;

import br.com.alura.ConversorDeMoedas.Calculadora.ConversorApp;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> opcoes = new ArrayList<>();
        opcoes.add("USD->BRL");
        opcoes.add("BRL->USD");
        opcoes.add("EUR->BRL");
        opcoes.add("BRL->EUR");
        opcoes.add("USD->EUR");
        opcoes.add("EUR->USD");

        int escolha;

        do {
            System.out.println("\n===== CONVERSOR DE MOEDAS =====");

            for (int i = 0; i < opcoes.size(); i++) {
                System.out.printf("%d - Converter %s\n", i + 1, opcoes.get(i));
            }
            System.out.println("0 - Sair");

            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();

            if (escolha >= 1 && escolha <= opcoes.size()) {
                String[] moedas = opcoes.get(escolha - 1).split("->");
                String moedaAtual = moedas[0];
                String moedaDestino = moedas[1];

                System.out.printf("Digite o valor em %s: ", moedaAtual);
                double valor = scanner.nextDouble();

                double resultado = ConversorApp.converter(moedaAtual, moedaDestino, valor);

                if (resultado != -1) {
                    System.out.printf("%.2f %s = %.2f %s\n", valor, moedaAtual, resultado, moedaDestino);
                } else {
                    System.out.println("Erro ao converter moeda.");
                }

            } else if (escolha != 0) {
                System.out.println("Opção inválida.");
            }

        } while (escolha != 0);

        System.out.println("Programa encerrado. Obrigado por usar!");
        scanner.close();
    }
}
