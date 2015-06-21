package btree;

import utils.Counter;
import utils.Writer;

class LeafNode<TKey extends Comparable<TKey>, TValue> extends Node<TKey>
{
  private Object[] values;

  public LeafNode()
  {
    this.keys = new Object[BTree.ORDER + 1];
    this.values = new Object[BTree.ORDER + 1];
  }

/* Getters & setters **********************************************************/

  public TValue getValue(int index)
  {
    return (TValue)this.values[index];
  }

  public void setValue(int index, TValue value)
  {
    this.values[index] = value;
  }

/* Insertion auxiliary methods ************************************************/

  public void insertKey(TKey key, TValue value)
  {
    int index = 0;
    while (index < this.getKeyCount() && this.getKey(index).compareTo(key) < 0) ++index;
    this.insertAt(index, key, value);
  }

  private void insertAt(int index, TKey key, TValue value)
  {
    // move space for the new key
    for (int i = this.getKeyCount() - 1; i >= index; --i)
    {
      this.setKey(i + 1, this.getKey(i));
      this.setValue(i + 1, this.getValue(i));
    }

    // insert new key and value
    this.setKey(index, key);
    this.setValue(index, value);
    ++this.keyCount;
  }

/* Deletion auxiliary methods *************************************************/

  private void deleteAt(int index) {
    int i = index;
    for (i = index; i < this.getKeyCount() - 1; ++i)
    {
      this.setKey(i, this.getKey(i + 1));
      this.setValue(i, this.getValue(i + 1));
    }
    this.setKey(i, null);
    this.setValue(i, null);
    --this.keyCount;
  }

  public boolean delete(TKey key)
  {
    int index = this.search(key);
    if (index == -1)
      return false;

    this.deleteAt(index);
    return true;
  }

/* Node<Tkey> methods implementation ******************************************/

  @Override
  public NodeType getNodeType()
  {
    return NodeType.LeafNode;
  }

  @Override
  public int search(TKey key)
  {
    for (int i = 0; i < this.getKeyCount(); ++i)
    {
       int cmp = this.getKey(i).compareTo(key);
       if (cmp == 0)
       {
         return i;
       }
       else if (cmp > 0)
       {
         return -1;
       }
    }

    return -1;
  }

  @Override
  protected Node<TKey> split()
  {
    int midIndex = this.getKeyCount() / 2;
    LeafNode<TKey, TValue> newRNode = new LeafNode<TKey, TValue>();
    for (int i = midIndex; i < this.getKeyCount(); ++i)
    {
      newRNode.setKey(i - midIndex, this.getKey(i));
      newRNode.setValue(i - midIndex, this.getValue(i));
      this.setKey(i, null);
      this.setValue(i, null);
    }
    newRNode.keyCount = this.getKeyCount() - midIndex;
    this.keyCount = midIndex;

    return newRNode;
  }

  @Override
  protected Node<TKey> pushUpKey(TKey key, Node<TKey> leftChild, Node<TKey> rightNode)
  {
    throw new UnsupportedOperationException();
  }

  @Override
  protected void processChildrenTransfer(Node<TKey> borrower, Node<TKey> lender, int borrowIndex)
  {
    throw new UnsupportedOperationException();
  }

  @Override
  protected Node<TKey> processChildrenFusion(Node<TKey> leftChild, Node<TKey> rightChild)
  {
    throw new UnsupportedOperationException();
  }

  @Override
  protected void fusionWithSibling(TKey sinkKey, Node<TKey> rightSibling)
  {
    LeafNode<TKey, TValue> siblingLeaf = (LeafNode<TKey, TValue>)rightSibling;

    int j = this.getKeyCount();
    for (int i = 0; i < siblingLeaf.getKeyCount(); ++i)
    {
      this.setKey(j + i, siblingLeaf.getKey(i));
      this.setValue(j + i, siblingLeaf.getValue(i));
    }
    this.keyCount += siblingLeaf.getKeyCount();

    this.setRightSibling(siblingLeaf.rightSibling);
    if (siblingLeaf.rightSibling != null)
      siblingLeaf.rightSibling.setLeftSibling(this);
  }

  @Override
  protected TKey transferFromSibling(TKey sinkKey, Node<TKey> sibling, int borrowIndex)
  {
    LeafNode<TKey, TValue> siblingNode = (LeafNode<TKey, TValue>)sibling;

    this.insertKey(siblingNode.getKey(borrowIndex), siblingNode.getValue(borrowIndex));
    siblingNode.deleteAt(borrowIndex);

    return borrowIndex == 0 ? sibling.getKey(0) : this.getKey(0);
  }
  
  @Override
  protected String walk(Counter counter, Writer writer)
  {
    String nodeid = "node" + counter.increment();
    writer.write(nodeid + "[label = \"");
    
    int i = 0;
    for (; i < this.getKeyCount(); i++)
    {
        writer.write("<f" + i + "> |");
        writer.write(this.getKey(i).toString());
        writer.write("|");
    }
 
    writer.writeln("<f" + i + ">\"];");
    
    return nodeid;
  }
}