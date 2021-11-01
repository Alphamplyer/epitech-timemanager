package edu.epitech.timemanager.shared.cqrs;

public interface QueryHandler<TQuery extends Query, TResult extends QueryResult> {
    TResult execute(TQuery query);
}
