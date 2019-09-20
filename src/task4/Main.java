package task4;

import java.lang.annotation.Annotation;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class cl1 = MyClassWithAnnotation.class;
        Class cl2 = MyClassWithoutAnnotation.class;

        Class[] classes = {cl1, cl2};
        for (Class c : classes){
            Annotation[] a = c.getAnnotations();
            if (a.length > 0) {
                if (a[0] instanceof MyAnnotation) {
                    Object o = c.newInstance();
                    System.out.println(o);
                }
            }
        }
    }
}
