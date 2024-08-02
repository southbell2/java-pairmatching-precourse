package pairmatching.util;

import pairmatching.course.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;

public class Input {

    public final Course course;
    public final Level level;
    public final Mission mission;

    public Input(Course course, Level level, Mission mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

}
