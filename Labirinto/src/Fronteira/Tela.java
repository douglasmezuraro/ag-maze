package Fronteira;

import Entidades.Labirinto;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Tela {
    
    private static Labirinto labirinto;
    private static final String sLineBreak = "\n";

    /**
     * Método que printa a pergunta passada como parâmetro e retorna um valor
     * inteiro que o usuário digite 
     * @param pergunta
     * @return valor inteiro
     */
    public static int perguntarNumeroInteiro(String pergunta) {
        Scanner scanner = new Scanner(System.in);
        Boolean sucess = false;
        int result = 0;
        
        System.out.println(pergunta);
        while(!sucess) {
            try {
                result = scanner.nextInt();
                sucess = true;
            } catch(InputMismatchException e) {
                System.err.println("Erro: Tipo inválido, somente números inteiros. Digite novamente!");
                scanner.next();
            }
        }
        return result;
    }
    
    private static void criarLabirinto(int tamanho) {
        labirinto = new Labirinto(tamanho);
    }
   
    private static void criarCaminhos() {
        labirinto.dfs();
    }
    
    private static void encontrarCaminho() {
        int origem = perguntarNumeroInteiro("Digite a origem:");
        int destino = perguntarNumeroInteiro("Digite o destino:");          
        
        try {
            labirinto.bfs(origem);
            System.out.println(sLineBreak + 
                               "Caminho: " + 
                               labirinto.getCaminho(origem, destino) + 
                               sLineBreak);              
        } catch(NullPointerException e) {
            System.out.println(e.getMessage());
        }        
    }
    
    public static void printCabecario() {        
        System.out.print(
            "*********************************************** " + sLineBreak +                    
            "* Projeto    : Labirinto                        " + sLineBreak +
            "* Data       : 31/07/2017                       " + sLineBreak +
            "* Autores    : Douglas Mezuraro RA95676         " + sLineBreak +
            "*              Gustavo Leite Scalabrini RA89869 " + sLineBreak + 
            "* Disciplina : Algoritmos em Grafos             " + sLineBreak +
            "* Professor  : Rodrigo Calvo                    " + sLineBreak +                        
            "*********************************************** " + sLineBreak
        );
    }
    
    public static void printLabirinto() {
        System.out.println(labirinto.toString());
    }
    
    public static void main(String[] args) {
        printCabecario();
        criarLabirinto(perguntarNumeroInteiro("Qual o tamanho da matriz?"));
        criarCaminhos();
         
        do {
            printLabirinto();
            encontrarCaminho();
        } while(perguntarNumeroInteiro(
            "Deseja encontrar outro caminho?" + sLineBreak +
            "  == 0 : Nao                   " + sLineBreak +
            "  != 0 : Sim                   " + sLineBreak +
            "Resposta: ") != 0);
    }
    
}
