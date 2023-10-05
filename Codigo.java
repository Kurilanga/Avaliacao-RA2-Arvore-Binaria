import java.util.Random;


public class Codigo {
    public static void main(String[] args) {
        Random random = new Random(123);
        Arvore<Integer> arvore = new Arvore<>();
        ArvoreAVL arvoreAVL = new ArvoreAVL();
        int[] tamanhos = {100, 500, 1000, 10000, 20000};
        for (int tamanho: tamanhos){
            for (int i = 0; i < tamanho; i++) {
                int valor = random.nextInt();
                arvore.adicionar(valor);
                arvoreAVL.inserir(valor);
            }
        }   
        System.out.println("\nEm ordem:");
        arvore.emOrdem(arvore.getRaiz());
        System.out.println("\nPre ordem:");
        arvore.preOrdem(arvore.getRaiz());
        System.out.println("\nPos ordem:");
        arvore.posOrdem(arvore.getRaiz());

    ////////////////////////////////////////////////////////

        System.out.println("\nEm ordem:");
        arvoreAVL.emOrdem();
        
     
    }
    
}
