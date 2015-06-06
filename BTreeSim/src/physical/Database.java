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
public class Database {
  public static final int DATABASE_SIZE_IN_DATAFILES = 1;
  
  private String name;
  private Datafile[] datafiles = new Datafile[DATABASE_SIZE_IN_DATAFILES];
}
