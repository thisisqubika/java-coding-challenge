package com.mooveit.cars.ingestion.ford.xml;

import com.mooveit.cars.ingestion.ford.xml.model.FordCatalogue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

@Slf4j
@Service
public class FordCatalogueXmlParser {

    // JAXBContext is thread safe and can be created once
    private JAXBContext jaxbContext;

    public FordCatalogueXmlParser() {
        try {
            jaxbContext = JAXBContext.newInstance(FordCatalogue.class);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public FordCatalogue parse(InputStream xmlInputStream) throws FordNotParsedException {
        XMLStreamReader reader = null;
        try {
            // Unmarshallers are not thread-safe.  Create a new one every time.
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            reader = XMLInputFactory.newInstance().createXMLStreamReader(xmlInputStream);
            return unmarshaller.unmarshal(reader, FordCatalogue.class).getValue();
        } catch (Exception e) {
            throw new FordNotParsedException();
        } finally {
            closeXMLStreamReader(reader);
        }
    }

    private void closeXMLStreamReader(XMLStreamReader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (XMLStreamException e) {
                log.error("Error in closing XMLStreamReader");
            }
        }
    }
}
