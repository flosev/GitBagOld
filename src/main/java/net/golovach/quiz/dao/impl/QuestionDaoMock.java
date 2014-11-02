package net.golovach.quiz.dao.impl;

import net.golovach.quiz.dao.QuestionDao;
import net.golovach.quiz.dao.ThemeDao;
import net.golovach.quiz.dao.exception.DaoSystemException;
import net.golovach.quiz.dao.exception.NoSuchEntityException;
import net.golovach.quiz.entity.Answer;
import net.golovach.quiz.entity.Question;

import java.util.*;

import static java.util.Arrays.asList;

public class QuestionDaoMock implements QuestionDao {
    private Map<Integer, Question> memory = new HashMap<>();

    public QuestionDaoMock() {
        ThemeDao themes = new ThemeDaoMock();
        try {
            // iter
            memory.put(1, new Question(1, "iter-0", "for - is it a loop?", asList(yes(1, true), no(2, false)), "", asList(themes.selectById("java.core.proc.iter"))));
            memory.put(2, new Question(2, "iter-1", "switch - is it a loop?", asList(yes(3, false), no(4, true)), "", asList(themes.selectById("java.core.proc.iter"))));
            // rec
            memory.put(3, new Question(3, "rec-0", "Can calc factorial recursively?", asList(yes(5, true), no(6, false)), "", asList(themes.selectById("java.core.proc.rec"))));
            memory.put(4, new Question(4, "rec-1", "int x = 1;// Is it is recursion?", asList(yes(7, false), no(8, true)), "", asList(themes.selectById("java.core.proc.rec"))));
            // control flow
            memory.put(5, new Question(5, "control-flow-0", "Can exists 'try' without 'catch'?", asList(yes(9, true), no(10, false)), "", asList(themes.selectById("java.core.ex.control-flow"))));
            memory.put(6, new Question(6, "control-flow-1", "Can exists 'try' with two 'finally'?", asList(yes(11, false), no(12, true)), "", asList(themes.selectById("java.core.ex.control-flow"))));
            // checked/unchecked
            memory.put(7, new Question(7, "check/uncheck-0", "Exception - checked?", asList(yes(13, true), no(14, false)), "", asList(themes.selectById("java.core.ex.check/uncheck"))));
            memory.put(8, new Question(8, "check/uncheck-0", "Error - checked?", asList(yes(15, false), no(15, true)), "", asList(themes.selectById("java.core.ex.check/uncheck"))));
        } catch (DaoSystemException | NoSuchEntityException e) {
            throw new Error(e);
        }
    }

    @Override
    public Question selectById(int id) throws DaoSystemException, NoSuchEntityException {
        if (!memory.containsKey(id)) {
            throw new NoSuchEntityException("No Question for id == '" + id + "', only for " + memory.keySet());
        }
        return memory.get(id);
    }

    @Override
    public List<Question> selectAll() {
        return new ArrayList<>(memory.values());
    }

    private static Answer yes(int id, boolean correct) {
        return new Answer(id, "yes", correct, "");
    }

    private static Answer no(int id, boolean correct) {
        return new Answer(id, "no", correct, "");
    }
}
