import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;

public class XML {
  public static DocumentBuilder newBuilder() throws ParserConfigurationException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
    return factory.newDocumentBuilder();
  }

  public static Document parse(String xml) throws Exception {
    return newBuilder().parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
  }
}
