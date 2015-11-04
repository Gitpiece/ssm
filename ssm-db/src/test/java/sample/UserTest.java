package sample;

import org.junit.Test;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by admin on 2015/10/27.
 */
public class UserTest {

    @Test
    public void testTable(){
        Class clazz = User.class;
        while(true){
            if(clazz == null) break;
            System.out.println("class : " + clazz.getName());
            Collection<Table> coll = getAnnotations(clazz, Table.class);
            clazz = clazz.getSuperclass();
            if (coll == null) continue;
            for(Table t: coll){
//            Iterator<Table> it = coll.iterator();
//            while (it.hasNext()) {
//                Table c = it.next();
                System.out.println("Table : " + t.name());
            }
        }
    }

    @Test
    public void testColumn() {
        Class<User> clazz = User.class;
        printColumn(clazz);

        while (true) {
            Class sclazz = clazz.getSuperclass();
            if (sclazz == null) break;
            printColumn(sclazz);
            clazz = sclazz;
        }

    }

    private void printColumn(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        System.out.println("class : " + clazz.getName());
        System.out.println("files : " + fields.length);
        for (int i = 0; i < fields.length; i++) {
            System.out.println("field : " + fields[i].getName());
            fields[i].setAccessible(true);
            Collection<Column> coll = getAnnotations(fields[i], Column.class);
            if (coll == null) continue;
            Iterator<Column> it = coll.iterator();
            while (it.hasNext()) {
                Column c = it.next();
                System.out.println("column : " + c.name());
            }
        }
    }

    @Test
    public void testID() {
        Collection<Id> coll = getAnnotations(User.class, Id.class);
        Iterator<Id> it = coll.iterator();
        while (it.hasNext()) {
            Id c = it.next();
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

    @Test
    public void object2Xmltest() {
        User user = new User();
        user.setID(12);
        user.setNAME("name");
        user.setAGE(12);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(user, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void xml2ObjectTest() {
        String content = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<user age=\"12\" id=\"12\" name=\"name\"/>";
        StringReader stringReader = new StringReader(content);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            User user = (User) jaxbUnmarshaller.unmarshal(stringReader);
            System.out.println(user);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
