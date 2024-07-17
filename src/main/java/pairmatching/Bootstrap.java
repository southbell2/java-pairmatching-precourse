package pairmatching;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bootstrap {

    public static final List<String> backCrews = new ArrayList<>();
    public static final List<String> frontCrews = new ArrayList<>();

    static {
        makeCrewList("backend-crew.md", backCrews);
        makeCrewList("frontend-crew.md", frontCrews);
    }

    private static void makeCrewList(String filename, List<String> crewList) {
        File file = getCrewFile(filename);
        if (file.exists()) {
            BufferedReader bufferedReader = getBufferedReader(file);
            String crew;
            while ((crew = readCrew(bufferedReader)) != null) {
                crewList.add(crew);
            }
        }
    }

    private static File getCrewFile(String filename) {
        ClassLoader classLoader = Application.class.getClassLoader();
        URI backendURI = null;
        try {
            backendURI = Objects.requireNonNull(classLoader.getResource(filename)).toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return new File(backendURI);
    }

    private static BufferedReader getBufferedReader(File file) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return bufferedReader;
    }

    private static String readCrew(BufferedReader bufferedReader) {
        String crew;
        try {
            crew = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return crew;
    }
}
