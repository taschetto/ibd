/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logical;

import physical.IDatablock;

/**
 *
 * @author taschetto
 */
public class Table {
  private String name;
  private IDatablock first_datablock;
  // TODO: estrutura para armazenar quais datablocks da tabela ainda tem algum
  //       espaço livre
}
