
package utils;

public class Counter
{
  private int counter;

  public Counter()
  {
    this.counter = 0;
  }

  public Counter(int counter)
  {
    this.counter = counter;
  }

  public int getValue()
  {
    return this.counter;
  }

  public void clear()
  {
    this.counter = 0;
  }

  public int increment()
  {
    return ++this.counter;
  }

  public String toString()
  {
    return "" + this.counter;
  }
}