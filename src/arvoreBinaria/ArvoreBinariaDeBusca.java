package arvoreBinaria;

class No {
    int valor;
    No esquerda;
    No direita;
    
    public No(int valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
    }
}

public class ArvoreBinariaDeBusca {
    No raiz;
    
    public ArvoreBinariaDeBusca() {
        this.raiz = null;
    }
    
    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }
    
    private No inserirRecursivo(No atual, int valor) {
        if (atual == null) {
            return new No(valor);
        }
        
        if (valor < atual.valor) {
            atual.esquerda = inserirRecursivo(atual.esquerda, valor);
        } else if (valor > atual.valor) {
            atual.direita = inserirRecursivo(atual.direita, valor);
        }
        
        return atual;
    }
    
    public void percursoEmOrdem() {
        percursoEmOrdemRecursivo(raiz);
        System.out.println();
    }
    
    private void percursoEmOrdemRecursivo(No no) {
        if (no != null) {
            percursoEmOrdemRecursivo(no.esquerda);
            System.out.print(no.valor + " ");
            percursoEmOrdemRecursivo(no.direita);
        }
    }
    
    public void encontrarFolhas() {
        encontrarFolhasRecursivo(raiz);
        System.out.println();
    }
    
    private void encontrarFolhasRecursivo(No no) {
        if (no != null) {
            if (no.esquerda == null && no.direita == null) {
                System.out.print(no.valor + " ");
            }
            encontrarFolhasRecursivo(no.esquerda);
            encontrarFolhasRecursivo(no.direita);
        }
    }
    
    public int calcularAltura() {
        return calcularAlturaRecursivo(raiz);
    }
    
    private int calcularAlturaRecursivo(No no) {
        if (no == null) {
            return 0;
        }
        
        int alturaEsquerda = calcularAlturaRecursivo(no.esquerda);
        int alturaDireita = calcularAlturaRecursivo(no.direita);
        
        return Math.max(alturaEsquerda, alturaDireita) + 1;
    }
    
    public void mostrarEstrutura() {
        mostrarEstruturaRecursivo(raiz, "", true);
    }
    
    private void mostrarEstruturaRecursivo(No no, String prefixo, boolean isEsquerda) {
        if (no != null) {
            System.out.println(prefixo + (isEsquerda ? "├── " : "└── ") + no.valor);
            String novoPrefixo = prefixo + (isEsquerda ? "│   " : "    ");
            mostrarEstruturaRecursivo(no.esquerda, novoPrefixo, true);
            mostrarEstruturaRecursivo(no.direita, novoPrefixo, false);
        }
    }
    
    public int getRaiz() {
        return raiz != null ? raiz.valor : -1;
    }
    
    public static void main(String[] args) {
        ArvoreBinariaDeBusca bst = new ArvoreBinariaDeBusca();
        
        int[] ra = {2, 4, 0, 4, 2, 3, 0};
        
        System.out.println("=== CONSTRUÇÃO DA ÁRVORE BINÁRIA DE BUSCA ===");
        System.out.println("Sequência de inserção (dígitos do RA):");
        for (int i = 0; i < ra.length; i++) {
            System.out.print(ra[i] + " ");
            bst.inserir(ra[i]);
        }
        System.out.println("\n");
        
        System.out.println("Estrutura da Árvore Binária de Busca:");
        bst.mostrarEstrutura();
        System.out.println();
        
        System.out.println("--- CARACTERÍSTICAS DA ÁRVORE RESULTANTE ---");
        System.out.println("Raiz: " + bst.getRaiz());
        
        System.out.print("Folhas (Nós sem filhos): ");
        bst.encontrarFolhas();
        
        System.out.println("Altura da Árvore: " + bst.calcularAltura());
        
        System.out.print("Percurso Em Ordem (Ordenado): ");
        bst.percursoEmOrdem();
    }
}