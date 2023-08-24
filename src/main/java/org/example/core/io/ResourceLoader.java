package org.example.core.io;

/**
 * 支持解析资源表达式，并获取该资源的inputStream.
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
