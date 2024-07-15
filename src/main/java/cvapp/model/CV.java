package cvapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CV {

    private String firstName;

    private String lastName;

    private Contact contact;

    private List<Education> education;

    private List<WorkExperience> workExperience;

    private List<String> skills;

    private String githubLink;

    public static boolean isValidLink(String githubLink) {

        try {
            new URL(githubLink);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }

    }
}
