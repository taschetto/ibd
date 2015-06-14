/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btreesim;

import btree.BTree;

/**
 *
 * @author taschetto
 */
public class BTreeSim {

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]){
		IntegerBTree tree = new IntegerBTree();
        
        /*
          Criar opcoes para:
       
            1. Inserção de um lote de N registros com valores aleatórios ou sequenciais.
            2. Busca de um registro pelo valor de chave.
            3. Exclusão de um registro individual na tabela.
        */
	}
}


class IntegerBTree extends BTree<Integer, Integer> {
	public void insert(int key) {
		this.insert(key, key);
	}
	
	public void remove(int key) {
		this.delete(key);
	}
}