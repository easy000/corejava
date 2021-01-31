package com.easy.utils;

/**
 * @author zhouym
 * @version [1.0, 2018/3/1]
 */

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

public class ValidationUtil {
    private static final Logger logger = LoggerFactory.getLogger(ValidationUtil.class);
    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private static final ExecutableValidator executableValidator = Validation.buildDefaultValidatorFactory().getValidator().forExecutables();

    public ValidationUtil() {
    }

    public static ValidationUtil getInstance() {
        return ValidationUtilHolder.instance;
    }

    public void validateParams(Object object) {
        if(object != null) {
            if(logger.isDebugEnabled()) {
                logger.debug(object.toString());
            }

            Set violations = validator.validate(object, new Class[0]);
            Iterator iterator = violations.iterator();
            if(iterator.hasNext()) {
                String errMessage = ((ConstraintViolation)iterator.next()).getMessage();
                throw new ValidationException(errMessage);
            }
        } else {
            throw new ValidationException("The parameters to be tested can not be empty");
        }
    }

    public void validateParams(Object object, Method method, Object[] parameterValues) {
        if(object != null && method != null) {
            Set violations = executableValidator.validateParameters(object, method, parameterValues, new Class[0]);
            Iterator iterator = violations.iterator();
            if(iterator.hasNext()) {
                String var11 = ((ConstraintViolation)iterator.next()).getMessage();
                throw new ValidationException(var11);
            } else {
                if(ArrayUtils.isNotEmpty(parameterValues)) {
                    Object[] arr$ = parameterValues;
                    int len$ = parameterValues.length;

                    for(int i$ = 0; i$ < len$; ++i$) {
                        Object param = arr$[i$];
                        if(param != null) {
                            String dataType = param.getClass().getName();
                            if(!JavaDataTypeUtil.isPrimitive(dataType) && !JavaDataTypeUtil.isPrimitiveWrapper(dataType)) {
                                this.validateParams(param);
                            }
                        }
                    }
                }

            }
        } else {
            throw new ValidationException("The parameters to be tested can not be empty");
        }
    }

    private static class ValidationUtilHolder {
        private static ValidationUtil instance = new ValidationUtil();

        private ValidationUtilHolder() {
        }
    }
}

