package org.example.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

public class DefaultResourceLoader implements ResourceLoader {
    @Override
    public Resource getResource(String location) {
        Assert.notEmpty(location, "Location must not be empty.");

        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            // 需要从classpath下获取资源
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {

            try {
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }
    }

}
