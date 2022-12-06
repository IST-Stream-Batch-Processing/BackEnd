package cn.ist.lowcoding.streamservice.util;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class CodeGenerate {
    private static final String WRITER_PATH = "./stream-service/src/main/java/cn/ist/lowcoding/streamservice/generateClass/";
    public void javac(String dataName){
        //java编译器
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        //文件管理器，参数1：diagnosticListener  监听器,监听编译过程中出现的错误
        StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
        //java文件转换到java对象，可以是多个文件

        Iterable<? extends JavaFileObject> it = manager.getJavaFileObjects(WRITER_PATH+dataName);
        Iterable<String> options = Arrays.asList("-d", System.getProperty("user.dir")+"/stream-service/src/main/java");
        //编译任务,可以编译多个文件
        JavaCompiler.CompilationTask t = compiler.getTask(null, manager, null, options, null, it);
        //执行任务
        t.call();
        try {
            manager.close();
            System.out.println(WRITER_PATH+"编译成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //动态加载class文件
    public Class<?> java(String packName) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        //1.创建一个File文件对象：路径是存放class文件目录：要保证class的包目录是完整的：可以是压缩包
        File file = new File(System.getProperty("user.dir")+"/src/main/java");
        System.out.println(file);
        //2.获取URL对象
        URL url = file.toURI().toURL();
        System.out.println(url);
        //3.创建URL类加载器
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url});

        //4.通过urlClassLoader加载器调用loadClass方法传入类名动态加载class文件并获取class对象:会初始化静态块
        Class<?> clazz = urlClassLoader.loadClass("cn.ist.lowcoding.streamservice.generateClass."+packName);

        return clazz;
     }
}
