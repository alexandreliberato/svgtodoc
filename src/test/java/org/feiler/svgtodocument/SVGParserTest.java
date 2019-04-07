package org.feiler.svgtodocument;

import org.apache.batik.dom.util.SAXIOException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Unit test for simple App.
 */
public class SVGParserTest {

    public static final String RESOURCE_DIAGRAM_SVG_VALID = "/diagram.svg";
    public static final String RESOURCE_DIAGRAM_SVG_INVALID = "/diagram-invalido.svg";
    public static final String RESOURCE_DIAGRAM_SVG_NOT_FOUND = "/isto-no-ecsiste.svg";

    SVGParser svgParser;

    public SVGParserTest( ) {

        this.svgParser = new SVGParser();
    }

    @Test
    void testParseSVGToDocument() throws IOException {

        URL url = getResourceURL(RESOURCE_DIAGRAM_SVG_VALID);

        org.jsoup.nodes.Document doc = this.svgParser.toDocument(url.getPath());

        assertNotNull(doc);
    }

    @Test
    void parseSVGToW3CDocumentTest() throws IOException {

        URL url = getResourceURL(RESOURCE_DIAGRAM_SVG_VALID);

        org.w3c.dom.Document doc = this.svgParser.toW3CDocument(url.getPath());

        assertNotNull(doc);
    }

    @Test
    void parseInvalidSVGToDocument() throws IOException {

        URL url = getResourceURL(RESOURCE_DIAGRAM_SVG_INVALID);

        assertThrows(IOException.class, () -> {
            this.svgParser.toDocument(url.getPath());
        });
    }

    @Test
    void parseInvalidW3CSVGToDocumentTest() {

        URL url = getResourceURL(RESOURCE_DIAGRAM_SVG_INVALID);

        assertThrows(IOException.class, () -> {
            this.svgParser.toW3CDocument(url.getPath());
        });
    }

    @Test
    void parseNonExistentW3CSVGToDocumentTest() {

        URL url = getResourceURL(RESOURCE_DIAGRAM_SVG_NOT_FOUND);

        assertThrows(NullPointerException.class, () -> {
            this.svgParser.toW3CDocument(url.getPath());
        });
    }

    private URL getResourceURL(String resource) {
        return this.getClass().getResource(resource);
    }
}
