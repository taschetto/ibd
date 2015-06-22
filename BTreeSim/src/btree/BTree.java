package btree;

import graphviz.GraphViz;
import java.io.File;
import utils.Counter;
import utils.Writer;

public class BTree<TKey extends Comparable<TKey>, TRecord>
{
  public static int ORDER = 3;
  private Node<TKey> root;

  public BTree(int order)
  {
    BTree.ORDER = order;
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

  public String toGraphViz()
  {
    Counter counter = new Counter();
    Writer writer = new Writer();
  
    GraphViz gv = new GraphViz();
    gv.addln(gv.start_graph());
    gv.addln("node [shape = record,height=.1];");
    this.root.walk(counter, writer);
    gv.addln(writer.getBuffer());
    gv.addln(gv.end_graph());

    String type = "svg";
    String path = "";
    if (System.getProperty("os.name").startsWith("Windows"))
    {
      path = System.getProperty("java.io.tmpdir") + "out." + type;
    }
    else if (System.getProperty("os.name").startsWith("Linux") || System.getProperty("os.name").startsWith("LINUX"))
    {
      path = "/tmp/out." + type;
    }
    File out = new File(path);
    gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
    
    return path;
  }
}