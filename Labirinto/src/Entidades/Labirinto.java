package Entidades;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Random;

public class Labirinto {
    
    public final int length = 7;
    private final Vertice[][] matriz;
    private Vertice inicio;
    private int ultimoValor;
    
    public Labirinto() {
        matriz = new Vertice[length][length];
        criarVertices();
        setVerticesAdjacentes();
        setVerticeInicial();
    }
    
    /**
     * Método que percorre a matriz inteira instânciado os vértices com valor 0
     */
    private void criarVertices() {
        for(int l = 0; l < length; l++)
            for(int c = 0; c < length; c++)
                matriz[l][c] = new Vertice(0);
    }
    
    /**
     * Retorna o vértice que é o início do jogo
     * @return Vertice
     */
    public Vertice getVerticeInicial() {
        return inicio;
    }
    
    /**
     * Escolhe um vértice aleatório e seta ele como vértice da qual o jogo
     * começa. Esta vértice é iniciado com valor igual a 1.
     */
    private void setVerticeInicial() {
        Random r = new Random();
        
        final int i = r.nextInt(length),
                  j = r.nextInt(length);
        
        matriz[i][j].setValor(1);
        matriz[i][j].setCor(Cor.Cinza);
        
        inicio = matriz[i][j];
        ultimoValor = inicio.getValor();
    }

    /**
     * Este método é usado para ter acesso não direto à matriz.
     * @return Matriz de vértices 
     */
    public Vertice[][] getMatriz() {
        return matriz;
    }
    
    /**
     * Para cada vértice na matriz de vértice é setado sua lista de adjacentes.
     */
    private void setVerticesAdjacentes() {
        for(int l = 0; l < matriz.length; l++) {
            for(int c = 0; c < matriz.length; c++) {
                matriz[l][c].setAdj(getAdjByPos(l, c));
            }
        }
    }
    
    /**
     * Método que retorna uma lista de vértices adjacentes ao vétice que está
     * na matriz na posicão matriz[linha][coluna], que de acordo com a regra
     * são os vértices de cima, baixo, direita e esquerda.
     * @param linha
     * @param coluna
     * @return Lista de vértices adjacentes 
     */
    private List<Vertice> getAdjByPos(int linha, int coluna) {
        List<Vertice> adj = new ArrayList<>();
        
        // Vértice de cima
        if(linha >= 1)
            adj.add(matriz[linha - 1][coluna]);
       
        // Vértice de baixo
        if(linha < matriz.length - 1)
            adj.add(matriz[linha + 1][coluna]);
        
        // Vértice da esquerda
        if(coluna >= 1)
            adj.add(matriz[linha][coluna - 1]);
        
        // Vérice da Direita
        if(coluna < matriz.length - 1)
            adj.add(matriz[linha][coluna + 1]);
        
        return adj;
    }   
    
    /**
     * Este método encontra os caminhos possíveis a partir de um vertice v de maneira
     * recursiva através de seus adjacentes
     * @param v 
     */
    public void encontrarCaminhos(Vertice v) {
        for(Vertice adj: v.getAdj()) {
            if(validarCaminho(v, adj)) {
                adj.setValor(ultimoValor);
                ultimoValor = adj.getValor() + 1;
                encontrarCaminhos(adj);
            }
        }
    }
    
    /**
     * Este método valida se é possivel chegar do vértice origem até o vérticr
     * candidato respeitando as regras do jogo.
     * @param origem
     * @param candidato
     * @return Boolean que é o resultado da tentativa.
     */
    private Boolean validarCaminho(Vertice origem, Vertice candidato) {
        for(Vertice a: candidato.getAdj()) 
            if(!a.equals(origem)) 
                if (a.getValor() > 0) 
                    return false;        
        return true;
    }
   
    /** 
     * Método que percorre a matriz verificando se encontra a mesma instância
     * do vértice passado como parâmetro e retorna sua coordenada.
     * @param vertice
     * @return Coordenada caso encontre e null caso contrário.
     */
    private Coordenada getCoordenadaVertice(Vertice vertice) {
        for(int l = 0; l < matriz.length; l++) 
            for(int c = 0; c < matriz.length; c++) 
                if(matriz[l][c].equals(vertice))
                    return new Coordenada(l, c);

        return null;
    }
            
    /**
     * Método que retorna uma representação gráfica do labirinto na forma de 
     * uma String
     * @return Labirinto em String
     */
    @Override
    public String toString() {
        String str = "";
        
        for(int l = 0; l < matriz.length; l++) {
            for(int c = 0; c < matriz.length; c++) {
                int aux = matriz[l][c].getValor();
                
                if(aux == 0) 
                    str = str + "  |";
                else if(aux < 10)
                    str = str + aux + " |";
                else 
                    str = str + aux + "|";
            }            
            str = str + "\n";            
        }        
        
        return str;
    }
    
}
