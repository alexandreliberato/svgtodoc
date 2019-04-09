package org.feiler.svgtodocument;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;

import java.io.IOException;

public class SVGParser {

	Logger log = LogManager.getLogger(this.getClass().getName());

	public org.w3c.dom.Document toW3CDocument(String uri) throws IOException {

		log.log(Level.INFO, "> toW3CDocument: uri = " + uri);

		SAXSVGDocumentFactory f = initSAXSVGDocumentFactory();

		return createDocument(uri, f);
	}

	public org.jsoup.nodes.Document toDocument(String uri) throws IOException {

		log.log(Level.INFO, "> toDocument: uri = " + uri);

		org.jsoup.nodes.Document document;

		W3CDom w3c = new W3CDom();
		org.w3c.dom.Document wDoc = toW3CDocument(uri);

		String out = w3c.asString(wDoc);

		document = Jsoup.parse(out);

		return document;
	}

	private Document createDocument(String uri, SAXSVGDocumentFactory f) throws IOException {

		Document d = f.createDocument(uri);

		return d;
	}

	private SAXSVGDocumentFactory initSAXSVGDocumentFactory() {;
		return new SAXSVGDocumentFactory(XMLResourceDescriptor.getXMLParserClassName());
	}

	public String toJson(String path) {

		JsonNode jsonParentObject = JsonNodeFactory.instance.objectNode();


		//JSONArray list = new JSONArray();
//		for (Element table : doc.select("table")) {
//			for (Element row : table.select("tr")) {
//				JSONObject jsonObject = new JSONObject();
//				Elements tds = row.select("td");
//				String Name = tds.get(0).text();
//				String Group = tds.get(1).text();
//				String Code = tds.get(2).text();
//				String Lesson = tds.get(3).text();
//				String Day1 = tds.get(4).text();
//				String Day2 = tds.get(5).text();
//				String Day3= tds.get(6).text();
//				jsonObject.put("Group", Group);
//				jsonObject.put("Code", Code);
//				jsonObject.put("Lesson", Lesson);
//				jsonObject.put("Day1", Day1);
//				jsonObject.put("Day2", Day2);
//				jsonObject.put("Day3", Day3);
//				jsonParentObject.put(Name,jsonObject);
//			}
//		}
		return null;
	}
}
