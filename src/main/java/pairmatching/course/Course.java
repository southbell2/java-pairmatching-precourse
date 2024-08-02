package pairmatching.course;

import pairmatching.util.MakeCrew;

public enum Course {
    BACKEND("백엔드", new Backend(new MakeCrew())),
    FRONTEND("프론트엔드", new Frontend(new MakeCrew()));

    private final String name;
    private final Crew crew;

    Course(String name, Crew crew) {
        this.name = name;
        this.crew = crew;
    }

    public String getName() {
        return name;
    }

    public Crew getCrew() {
        return crew;
    }
}
