package org.fernandodev.validators;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fernandodev.core.FileValidator;

import java.io.File;
import java.io.IOException;

public class JsonValidator implements FileValidator {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean isValid(File file) {
        try{
            mapper.readTree(file);
            return true;
        }catch(IOException e){
            return false;
        }
    }
}
