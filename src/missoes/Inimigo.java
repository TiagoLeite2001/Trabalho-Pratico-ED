package missoes;

/**
 * Esta classe representa os dados relativos ao inimigo.
 */
public class Inimigo {
    
    private String nome;
    private int poder;
    
    /**
     * Construtor para o inimigo.
     * @param nome nome do inimigo
     * @param poder poder de dano do inimigo
     */
    public Inimigo(String nome,int poder){
        this.nome=nome;
        this.poder=poder;
    }

    /**
     * Obter o nome do inimigo.
     * @return nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obter o poder do inimigo.
     * @return poder
     */
    public int getPoder() {
        return poder;
    }
    
    /**
     * Verificar se dois inimigos são iguais.
     * @param obj
     * @return bolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Inimigo) {
            Inimigo temp=(Inimigo)obj;
            if (this.getNome().equals(temp.getNome())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Obter a informação do inimigo.
     * @return informação
     */
    public String toString(){
        String info="\n Inimigo: ";
        info+= "\n Nome: "+this.getNome()+"\n Poder: "+this.getPoder();
        return info;
    }
    
    
    
}
