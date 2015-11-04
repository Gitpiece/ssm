package com.icfcc.db.sys;

import org.junit.Test;
import sample.User;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by admin on 2015/11/4.
 */
public class SmMenuTest {

    @Test
    public void testTable(){
        Collection<Table> coll = getAnnotations(SmMenu.class, Table.class);
//        Iterator<Table> it = coll.iterator();
        for(Table table:coll){
//        while (it.hasNext()){
//            Table table = it.next();
            System.out.println(table.name());
        }
    }

    @Test
    public void testColumn(){

        Collection<Column> coll = getAnnotations(User.class, Column.class);
//        Iterator<Column> it = coll.iterator();
        for(Column c : coll){
//        while (it.hasNext()){
//            Column c = it.next();
            System.out.println(c.name());
        }
    }

    @Test
    public void testID(){
        Collection<Id> coll = getAnnotations(User.class, Id.class);
//        Iterator<Id> it = coll.iterator();
        for(Id c : coll){
//        while (it.hasNext()){
//            Id c = it.next();
            System.out.println(c);
        }
    }
    private <T extends Annotation> Collection<T> getAnnotations(AnnotatedElement ae, Class<T> annotationType) {
        Collection<T> anns = new ArrayList<>(2);

        // look at raw annotation
        T ann = ae.getAnnotation(annotationType);
        if (ann != null) {
            anns.add(ann);
        }

        // scan meta-annotations
        for (Annotation metaAnn : ae.getAnnotations()) {
            ann = metaAnn.annotationType().getAnnotation(annotationType);
            if (ann != null) {
                anns.add(ann);
            }
        }

        return (anns.isEmpty() ? null : anns);
    }
}
