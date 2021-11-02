package edu.epitech.timemanager.shared.business_rules;

public interface BusinessRule<TInput> {
    boolean isVerify(TInput input);
}
