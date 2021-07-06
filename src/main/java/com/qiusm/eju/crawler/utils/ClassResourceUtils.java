package com.qiusm.eju.crawler.utils;

import com.qiusm.eju.crawler.exception.SystemException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qiushengming
 */
public class ClassResourceUtils {
    private static final String RESOURCE_PATTERN = "/**/*.class";

    public static List<Class<?>> scanPackages(TypeFilter[] entityTypeFilters, String... packagesToScan) {
        List<Class<?>> classes = new ArrayList<>();
        try {
            for (String pkg : packagesToScan) {
                String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                        + ClassUtils.convertClassNameToResourcePath(pkg) + RESOURCE_PATTERN;
                ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
                Resource[] resources = resourcePatternResolver.getResources(pattern);

                MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
                for (Resource resource : resources) {
                    if (resource.isReadable()) {
                        MetadataReader reader = readerFactory.getMetadataReader(resource);
                        String className = reader.getClassMetadata().getClassName();
                        if (matchesFilter(reader, readerFactory, entityTypeFilters)) {
                            Class<?> var = resourcePatternResolver.getClassLoader().loadClass(className);
                            classes.add(var);
                        }
                    }
                }
            }
        } catch (IOException ex) {
            throw new SystemException("Failed to scan classpath for unlisted classes", ex);
        } catch (ClassNotFoundException ex) {
            throw new SystemException("Failed to load annotated classes from classpath", ex);
        } catch (Exception ex) {
            throw new SystemException("Failed to addAnnotatedClass", ex);
        }

        return classes;
    }

    private static boolean matchesFilter(MetadataReader reader, MetadataReaderFactory readerFactory, TypeFilter[] entityTypeFilters) throws IOException {
        for (TypeFilter filter : entityTypeFilters) {
            if (filter.match(reader, readerFactory)) {
                return true;
            }
        }
        return false;
    }
}
