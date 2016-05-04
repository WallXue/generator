package org.mybatis.generator.ext.codegen;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;

import java.lang.reflect.Method;

/**
 */
public class IntrospectedTableDecorator extends IntrospectedTableMyBatis3Impl implements MethodInterceptor {

    private IntrospectedTableMyBatis3Impl target;

    public static IntrospectedTableMyBatis3Impl newProxyInstance(IntrospectedTableMyBatis3Impl target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(IntrospectedTableMyBatis3Impl.class);
        IntrospectedTableDecorator introspectedTableDecorator = new IntrospectedTableDecorator(target);
        enhancer.setCallback(introspectedTableDecorator);
        return (IntrospectedTableMyBatis3Impl)enhancer.create() ;
    }

    public Object intercept(Object obj, Method method, Object[] args,
                            MethodProxy proxy) throws Throwable {
//        if (isDelegateMethod(method)) {
//            return method.invoke(this, args);
//        }

        if (method.getName().equals("getDAOImplementationType") || method.getName().equals("getDAOInterfaceType")) {
            return method.invoke(this, args);
        }

        return method.invoke(target, args); //return proxy.invokeSuper(obj, args);
    }

    private IntrospectedTableDecorator(IntrospectedTableMyBatis3Impl introspectedTableMyBatis3) {
        this.target = introspectedTableMyBatis3;
    }

    public String getDAOImplementationType() {
        String daoImpl =  target.getDAOImplementationType();
        if (!daoImpl.contains(".impl.")) {
            int index = daoImpl.lastIndexOf(".");
            daoImpl = daoImpl.substring(0, index) + ".impl." + daoImpl.substring(index + 1);
            target.setDAOImplementationType(daoImpl);
        }

        return daoImpl;
    }

    public String getDAOInterfaceType() {
        String daoIntf =  target.getDAOInterfaceType();
        if (!daoIntf.contains(".intf.")) {
            int index = daoIntf.lastIndexOf(".");
            daoIntf = daoIntf.substring(0, index) + ".impl." + daoIntf.substring(index + 1);
            target.setDAOImplementationType(daoIntf);
        }

        return daoIntf;
    }

    private boolean isDelegateMethod(Method method) {
        if (method.getName().equals("getDAOImplementationType") || method.getName().equals("getDAOInterfaceType")) {
            return true;
        }
        return false;
    }


//    @Override
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        if (method.getName().equals("getExtDaoIntfType") || method.getName().equals("getExtDaoImplType")) {
//            return method.invoke(this, args);
//        }
//        return method.invoke(this.introspectedTable, args);
//    }
//        return (IntrospectedTable) Proxy
//                .newProxyInstance(IntrospectedTableDecorator.class.getClassLoader(),
//                        new Class<?>[] { IntrospectedTable.class },
//                        new IntrospectedTableDecorator(target));
//        new Callback[]{new MethodInterceptor() {
//            @Override
//            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
//                // only common method will intercept this call back.
//                return proxy.invoke(this, args);
//            }
//        }, NoOp.INSTANCE});//

}
