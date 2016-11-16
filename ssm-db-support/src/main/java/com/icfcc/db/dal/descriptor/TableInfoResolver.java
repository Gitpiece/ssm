package com.icfcc.db.dal.descriptor;

import java.util.Collection;
import java.util.Iterator;

import static me.pinenut.util.AnnotationUtil.getAnnotations;

/**
 * 从法注解中获取表信息
 * Created by admin on 2015/11/4.
 */
public class TableInfoResolver {

    @SuppressWarnings("rawtypes")
    public static String resolverTable(Class<?> oClass) {
        Class clazz = oClass;
        String table = null;
        while (true) {
            if (clazz == null) break;
            Collection<javax.persistence.Table> coll = getAnnotations(clazz, javax.persistence.Table.class);
            clazz = clazz.getSuperclass();
            if (coll == null) continue;
            Iterator<javax.persistence.Table> it = coll.iterator();
            while (it.hasNext()) {
                javax.persistence.Table c = it.next();
                table = c.name().toLowerCase().trim();
                break;
            }
        }

        if (table == null) {
            String classname = oClass.getName();
            table = classname.substring(classname.lastIndexOf(".") + 1).toLowerCase().trim();
        }

        return table;
    }

    public static Collection<Column> resolverTableColumns(Class<?> userClass) {
        throw new UnsupportedOperationException();
    }

//    private <T extends Annotation> Collection<T> getAnnotations(AnnotatedElement ae, Class<T> annotationType) {
//        Collection<T> anns = new ArrayList<T>(2);
//
//        // look at raw annotation
//        T ann = ae.getAnnotation(annotationType);
//        if (ann != null) {
//            anns.add(ann);
//        }
//
//        // scan meta-annotations
//        for (Annotation metaAnn : ae.getAnnotations()) {
//            ann = metaAnn.annotationType().getAnnotation(annotationType);
//            if (ann != null) {
//                anns.add(ann);
//            }
//        }
//
//        return (anns.isEmpty() ? null : anns);
//    }
}
