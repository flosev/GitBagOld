package net.golovach.quiz.entity;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private final int id;
    private String caption;
    private String question;
    private List<Answer> answers = new ArrayList<>();
    private String explanation;
    private List<Theme> themes = new ArrayList<>();

    public Question(int id, String caption, String question, List<Answer> answers, String explanation, List<Theme> themes) {
        this.id = id;
        this.caption = caption;
        this.question = question;
        this.answers = answers;
        this.explanation = explanation;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return new ArrayList<>(answers);
    }

    public void setAnswers(List<Answer> answers) {
        if (answers == null) {
            throw new IllegalArgumentException();
        }
        this.answers = new ArrayList<>(answers);
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public List<Theme> getThemes() {
        return new ArrayList<>(themes);
    }

    public void setThemes(List<Theme> themes) {
        if (themes == null) {
            throw new IllegalArgumentException();
        }
        this.themes = new ArrayList<>(themes);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", question='" + question + '\'' +
                ", answers=" + answers +
                ", explanation='" + explanation + '\'' +
                ", themes=" + themes +
                '}';
    }
}
