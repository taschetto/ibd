package btree;

import graphviz.GraphViz;
import java.io.File;

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

  public void plot()
  {
    GraphViz gv = new GraphViz();
    gv.addln(gv.start_graph());
    gv.addln("node [shape = record,height=.1];");
    gv.addln("node0[label = \"<f0> |11|<f1> |20|<f2>\"];");
    gv.addln("node1[label = \"<f0> |10|<f1>\"];");
    gv.addln("node2[label = \"<f0> |1|<f1> |2|<f2>\"];");
    gv.addln("\"node1\":f0 -> \"node2\"");
    gv.addln("node3[label = \"<f0> |11|<f1>\"];");
    gv.addln("\"node1\":f1 -> \"node3\"");
    gv.addln("\"node0\":f0 -> \"node1\"");
    gv.addln("node4[label = \"<f0> |11|<f1> |12|<f2>\"];");
    gv.addln("node5[label = \"<f0> |11|<f1> |11|<f2>\"];");
    gv.addln("\"node4\":f0 -> \"node5\"");
    gv.addln("node6[label = \"<f0> |11|<f1>\"];");
    gv.addln("\"node4\":f1 -> \"node6\"");
    gv.addln("node7[label = \"<f0> |12|<f1> |15|<f2>\"];");
    gv.addln("\"node4\":f2 -> \"node7\"");
    gv.addln("\"node0\":f1 -> \"node4\"");
    gv.addln("node8[label = \"<f0> |30|<f1>\"];");
    gv.addln("node9[label = \"<f0> |21|<f1> |22|<f2>\"];");
    gv.addln("\"node8\":f0 -> \"node9\"");
    gv.addln("node10[label = \"<f0> |31|<f1> |32|<f2> |63|<f3>\"];");
    gv.addln("\"node8\":f1 -> \"node10\"");
    gv.addln("\"node0\":f2 -> \"node8\"");
    gv.addln(gv.end_graph());
    System.out.println(gv.getDotSource());

    String type = "svg";
    File out = new File("/tmp/out." + type);
    gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
  }
}