package com.mooveit.cars.helper;

import java.io.StringWriter;

import com.mooveit.cars.dto.CatalogueDto;
import com.mooveit.cars.dto.EngineDto;
import com.mooveit.cars.dto.ModelDto;
import com.mooveit.cars.dto.SubModelsDto;
import com.mooveit.cars.dto.WheelsDto;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public final class XStreamHelper {
	private XStreamHelper() {}

	public static String toXML(CatalogueDto catalogueDto) {
		final XStream xstream = getXStream();
		xstream.setMode(XStream.ID_REFERENCES);

		StringWriter writer = new StringWriter();
		xstream.marshal(catalogueDto, new PrettyPrintWriter(writer));
		return writer.toString();
	}

	private static XStream getXStream() {
		final XStream xstream = new XStream(new StaxDriver());
		xstream.autodetectAnnotations(true);
		xstream.processAnnotations(CatalogueDto.class);
		xstream.processAnnotations(ModelDto.class);
		xstream.processAnnotations(EngineDto.class);
		xstream.processAnnotations(WheelsDto.class);
		xstream.processAnnotations(SubModelsDto.class);
		return xstream;
	}

	public static CatalogueDto xmlToDto(String xmlInput) {
		final XStream xstream = getXStream();
		xstream.setMode(XStream.ID_REFERENCES);
		return (CatalogueDto) xstream.fromXML(xmlInput);
	}

}
