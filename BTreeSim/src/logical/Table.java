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
  
  public Record select(int clientId)
  {
    // busca por clientId (usa índice)
    return null;
  }
  
  public Record[] select(String name)
  {
    // busca por name (full table scan)
    return null;
  }
  
  public boolean insert(int clientId, String Name)
  {
    return false;
  }
  
  public boolean update(int clientId, String Name)
  {
    return false;
  }
  
  public boolean delete(int clientId)
  {
    return false;
  }
}
