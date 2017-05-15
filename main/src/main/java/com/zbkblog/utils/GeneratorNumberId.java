package com.zbkblog.utils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * Created by zhangbokang on 2017/5/14.
 */
public class GeneratorNumberId implements IdentifierGenerator {
    @Override
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        return System.currentTimeMillis()/1000;
    }
}
