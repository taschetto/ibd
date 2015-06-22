package btree;

import utils.Counter;
import utils.Writer;

class BranchNode<TKey extends Comparable<TKey>> extends Node<TKey>
{
  protected Object[] children;

  public BranchNode()
  {
    this.keys = new Object[BTree.ORDER + 1];
    this.children = new Object[BTree.ORDER + 2];
  }

/* Getters & setters **********************************************************/

  public Node<TKey> getChild(int index)
  {
    return (Node<TKey>)this.children[index];
  }

  public void setChild(int index, Node<TKey> child)
  {
    this.children[index] = child;
    if (child != null) child.setParent(this);
  }

/* Insertion auxiliary methods ************************************************/

  private void insertAt(int index, TKey key, Node<TKey> leftChild, Node<TKey> rightChild)
  {
    for (int i = this.getKeyCount() + 1; i > index; --i)
      this.setChild(i, this.getChild(i - 1));
    
    for (int i = this.getKeyCount(); i > index; --i)
      this.setKey(i, this.getKey(i - 1));

    this.setKey(index, key);
    this.setChild(index, leftChild);
    this.setChild(index + 1, rightChild);
    this.keyCount += 1;
  }

/* Deletion auxiliary methods *************************************************/

  private void deleteAt(int index)
  {
    int i = 0;
    for (i = index; i < this.getKeyCount() - 1; ++i)
    {
      this.setKey(i, this.getKey(i + 1));
      this.setChild(i + 1, this.getChild(i + 2));
    }
    this.setKey(i, null);
    this.setChild(i + 1, null);
    --this.keyCount;
  }

/* Node<Tkey> methods implementation ******************************************/

  @Override
  public NodeType getNodeType()
  {
    return NodeType.BranchNode;
  }

  @Override
  public int search(TKey key)
  {
    int index = 0;
    for (index = 0; index < this.getKeyCount(); ++index)
    {
      int cmp = this.getKey(index).compareTo(key);
      if (cmp == 0)
        return index + 1;
      else if (cmp > 0)
        return index;
    }

    return index;
  }

  @Override
  protected Node<TKey> split()
  {
    int midIndex = this.getKeyCount() / 2;

    BranchNode<TKey> newRNode = new BranchNode<TKey>();
    for (int i = midIndex + 1; i < this.getKeyCount(); ++i)
    {
      newRNode.setKey(i - midIndex - 1, this.getKey(i));
      this.setKey(i, null);
    }
    for (int i = midIndex + 1; i <= this.getKeyCount(); ++i)
    {
      newRNode.setChild(i - midIndex - 1, this.getChild(i));
      newRNode.getChild(i - midIndex - 1).setParent(newRNode);
      this.setChild(i, null);
    }
    this.setKey(midIndex, null);
    newRNode.keyCount = this.getKeyCount() - midIndex - 1;
    this.keyCount = midIndex;

    return newRNode;
  }

  @Override
  protected Node<TKey> pushUpKey(TKey key, Node<TKey> leftChild, Node<TKey> rightNode)
  {
    int index = this.search(key);
    this.insertAt(index, key, leftChild, rightNode);
    if (this.isOverflow()) return this.dealOverflow();
    return this.getParent() == null ? this : null;
  }

  @Override
  protected void processChildrenTransfer(Node<TKey> borrower, Node<TKey> lender, int borrowIndex)
  {
    int borrowerChildIndex = 0;
    while (borrowerChildIndex < this.getKeyCount() + 1 && this.getChild(borrowerChildIndex) != borrower)
      ++borrowerChildIndex;

    if (borrowIndex == 0)
    {
      TKey upKey = borrower.transferFromSibling(this.getKey(borrowerChildIndex), lender, borrowIndex);
      this.setKey(borrowerChildIndex, upKey);
    }
    else
    {
      TKey upKey = borrower.transferFromSibling(this.getKey(borrowerChildIndex - 1), lender, borrowIndex);
      this.setKey(borrowerChildIndex - 1, upKey);
    }
  }

  @Override
  protected Node<TKey> processChildrenFusion(Node<TKey> leftChild, Node<TKey> rightChild)
  {
    int index = 0;
    while (index < this.getKeyCount() && this.getChild(index) != leftChild) ++index;
    TKey sinkKey = this.getKey(index);
    leftChild.fusionWithSibling(sinkKey, rightChild);
    this.deleteAt(index);
    if (!this.isUnderflow())      return null;
    if (this.getParent() != null) return this.dealUnderflow();
    if (this.getKeyCount() != 0)  return null;
    leftChild.setParent(null);
    return leftChild;
  }

  @Override
  protected void fusionWithSibling(TKey sinkKey, Node<TKey> rightSibling)
  {
    BranchNode<TKey> rightSiblingNode = (BranchNode<TKey>)rightSibling;

    int j = this.getKeyCount();
    this.setKey(j++, sinkKey);

    for (int i = 0; i < rightSiblingNode.getKeyCount(); ++i)
      this.setKey(j + i, rightSiblingNode.getKey(i));
    for (int i = 0; i < rightSiblingNode.getKeyCount() + 1; ++i)
      this.setChild(j + i, rightSiblingNode.getChild(i));
    this.keyCount += 1 + rightSiblingNode.getKeyCount();

    this.setRightSibling(rightSiblingNode.rightSibling);
    if (rightSiblingNode.rightSibling != null)
      rightSiblingNode.rightSibling.setLeftSibling(this);
  }

  @Override
  protected TKey transferFromSibling(TKey sinkKey, Node<TKey> sibling, int borrowIndex)
  {
    BranchNode<TKey> siblingNode = (BranchNode<TKey>)sibling;

    TKey upKey = null;
    if (borrowIndex == 0) 
    {
      int index = this.getKeyCount();
      this.setKey(index, sinkKey);
      this.setChild(index + 1, siblingNode.getChild(borrowIndex));      
      this.keyCount += 1;

      upKey = siblingNode.getKey(0);
      siblingNode.deleteAt(borrowIndex);
    }
    else
    {
      this.insertAt(0, sinkKey, siblingNode.getChild(borrowIndex + 1), this.getChild(0));
      upKey = siblingNode.getKey(borrowIndex);
      siblingNode.deleteAt(borrowIndex);
    }

    return upKey;
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

    int childNum = -1;

    for (Object o : this.children)
    {
      if (o == null) continue;
      Node<TKey> child = (Node<TKey>) o;
      childNum++;
      String childnodeid = child.walk(counter, writer);
      writer.writeln("\"" + nodeid + "\":f" + childNum + " -> \"" + childnodeid + "\"");
    }
    return nodeid;
  }
}