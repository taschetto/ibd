/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physical;

import logical.Record;

/**
 *
 * @author taschetto
 */
public class TableDatablock implements IDatablock {
  private Record[] records = new Record[DATABLOCK_SIZE_IN_RECORDS];
  private TableDatablock next_datablock;

  public TableDatablock() {
  }

  public Record[] getRecords() {
    return this.records;
  }
}
