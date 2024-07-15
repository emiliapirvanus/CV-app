package cvapp.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import cvapp.model.CV;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JsonDB implements IDB {

    private final ObjectMapper objectMapper;
    private final List<CV> cvs;
    private final File jsonFile;

    public JsonDB(String filePath) {

        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        jsonFile = new File(filePath);
        try {
            if (Files.notExists(jsonFile.toPath())) {
                objectMapper.writeValue(jsonFile, new ArrayList<>());
            }
            cvs =  objectMapper.readValue(jsonFile, new TypeReference<List<CV>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(CV cv) {
        cvs.add(cv);
        saveJson(cvs);

    }

    @Override
    public List<CV> loadAllCVs() {
        return cvs;
    }

    @Override
    public Optional<CV> findCVByLastName(String lastName) {
        return cvs.stream()
                .filter(cv -> cv.getLastName().equals(lastName))
                .findFirst();
    }

    @Override
    public List<CV> findCVsByEducationEndDate(String educationEndDate) {

        return cvs.stream()
                .filter(cv -> cv.getEducation().stream()
                        .anyMatch(education -> education.getEndDate().equals(educationEndDate)))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(CV cv)  {
        try {
            cvs.remove(cv);
            objectMapper.writeValue(jsonFile, cvs);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private void saveJson(List<CV> cvs) {
        try {
            objectMapper.writeValue(jsonFile, cvs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
