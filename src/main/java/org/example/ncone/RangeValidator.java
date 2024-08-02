package org.example.ncone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RangeValidator implements ConstraintValidator<ValidateRange, Range<?>> {

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public boolean isValid(Range<? extends Comparable<?>> range, ConstraintValidatorContext constraintValidatorContext) {
        if (range == null) {
            return true;
        }

        Comparable min = range.getMin();
        Comparable max = range.getMax();

        if (min == null || max == null) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("min & max can not be null")
                    .addConstraintViolation();

            return false;
        }

        if (min.compareTo(max) > 0) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("min > max")
                    .addConstraintViolation();

            return false;
        }

        return true;
    }

}
