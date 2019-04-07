package org.feiler.svgtodocument;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
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
}
