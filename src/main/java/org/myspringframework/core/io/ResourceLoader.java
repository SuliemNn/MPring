package org.myspringframework.core.io;

public interface ResourceLoader {

    /**
     * Pseudo URL prefix for loading from the class path: "classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    org.myspringframework.core.io.Resource getResource(String location);

}
