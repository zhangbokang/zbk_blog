package com.zbkblog.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class BaseCacheKey implements Serializable {
    private static final long serialVersionUID = -1651889717223143579L;
    private static final Logger logger = LoggerFactory.getLogger(BaseCacheKey.class);
    private final Object[] params;
    private final int hashCode;
    private final String className;
    private final String methodName;
    public BaseCacheKey(Object target, Method method, Object[] elements){
        this.className=target.getClass().getName();
        this.methodName=getMethodName(method);
        this.params = new Object[elements.length];
        System.arraycopy(elements, 0, this.params, 0, elements.length);
        this.hashCode=generatorHashCode();
    }
    private String getMethodName(Method method){
        StringBuilder builder = new StringBuilder(method.getName());
        Class<?>[] types = method.getParameterTypes();
        if(types.length!=0){
            builder.append("(");
            for(Class<?> type:types){
                String name = type.getName();
                builder.append(name+",");
            }
            builder.append(")");
        }
        return builder.toString();
    }
    @Override
    public boolean equals(Object obj){
        if(this==obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseCacheKey o=(BaseCacheKey) obj;
        if(this.hashCode!=o.hashCode())
            return false;
        if(!Optional.ofNullable(o.className).orElse("").equals(this.className))
            return false;
        if(!Optional.ofNullable(o.methodName).orElse("").equals(this.methodName))
            return false;
        if (!Arrays.equals(params, o.params))
            return false;
        return true;
    }
    @Override
    public final int hashCode() {
        return hashCode;
    }
    private int generatorHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + hashCode;
        result = prime * result + ((methodName == null) ? 0 : methodName.hashCode());
        result = prime * result + Arrays.deepHashCode(params);
        result = prime * result + ((className == null) ? 0 : className.hashCode());
        return result;
    }
    @Override
    public String toString() {
        logger.debug(Arrays.toString(params));
        logger.debug(Arrays.deepToString(params));
        return "BaseCacheKey [params=" + Arrays.deepToString(params) + ", className=" + className + ", methodName="
                + methodName + "]";
    }
}