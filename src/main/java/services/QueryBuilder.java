package services;

public interface QueryBuilder<T> {
    public abstract QueryBuilder select();
    public abstract QueryBuilder where(SpecificationCriterion criterion);
    public abstract QueryBuilder like(SpecificationCriterion criterion);
    public abstract Object getObject();
}
