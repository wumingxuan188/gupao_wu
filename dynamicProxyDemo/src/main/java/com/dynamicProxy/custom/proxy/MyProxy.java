package com.dynamicProxy.custom.proxy;


import com.dynamicProxy.custom.client.Person;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyProxy {

    private static final String ln = "\r\n";

    public static Object newProxyInstance(com.dynamicProxy.custom.proxy.MyClassLoader classLoader,
                                          Class<?>[] interfaces,
                                          MyInvocationHandler h)
            throws IllegalArgumentException {
       //拼接代码
        String src = generateSrc(interfaces);

        String path = MyProxy.class.getResource("").getPath();

        //编写程java类
        File file = new File(path + "$Proxy2.java");
        try {
            FileWriter wr = new FileWriter(file);
            wr.write(src);
            wr.flush();
            wr.close();

            /**
             * 编译
             */
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
            Iterable iterable = manager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
            task.call();
            manager.close();

//            loader.findClass("$Proxy2.java");
            //加载类
            Class<?> aClass = classLoader.findClass("$Proxy2");

            Constructor<?> constructor = aClass.getConstructor(MyInvocationHandler.class);
            file.delete();
            return constructor.newInstance(h);


        } catch ( NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String s = generateSrc(new Class[]{Person.class});
        System.err.println(s);
    }
    private static String generateSrc(Class<?>[] interfaces) {
        StringBuilder builder = new StringBuilder();
        builder.append("package com.dynamicProxy.custom.proxy;").append(ln );
      // builder.append("import com.dynamicProxy.custom.client;"+ln);
        builder.append("import java.lang.reflect.*;"+ln );
        builder.append("public final class $Proxy2 implements "+interfaces[0].getName()+"{" +ln);
            builder.append("private MyInvocationHandler h;"+ln);
            builder.append("public $Proxy2(MyInvocationHandler h){"+ln);
                builder.append("this.h=h;");
            builder.append("}"+ln);

       //拼接参数
        for (Method m:interfaces[0].getMethods()) {
            Class<?>[] parameterTypes = m.getParameterTypes();

            StringBuffer names = new StringBuffer();
            StringBuffer classes = new StringBuffer();
            StringBuffer values = new StringBuffer();

            for (int i = 0; i < parameterTypes.length; i++) {
                Class<?> parameter = parameterTypes[i];
                String type = parameter.getName();
                String name = toLowerFirstCase(parameter.getSimpleName());
                names.append(type + " " +  name);
                classes.append(name);
                classes.append(parameter.getName() + ".class");
                if(i >= 0 && i < parameterTypes.length-1){
                    names.append(",");
                    classes.append(",");
                    values.append(",");
                }
            }

            builder.append("public final "+m.getReturnType().getName()+" "+m.getName()+"("+names.toString()+"){"+ln);
                builder.append("try{" + ln);
                builder.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + m.getName() + "\",new Class[]{"+classes.toString()+"});" + ln);
               builder.append("this.h.invoke(this,m,new Object[]{"+values+"});" + ln);
                builder.append("}catch(Error _ex) { }"+ln);
                builder.append("catch(Throwable e){" + ln);
                builder.append("throw new UndeclaredThrowableException(e);" + ln);
                builder.append("}"+ln);
            builder.append("}"+ln);
        }

        builder.append("}");
      //  System.err.println(builder.toString());
        return builder.toString();
    }


    private static String toLowerFirstCase(String src){
        char [] chars = src.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }


}
