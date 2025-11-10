package io.blazingtwist.xml.instances;

import io.blazingtwist.xml.exception.WrapperRuntimeException;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class XsdInstance extends InstanceData {
	private List<String> schemaStrings;
	private List<File> schemaFiles;
	private SchemaFactory schemaFactory;
	private Validator validator;

	public XsdInstance() {
		schemaStrings = new ArrayList<>();
		schemaFiles = new ArrayList<>();
	}

	public void addSchema(String str) {
		schemaStrings.add(str);
		validator = null;
	}

	public void addSchema(File file) {
		schemaFiles.add(file);
		validator = null;
	}

	public SchemaFactory getSchemaFactory() {
		if (schemaFactory == null) {
			schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		}
		return schemaFactory;
	}

	public Validator getValidator() throws SAXException, FileNotFoundException {
		if (validator == null) {
			final StreamSource[] schemas = Stream.concat(
					schemaStrings.stream().map(ss -> new StreamSource(new StringReader(ss))),
					schemaFiles.stream().map(sf -> new StreamSource(openFile(sf)))
			).toArray(StreamSource[]::new);
			validator = getSchemaFactory().newSchema(schemas).newValidator();
		}
		return validator;
	}

	@Override
	public void close() {
		super.close();
		schemaStrings = null;
		schemaFiles = null;
		schemaFactory = null;
		validator = null;
	}

	private static FileInputStream openFile(File file) {
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new WrapperRuntimeException(e);
		}
	}

}
