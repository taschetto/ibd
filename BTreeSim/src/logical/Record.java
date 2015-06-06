/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logical;

/**
 *
 * @author taschetto
 */
public class Record {
  
  private int clientId;
  private String name;

  public Record(int clientId, String name) {
    this.clientId = clientId;
    this.name = name;
  }

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
