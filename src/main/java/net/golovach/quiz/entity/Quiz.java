package net.golovach.quiz.entity;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private final int id;
    private String caption;
    private String intro;
    private List<Question> questions = new ArrayList<>();
    private List<Theme> themes = new ArrayList<>();

    public Quiz(int id, String caption, String intro, List<Question> questions, List<Theme> themes) {
        this.id = id;
        this.caption = caption;
        this.intro = intro;
        this.questions = questions;
        this.themes = themes;
    }

    public int getId() {
        return id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public List<Question> getQuestions() {
        return new ArrayList<>(questions);
    }

    public void setQuestions(List<Question> questions) {
        this.questions = new ArrayList<>(questions);
    }

    public List<Theme> getThemes() {
        return new ArrayList<>(themes);
    }

    public void setThemes(List<Theme> themes) {
        this.themes = new ArrayList<>(themes);
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", intro='" + intro + '\'' +
                ", questions=" + questions +
                ", themes=" + themes +
                '}';
    }
}
