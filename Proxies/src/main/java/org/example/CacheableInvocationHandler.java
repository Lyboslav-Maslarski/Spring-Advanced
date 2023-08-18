package org.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheableInvocationHandler implements InvocationHandler {
    private final Object realObject;
    private final Map<String, Object> cachedValues = new ConcurrentHashMap<>();

    public CacheableInvocationHandler(Object realObject) {
        this.realObject = realObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Cacheable cacheable = realObject.getClass()
                .getMethod(method.getName(), method.getParameterTypes())
                .getAnnotation(Cacheable.class);

        if (cacheable != null) {
            String cacheId = cacheable.value();
            if (cachedValues.containsKey(cacheId)) {
                return cachedValues.get(cacheId);
            } else {
                Object value = method.invoke(realObject, args);
                cachedValues.put(cacheId, value);
                return value;
            }
        }

        return method.invoke(realObject, args);
    }
}
