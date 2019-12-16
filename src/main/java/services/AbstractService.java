package services;

import com.google.inject.Inject;
import entity.AbstractEntity;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;

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
            if (!Modifier.isStatic(field.getModifiers())
                    &&(value != null && !Objects.equals(field.getName().toLowerCase(), "id"))) {
                    field.set(to, value);
            }

        }
    }

}
