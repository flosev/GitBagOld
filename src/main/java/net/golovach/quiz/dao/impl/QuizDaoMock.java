package net.golovach.quiz.dao.impl;

import net.golovach.quiz.dao.QuestionDao;
import net.golovach.quiz.dao.QuizDao;
import net.golovach.quiz.dao.ThemeDao;
import net.golovach.quiz.dao.exception.DaoSystemException;
import net.golovach.quiz.dao.exception.NoSuchEntityException;
import net.golovach.quiz.entity.Quiz;
import net.golovach.quiz.entity.Theme;

import java.util.*;

import static java.util.Arrays.asList;

public class QuizDaoMock implements QuizDao {
    private Map<Integer, Quiz> memory = new HashMap<>();

    public QuizDaoMock() {
        QuestionDao questions = new QuestionDaoMock();
        ThemeDao themes = new ThemeDaoMock();
        try {
            this.memory.put(1, new Quiz(1, "Loops quiz", "Some questions for Lecture #1", asList(questions.selectById(1), questions.selectById(2)), asList(themes.selectById("java.core.proc.iter"))));
            this.memory.put(2, new Quiz(2, "Recursion quiz", "Some questions for Lecture #2", asList(questions.selectById(3), questions.selectById(4)), asList(themes.selectById("java.core.proc.rec"))));
            this.memory.put(3, new Quiz(3, "Exceptions control-flow quiz", "Some questions for Lecture #5", asList(questions.selectById(5), questions.selectById(6)), asList(themes.selectById("java.core.ex.control-flow"))));
            this.memory.put(4, new Quiz(4, "Exceptions check/uncheck quiz", "Some questions for Lecture #6", asList(questions.selectById(7), questions.selectById(8)), asList(themes.selectById("java.core.ex.check/uncheck"))));
        } catch (DaoSystemException | NoSuchEntityException e) {
            throw new Error(e);
        }
    }

    @Override
    public Quiz selectById(int id) throws DaoSystemException, NoSuchEntityException {
        if (!memory.containsKey(id)) {
            throw new NoSuchEntityException("No Quiz for id == '" + id + "', only for " + memory.keySet());
        }
        return memory.get(id);
    }

    @Override
    public List<Quiz> selectByTheme(Theme theme) throws DaoSystemException {
        ArrayList<Quiz> result = new ArrayList<>();
        for (Quiz quiz : memory.values()) {
            if (quiz.getThemes().contains(theme)) {
                result.add(quiz);
            }
        }
        return result; 
    }

    @Override
    public List<Quiz> selectAll() throws DaoSystemException {
        return new ArrayList<>(memory.values());
    }
}
