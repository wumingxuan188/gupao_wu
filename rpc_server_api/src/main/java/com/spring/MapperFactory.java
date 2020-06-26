package com.spring;

import org.springframework.beans.factory.FactoryBean;

public class MapperFactory  extends SqlSessionTemplate  implements FactoryBean{

    public Object getObject() throws Exception {
        return new Student();
    }


    public Class<?> getObjectType() {
        return null;
    }
}
