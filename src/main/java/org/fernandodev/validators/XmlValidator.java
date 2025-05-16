package org.fernandodev.validators;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XmlValidator implements FileValidator {

    @Override
    public boolean isValid(File file) {
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.parse(file);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}