package utils;

public class Writer {
  
  private String buffer = "";
  
  public Writer()
  {
  }

  public void write(String str)
  {
    this.buffer += str;
  };

  public void writeln(String str)
  {
    this.buffer += str + "\n";
  }
  
  public String getBuffer()
  {
    return this.buffer;
  }
}