package com.goribun.navie.client.context;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.goribun.navie.core.annotation.RpcService;
import com.goribun.navie.core.utils.PropertyUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * 服务管理
 *
 * @author wangxuesong
 */
public class ServiceManager {

    private final Map<Class<?>, ServiceInfo> serviceMap = new HashMap<>();

    private static final ServiceManager INSTANCE = new ServiceManager();


    private ServiceManager() {

    }

    public static ServiceManager getInstance() {
        return INSTANCE;
    }

    public void load() {
        try {
            if (loadLib() == 0) {
                loadClassPath();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadClassPath() throws IOException, ClassNotFoundException {
        String paths = System.getProperty("java.class.path");
        String[] array = paths.split("\\" + File.pathSeparator);

        for (String path : array) {
            File jarFile = new File(path);
            if (path.endsWith(".jar")) {
                include(jarFile);

            }
        }
    }

    private int loadLib() throws IOException, ClassNotFoundException {
        URL location = ServiceManager.class.getProtectionDomain().getCodeSource().getLocation();
        String filePath = location.getPath();
        if (filePath.endsWith(".jar")) {
            // 截取路径中的jar包名
            filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        }
        File file = new File(filePath);
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return 0;
        }

        int loadCnt = 0;
        for (File jarFile : listFiles) {
            include(jarFile);
            loadCnt++;
        }
        return loadCnt;
    }


    /**
     * 获取Rpc服务元数据
     *
     */
    public ServiceInfo getServiceInfo(Class<?> clazz) {
        return serviceMap.get(clazz);
    }

    private void include(File file) throws IOException, ClassNotFoundException {

        String jarFileName = file.getName();
        String[] strs = jarFileName.split("-rpc-client-");
        if (strs.length != 2) {
            return;
        }

        JarFile jarFile = new JarFile(file);

        Enumeration<JarEntry> entrys = jarFile.entries();

        while (entrys.hasMoreElements()) {
            JarEntry jarEntry = entrys.nextElement();
            String entryName = jarEntry.getName();

            if (entryName.endsWith(".class")) {
                String className = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
                Class<?> clazz = Class.forName(className);
                if (clazz != null && clazz.getAnnotation(RpcService.class) != null) {
                    RpcService rpcService = clazz.getAnnotation(RpcService.class);
                    //服务元数据
                    ServiceInfo serviceInfo = new ServiceInfo();
                    serviceInfo.setAppName(strs[0]);
                    serviceInfo.setUrl(this.getUrlByAppName(strs[0]));

                    serviceInfo.setClazz(clazz);
                    //自定义路径
                    if (StringUtils.isNotBlank(rpcService.value())) {
                        serviceInfo.setServiceName(rpcService.value());
                    } else {
                        serviceInfo.setServiceName(clazz.getName());
                    }
                    serviceMap.put(clazz, serviceInfo);
                }
            }
        }
    }

    //根据项目名取得请求地址
    private String getUrlByAppName(String appName) {
        return PropertyUtil.getProperty(appName);
    }

}
