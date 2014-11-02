package net.golovach.quiz.dao.impl;

import net.golovach.quiz.dao.ThemeDao;
import net.golovach.quiz.dao.exception.DaoSystemException;
import net.golovach.quiz.dao.exception.NoSuchEntityException;
import net.golovach.quiz.entity.Question;
import net.golovach.quiz.entity.Theme;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class ThemeDaoMock implements ThemeDao {
    private Map<String, Theme> memory = new HashMap<>();

    public ThemeDaoMock() {
        List<Theme> emptyList = Collections.emptyList();

        Theme java = new Theme("java", "Java", null, emptyList);
        Theme javaCore = new Theme("java.core", "Java Core", java, emptyList);
        java.setChildren(asList(javaCore));
        Theme procedural = new Theme("java.core.proc", "Procedural Java", javaCore, emptyList);
        Theme exceptions = new Theme("java.core.ex", "Exceptions in Java", javaCore, emptyList);
        javaCore.setChildren(asList(procedural, exceptions));
        Theme iteration = new Theme("java.core.proc.iter", "Iteration", procedural, emptyList);
        Theme recursion = new Theme("java.core.proc.rec", "Recursion", procedural, emptyList);
        procedural.setChildren(asList(iteration, recursion));
        Theme controlFlow = new Theme("java.core.ex.control-flow", "Exceptions: control flow", exceptions, emptyList);
        Theme checkedUnchecked = new Theme("java.core.ex.check/uncheck", "Exceptions: checked/unchecked", exceptions, emptyList);
        exceptions.setChildren(asList(controlFlow, checkedUnchecked));
        
        this.memory.put(java.getId(), java);
        this.memory.put(javaCore.getId(), javaCore);
        this.memory.put(procedural.getId(), procedural);
        this.memory.put(exceptions.getId(), exceptions);
        this.memory.put(recursion.getId(), recursion);
        this.memory.put(iteration.getId(), iteration);
        this.memory.put(controlFlow.getId(), controlFlow);
        this.memory.put(checkedUnchecked.getId(), checkedUnchecked);
    }

    @Override
    public Theme selectById(String id) throws DaoSystemException, NoSuchEntityException {
        if (!memory.containsKey(id)) {
            throw new NoSuchEntityException("No Theme for id == '" + id + "', only for " + memory.keySet());
        }
        return memory.get(id);
    }

    @Override
    public List<Theme> selectAll() throws DaoSystemException {
        return new ArrayList<>(memory.values());
    }
}
