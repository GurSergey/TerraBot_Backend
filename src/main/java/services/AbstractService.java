package services;

import com.google.inject.Inject;
import entity.AbstractEntity;

import java.lang.reflect.Field;

public abstract class AbstractService {
    protected void notNull(Object object, Exception exception) throws Exception {
        if(object==null) {
            throw exception;
        }
    }

    protected void copyFieldsNotNull(AbstractEntity from, AbstractEntity to, Class clazz) throws IllegalAccessException {
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            Object value = field.get(from);
            if (value != null) {
                if (field.get(from) instanceof AbstractEntity) {
                    copyFieldsNotNull((AbstractEntity) field.get(from),
                            (AbstractEntity) field.get(to), field.get(from).getClass());
                } else {
                    field.set(to, value);
                }
            }

        }
    }

}
