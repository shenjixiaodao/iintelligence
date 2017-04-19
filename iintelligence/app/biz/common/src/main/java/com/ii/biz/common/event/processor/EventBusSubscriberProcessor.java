package com.ii.biz.common.event.processor;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class EventBusSubscriberProcessor implements BeanPostProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(DeadEventProcessor.class);

    @Autowired
    private EventBus            eventBus;

    @Override
    public Object postProcessAfterInitialization(Object beanObject,
                                                 String beanObjectName) throws BeansException {
        return beanObject;
    }

    @Override
    public Object postProcessBeforeInitialization(Object beanObject,
                                                  String beanObjectName) throws BeansException {

        Method[] methods = beanObject.getClass().getMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();

            for (Annotation methodAnnotation : annotations) {
                if (methodAnnotation.annotationType().equals(Subscribe.class)) {
                    eventBus.register(beanObject);
                    LOGGER.info("Bean {} method {} has been subscribed to the EventBus.",
                        new Object[] { beanObjectName, method.getName() });
                    return beanObject;
                }
            }
        }

        return beanObject;
    }

}