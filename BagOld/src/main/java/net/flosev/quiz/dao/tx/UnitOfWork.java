package net.flosev.quiz.dao.tx;

public interface UnitOfWork<T, E extends Throwable> {

    public T call() throws E;
}
