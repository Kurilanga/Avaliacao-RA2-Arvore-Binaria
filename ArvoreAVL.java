class Node {
    int chave, altura;
    Node esquerda, direita;

    Node(int chave) {
        this.chave = chave;
        this.altura = 1;
    }
}

public class ArvoreAVL {
    private Node raiz;

    private int altura(Node no) {
        if (no == null) return 0;
        return no.altura;
    }

    private int fatorEquilibrio(Node no) {
        if (no == null) return 0;
        return altura(no.esquerda) - altura(no.direita);
    }

    private Node rotacaoDireita(Node y) {
        Node x = y.esquerda;
        Node T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;

        return x;
    }

    private Node rotacaoEsquerda(Node x) {
        Node y = x.direita;
        Node T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;

        return y;
    }

    private Node inserir(Node no, int chave) {
        if (no == null) return new Node(chave);

        if (chave < no.chave)
            no.esquerda = inserir(no.esquerda, chave);
        else if (chave > no.chave)
            no.direita = inserir(no.direita, chave);
        else
            return no;

        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));

        int fatorBalanceamento = fatorEquilibrio(no);

        if (fatorBalanceamento > 1 && chave < no.esquerda.chave)
            return rotacaoDireita(no);

        if (fatorBalanceamento < -1 && chave > no.direita.chave)
            return rotacaoEsquerda(no);

        if (fatorBalanceamento > 1 && chave > no.esquerda.chave) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        if (fatorBalanceamento < -1 && chave < no.direita.chave) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    public void inserir(int chave) {
        raiz = inserir(raiz, chave);
    }

    public boolean buscar(int chave) {
        return buscar(raiz, chave);
    }

    private boolean buscar(Node no, int chave) {
        if (no == null) return false;
        if (chave == no.chave) return true;
        if (chave < no.chave) return buscar(no.esquerda, chave);
        return buscar(no.direita, chave);
    }

    public void remover(int chave) {
        raiz = remover(raiz, chave);
    }

    private Node remover(Node no, int chave) {
        if (no == null) return no;

        if (chave < no.chave)
            no.esquerda = remover(no.esquerda, chave);
        else if (chave > no.chave)
            no.direita = remover(no.direita, chave);
        else {
            if ((no.esquerda == null) || (no.direita == null)) {
                Node temp = null;
                if (temp == no.esquerda)
                    temp = no.direita;
                else
                    temp = no.esquerda;

                if (temp == null) {
                    temp = no;
                    no = null;
                } else 
                    no = temp; 

                temp = null;
            } else {
                Node temp = menorValorNo(no.direita);
                no.chave = temp.chave;
                no.direita = remover(no.direita, temp.chave);
            }
        }

        
        if (no == null)
            return no;

        no.altura = Math.max(altura(no.esquerda), altura(no.direita)) + 1;

        int fatorBalanceamento = fatorEquilibrio(no);

        if (fatorBalanceamento > 1 && fatorEquilibrio(no.esquerda) >= 0)
            return rotacaoDireita(no);

        
        if (fatorBalanceamento > 1 && fatorEquilibrio(no.esquerda) < 0) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }

        
        if (fatorBalanceamento < -1 && fatorEquilibrio(no.direita) <= 0)
            return rotacaoEsquerda(no);

        
        if (fatorBalanceamento < -1 && fatorEquilibrio(no.direita) > 0) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }

        return no;
    }
    
    private Node menorValorNo(Node no) {
        Node atual = no;
        while (atual.esquerda != null)
            atual = atual.esquerda;
        return atual;
    }

    public void emOrdem() {
        emOrdem(raiz);
    }

    private void emOrdem(Node no) {
        if (no != null) {
            emOrdem(no.esquerda);
            System.out.print(no.chave + " ");
            emOrdem(no.direita);
        }
    }

}