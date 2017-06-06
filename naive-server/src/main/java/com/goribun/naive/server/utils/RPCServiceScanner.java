package com.goribun.naive.server.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.goribun.naive.core.annotation.RpcService;
import com.goribun.naive.core.constants.Const;
import com.goribun.naive.core.utils.PropertyUtil;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.SystemPropertyUtils;

/**
 * 类扫描工具
 * <p>
 * 可以指定包路径和注解
 *
 * @author wangxuesong
 * @version 1.0
 */
public class RPCServiceScanner {

    private RPCServiceScanner() {
    }

    //默认包扫描路径
    private static final String DEFAULT_SCAN_PACKAGE = "cn.wangxs.crm.service.*";

    public static List<Class<?>> scan() {

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory readerFactory = new SimpleMetadataReaderFactory();

        //优先取得自定义配置
        String pkgString = PropertyUtil.getProperty(Const.SCAN_PACKAGE_KEY_, DEFAULT_SCAN_PACKAGE);

        List<Class<?>> list = new ArrayList<>();
        //多个路径拆分成路径数组
        String[] pkgArray = StringUtils.tokenizeToStringArray(pkgString, ConfigurableApplicationContext
                .CONFIG_LOCATION_DELIMITERS);

        for (String pkg : pkgArray) {
            //类名转资源路径
            String resourcePath = ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils
                    .resolvePlaceholders(pkg)) + ".class";

            try {
                //根据资源路径取得资源数组
                Resource[] resources = resolver.getResources(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                        + resourcePath);

                for (Resource resource : resources) {
                    if (resource.isReadable()) {
                        MetadataReader reader = readerFactory.getMetadataReader(resource);
                        String className = reader.getClassMetadata().getClassName();
                        Class<?> clazz = null;
                        try {
                            clazz = Class.forName(className);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        //判断是否带有RpcServer注解
                        if (clazz != null && clazz.getAnnotation(RpcService.class) != null) {
                            list.add(clazz);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}
