package services;

public class SpecificationCriterion {
    public SpecificationCriterion(String nameField, Object value) {
        this.nameField = nameField;
        this.value = value;
    }

    public String nameField;
    public Object value;
}
