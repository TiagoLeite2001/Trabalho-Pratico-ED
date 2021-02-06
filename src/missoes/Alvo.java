/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package missoes;

import interfaces.IDivisao;

/**
 *Esta classe guarda a informação de um alvo.
 */
public class Alvo {
    private IDivisao divisao;
    private String tipo;
    
    /**
     * Contrutor de um alvo.
     * @param divisao Divisão onde se encontra o alvo.
     * @param tipo Tipo de alvo.
     */
    public Alvo(IDivisao divisao, String tipo){
        this.divisao = divisao;
        this.tipo = tipo;
    }
    
    /**
     * Obter a divisão onde se encontra o alvo.
     * @return Divisao.
     */
    public IDivisao getDivisao() {
        return divisao;
    }
    
    /**
     * Obter o tipo de alvo.
     * @return Tipo.
     */
    public String getTipo() {
        return tipo;
    }
    
    /**
     * Obter a informação do alvo.
     * @return Informação em string.
     */
    @Override
    public String toString(){
        String info = "";
        info += "\n  *Divisão: " + this.divisao.getNome()+
                "\n  *Tipo: " + this.tipo;
        
        return info;
    }
    
    /**
     * Verificar se dois alvos são iguais.
     * @return true se os alvos forem iguais.
     * @return false se os alvos forem diferentes.
     */
    @Override
    public boolean equals(Object obj){
        if (obj != null && obj instanceof Alvo) {
            Alvo temp = (Alvo) obj;
            if (this.getDivisao().equals(temp.getDivisao()) && 
                    this.getTipo().equals(temp.getTipo())) {
                return true;
            }
        }
        return false;
    }
}
