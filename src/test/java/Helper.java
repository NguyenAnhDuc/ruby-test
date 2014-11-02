import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Helper {
	public static List<DataTest> readXmlFile(String  fileName){
		List<DataTest> datatests = new ArrayList<DataTest>();
		try {
				File fXmlFile = new File(fileName);
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);
			 
				//optional, but recommended
				//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
				doc.getDocumentElement().normalize();
			 
				NodeList nList = doc.getElementsByTagName("data");
			 
				System.out.println("----------------------------");
			 
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						DataTest dataTest = new DataTest();
						NameMapper nameMapper = new NameMapper();
						dataTest.setId( Integer.parseInt(eElement.getAttribute("id")));
						dataTest.setQuestion( eElement.getElementsByTagName("question").item(0).getTextContent());
						dataTest.setDomain( eElement.getElementsByTagName("domain").item(0).getTextContent());
						dataTest.setIntent( eElement.getElementsByTagName("intent").item(0).getTextContent());
						if (eElement.getElementsByTagName("cinname").getLength() > 0)
							nameMapper.setMv_cinName(eElement.getElementsByTagName("cinname").item(0).getTextContent());
						if (eElement.getElementsByTagName("channel").getLength() > 0)
							nameMapper.setTv_channel(eElement.getElementsByTagName("channel").item(0).getTextContent());
						if (eElement.getElementsByTagName("movietitle").getLength() > 0)
							nameMapper.setMv_movieTitle(eElement.getElementsByTagName("movietitle").item(0).getTextContent());
						if (eElement.getElementsByTagName("tvprogram").getLength() > 0)
							nameMapper.setTv_program(eElement.getElementsByTagName("tvprogram").item(0).getTextContent());
						dataTest.setNameMapper(nameMapper);
						datatests.add(dataTest);
					}
				}
			    } catch (Exception e) {
				e.printStackTrace();
			    }
		return datatests;
	}
	
	public static void main(String[] args){
		readXmlFile("data_test_api.xml");
	}
}
