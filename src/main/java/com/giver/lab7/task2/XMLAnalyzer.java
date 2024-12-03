package com.giver.lab7.task2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XMLAnalyzer {
    File inputFile;
    File outputFile;
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder;
    Document doc;
    NodeList nodeList;
    TransformerFactory transformerFactory;
    Transformer transformer;
    DOMSource source;
    StreamResult result;

    public XMLAnalyzer(String inputFilename, String outputFilename) {
        try {
            this.inputFile = new File(inputFilename);
            this.outputFile = new File(outputFilename);

            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public double calculateAverage() {
        nodeList = doc.getElementsByTagName("subject");
        double totalMarks = 0;
        int count = 0;
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element subject = (Element) nodeList.item(i);
            double mark = Double.parseDouble(subject.getAttribute("mark"));
            totalMarks += mark;
            count++;
        }
        return totalMarks / count;
    }

    public double averageMarkInXML() {
        nodeList = doc.getElementsByTagName("average");
        Element average = (Element) nodeList.item(0);
        return Double.parseDouble(average.getTextContent());
    }

    public void writeAverageValue(double newAverage) {
        try {
            NodeList averageList = doc.getElementsByTagName("average");
            if (averageList.getLength() > 0) {
                Element averageElement = (Element) averageList.item(0);
                averageElement.setTextContent(String.valueOf(newAverage));
            } else {
                // Если элемента <average> нет, создаем его
                Element averageElement = doc.createElement("average");
                averageElement.setTextContent(String.valueOf(newAverage));
                doc.getDocumentElement().appendChild(averageElement);
            }

            // Инициализация трансформера
            transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Создание нового выходного файла и потоков для записи
            source = new DOMSource(doc);
            result = new StreamResult(outputFile);
            transformer.transform(source, result);

            System.out.println("Создан новый файл с обновленным значением <average>: " + newAverage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
