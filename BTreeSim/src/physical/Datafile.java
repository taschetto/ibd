/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physical;

/**
 *
 * @author taschetto
 */
public class Datafile {
  public static final int DATAFILE_SIZE_IN_DATABLOCKS = 1024;
  
  private String name;
  private IDatablock[] datablocks = new IDatablock[DATAFILE_SIZE_IN_DATABLOCKS];

  public Datafile(String name) {
    this.name = name;
  }
}
