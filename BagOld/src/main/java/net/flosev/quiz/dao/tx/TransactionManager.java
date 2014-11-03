package net.flosev.quiz.dao.tx;

import java.sql.SQLException;

public interface TransactionManager {

    public <T, E extends Throwable> T call(UnitOfWork<T, E> unit) throws E, SQLException;
}

