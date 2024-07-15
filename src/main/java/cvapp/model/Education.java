package cvapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Education {

    private String collegeName;

    private String startDate;

    private String endDate;

    private String degree;


    public static boolean isValidStartDate(String startDate) {

       return startDate.matches("\\d{4}");
    }

    public static boolean isValidEndDate(String endDate){

        return endDate.equals("present") || endDate.matches("\\d{4}");
    }

    public static boolean isValidDegree(String degree) {
        return degree == null || degree.trim().isEmpty();
    }
}
