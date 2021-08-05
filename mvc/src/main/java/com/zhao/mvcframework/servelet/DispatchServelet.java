package com.zhao.mvcframework.servelet;

import com.zhao.mvcframework.annotation.Autowired;
import com.zhao.mvcframework.annotation.Controller;
import com.zhao.mvcframework.annotation.RequestMapping;
import com.zhao.mvcframework.annotation.Service;
import com.zhao.mvcframework.pojo.Handler;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DispatchServelet extends HttpServlet {
    private Properties properties = new Properties();
    private List<String> classNames = new ArrayList<>();
    private Map<String, Object> ioc = new HashMap<>();
    private List<Handler> handlerMapping = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        //加载配置文件
        String contextConfigLocation = config.getInitParameter("contextConfigLocation");
        doLoadConfig(contextConfigLocation);
        //扫描相关的类，扫描注解
        doScan(properties.getProperty("scanPackage"));
        //初始化bean,基于注解
        doInstance();
        //实现依赖注入
        doAutoWired();
        //实现处理器映射器，将url和method进行关联
        initHandlerMapping();
        System.out.println("mvc 初始化完成");

    }

    //执行的是方法和url方法映射
    private void initHandlerMapping() {
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> aClass = entry.getValue().getClass();
            if (!aClass.isAnnotationPresent(Controller.class)) {
                continue;
            }
            String baseUrl = "";
            if (aClass.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping requestMapping = aClass.getAnnotation(RequestMapping.class);
                baseUrl = requestMapping.value();
            }
            Method[] methods = aClass.getMethods();
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];
                if (!method.isAnnotationPresent(RequestMapping.class)) {
                    continue;
                }
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                String methodUrl = requestMapping.value();
                String url = baseUrl + methodUrl;
                Handler handler = new Handler(entry.getValue(), method, Pattern.compile(url));
                Parameter[] parameters = method.getParameters();
                for (int j = 0; j < parameters.length; j++) {
                    Parameter parameter = parameters[j];
                    if (parameter.getType().equals(HttpServletRequest.class) || parameter.getType().equals(HttpServletResponse.class)) {
                        handler.getParamIndexMapping().put(parameter.getType().getSimpleName(), j);
                    } else {
                        handler.getParamIndexMapping().put(parameter.getName(), j);
                    }
                }
                //完成方法和url的映射关系
                handlerMapping.add(handler);
            }
        }
    }

    //执行注入部分，同样是做的ioc的部分功能
    private void doAutoWired() {
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Field[] declareFields = entry.getValue().getClass().getDeclaredFields();
            for (int i = 0; i < declareFields.length; i++) {
                Field declareField = declareFields[i];
                if (!declareField.isAnnotationPresent(Autowired.class)) {
                    continue;
                }
                Autowired autowired = declareField.getAnnotation(Autowired.class);
                String beanName = autowired.value();
                if ("".equals(beanName.trim())) {
                    beanName = declareField.getType().getName();
                }
                declareField.setAccessible(true);
                try {
                    //直接将这个字段的值设置为ioc中已经示例化的类，
                    // 即是完成了ioc中的实例化交给容器来管理的情况
                    declareField.set(entry.getValue(), ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    //执行的是符合要求的类的初始化，实际上是实现的一部分ioc的功能
    private void doInstance() {
        if (classNames.size() == 0) {
            return;
        }
        try {
            for (int i = 0; i < classNames.size(); i++) {
                String className = classNames.get(i);
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(Controller.class)) {
                    String simpleName = clazz.getSimpleName();
                    String lowerFirst = lowerFirst(simpleName);
                    Object o = clazz.newInstance();
                    //因为controller无别名，所以简单设置成首字母小写就行
                    ioc.put(lowerFirst, o);
                } else if (clazz.isAnnotationPresent(Service.class)) {
                    Service service = clazz.getAnnotation(Service.class);
                    String beanName = service.value();
                    if (!"".equals(beanName.trim())) {
                        ioc.put(beanName, clazz.newInstance());
                    } else {
                        beanName = lowerFirst(clazz.getSimpleName());
                        ioc.put(beanName, clazz.newInstance());
                    }

                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (int j = 0; j < interfaces.length; j++) {
                        Class<?> ainterface = interfaces[j];
                        System.out.println(ainterface.getName());
                        //将实现类和接口进行绑定
                        ioc.put(ainterface.getName(), clazz.newInstance());
                    }
                } else {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private String lowerFirst(String className) {
        char[] chars = className.toCharArray();
        if ('A' < chars[0] && chars[0] < 'Z') {
            chars[0] += 32;
        }
        return new String(chars);
    }

    private void doScan(String basePackage) {
        //获取到指定包下的所有类的类名
        String scanPackagePath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + basePackage.replaceAll("\\.", "/");
        File pack = new File(scanPackagePath);
        File[] files = pack.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                doScan(basePackage + "." + file.getName());
            } else if (file.getName().endsWith(".class")) {
                String className = basePackage + "." + file.getName().replaceAll(".class", "");
                classNames.add(className);
            }
        }
    }

    //实现加载web.xml中配置的文件的路径
    private void doLoadConfig(String contextConfigLocation) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Handler handler = getHander(req);
        if (handler == null) {
            resp.getWriter().write("404 not found");
            return;
        }
        Class<?>[] parameterTypes = handler.getMethod().getParameterTypes();
        Object[] paraValues = new Object[parameterTypes.length];

        Map<String, String[]> parameterMap = req.getParameterMap();
        for (Map.Entry<String, String[]> param : parameterMap.entrySet()) {
            String value = StringUtils.join(param.getValue(), ",");
            if (!handler.getParamIndexMapping().containsKey(param.getKey())) {
                continue;
            }
            //对应实际参数的位置
            Integer index = handler.getParamIndexMapping().get(param.getKey());
            paraValues[index] = value;

        }
        //对应上req,和resp参数的位置
        int reqIndex = handler.getParamIndexMapping().get(HttpServletRequest.class.getSimpleName());
        paraValues[reqIndex] = req;
        int respIndex = handler.getParamIndexMapping().get(HttpServletResponse.class.getSimpleName());
        paraValues[respIndex] = resp;

        try {
            //实际执行的是controller中的方法
            handler.getMethod().invoke(handler.getController(), paraValues);
            System.out.println("执行controller方法成功");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private Handler getHander(HttpServletRequest req) {
        if (handlerMapping.isEmpty()) {
            return null;
        }
        String url = req.getRequestURI();
        for (Handler handler : handlerMapping) {
            Matcher matcher = handler.getPattern().matcher(url);
            if (!matcher.matches()) {
                continue;
            }
            return handler;
        }
        return null;
    }

}
