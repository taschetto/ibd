package btree;

import graphviz.GraphViz;
import java.io.File;
import utils.Counter;
import utils.Writer;

public class BTree<TKey extends Comparable<TKey>, TRecord>
{
  private Node<TKey> root;

  public BTree()
  {
    this.root = new LeafNode<TKey, TRecord>();
  }

  public void insert(TKey key, TRecord value)
  {
    LeafNode<TKey, TRecord> leaf = this.findLeafByKey(key);
    leaf.insertKey(key, value);

    if (leaf.isOverflow())
    {
      Node<TKey> node = leaf.dealOverflow();
      if (node != null) this.root = node;
    }
  }

  public TRecord search(TKey key)
  {
    LeafNode<TKey, TRecord> leaf = this.findLeafByKey(key);

    int index = leaf.search(key);
    return (index == -1) ? null : leaf.getValue(index);
  }

  public void delete(TKey key)
  {
    LeafNode<TKey, TRecord> leaf = this.findLeafByKey(key);

    if (leaf.delete(key) && leaf.isUnderflow())
    {
      Node<TKey> node = leaf.dealUnderflow();
      if (node != null) this.root = node;
    }
  }

  private LeafNode<TKey, TRecord> findLeafByKey(TKey key)
  {
    Node<TKey> node = this.root;
    while (node.getNodeType() == NodeType.BranchNode)
    {
      node = ((BranchNode<TKey>)node).getChild(node.search(key));
    }

    return (LeafNode<TKey, TRecord>)node;
  }

  public void toGraphViz()
  {
    Counter counter = new Counter();
    Writer writer = new Writer();
  
    GraphViz gv = new GraphViz();
    gv.addln(gv.start_graph());
    gv.addln("node [shape = record,height=.1];");
    this.root.walk(counter, writer);
    gv.addln(writer.getBuffer());
    gv.addln(gv.end_graph());
    System.out.println(gv.getDotSource());

    String type = "svg";
    File out = new File("/tmp/out." + type);
    gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
  }
}