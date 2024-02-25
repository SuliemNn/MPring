package org.myspringframework.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

public class DefaultResourceLoader implements org.myspringframework.core.io.ResourceLoader {

    @Override
    public org.myspringframework.core.io.Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null");
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new org.myspringframework.core.io.ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }
        else {
            try {
                //尝试是否能从网络上获取
                URL url = new URL(location);
                return new org.myspringframework.core.io.UrlResource(url);
            } catch (MalformedURLException e) {
                //不能则尝试取文件系统中获取
                return new org.myspringframework.core.io.FileSystemResource(location);
            }
        }
    }

}
