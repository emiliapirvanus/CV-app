package cvapp.repository;

import cvapp.model.CV;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IDB {

    void add(CV cv) throws IOException;

    List<CV> loadAllCVs();


    Optional<CV> findCVByLastName(String lastName);

    List<CV> findCVsByEducationEndDate(String educationEndDate);

    void delete(CV cv) throws IOException;
}
