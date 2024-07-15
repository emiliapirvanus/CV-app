package cvapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkExperience {

    private String companyName;

    private String jobTitle;

    private String startDate;

    private String endDate;

    private String jobDescription;

    public static boolean isValidStartDate(String startDate) {

        return startDate.matches("\\d{4}");
    }

    public static boolean isValidEndDate(String endDate){

        return endDate.equals("present") || endDate.matches("\\d{4}");
    }
}
