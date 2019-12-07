package services;

import com.google.inject.Inject;

public abstract class AbstractService {
    protected void notNull(Object object, Exception exception) throws Exception {
        if(object==null) {
            throw exception;
        }
    }

}
