package com.quan.demo.framework.spring.aop;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: class ProxyDemo
 * @Author Force丶Oneself
 * @Date 2021-05-29
 **/
public class ProxyDemo {

    public interface Perform {
        void sing();
    }


    public class Performer implements Perform {
        @Override
        public void sing() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("男孩在唱歌");
        }

        public void eat() {
            System.out.println("男孩在吃饭");
        }
    }


    public static void main(String[] args) throws NoSuchMethodException {
        boolean b = testClassMatchExpression("execution(public * foo.Perform.*(..))", Performer.class);
        System.out.println(b);

        b = testClassMatchExpression("execution(public * foo.Perform.*(..))", ProxyDemo.class);
        System.out.println(b);

        b = testClassMatchExpression("execution(public * foo.Perform.*(..))", Perform.class);
        System.out.println(b);

        Method sing = Perform.class.getMethod("sing");
        b = testMethodMatchExpression("execution(public * *.*.sing(..))",sing);
        System.out.println(b);
    }

    /**
     * 测试class匹配
     * @param expression
     * @param clazzToBeTest
     * @return
     */
    public static boolean testClassMatchExpression(String expression, Class<?> clazzToBeTest) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        PointcutExpression pointcutExpression = buildPointcutExpression(classLoader, expression);
        boolean b = pointcutExpression.couldMatchJoinPointsInType(clazzToBeTest);
        return b;
    }

    /**
     * 测试方法匹配
     * @param expression
     * @return
     */
    public static boolean testMethodMatchExpression(String expression, Method targetMethod) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        PointcutExpression pointcutExpression = buildPointcutExpression(classLoader, expression);
        ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(targetMethod);
        if (shadowMatch.alwaysMatches()) {
            return true;
        } else if (shadowMatch.neverMatches()) {
            return false;
        } else if (shadowMatch.maybeMatches()) {
            System.out.println("可能匹配");
        }
        return false;
    }

    /**
     * Build the underlying AspectJ pointcut expression.
     */
    private static PointcutExpression buildPointcutExpression(ClassLoader classLoader, String expression) {
        PointcutParser parser = initializePointcutParser(classLoader);
        return parser.parsePointcutExpression(expression);
    }

    /**
     * Initialize the underlying AspectJ pointcut parser.
     */
    private static PointcutParser initializePointcutParser(ClassLoader cl) {
        return PointcutParser
                .getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(
                        SUPPORTED_PRIMITIVES, cl);
    }

    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();

    static {
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
    }

}
