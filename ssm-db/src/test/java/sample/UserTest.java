package sample;

import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * Created by admin on 2015/10/27.
 */
public class UserTest {

    public <T> T getvalue(Integer i){
        switch (i){
            case 1 :return (T)"string";
            case 2 :return (T)i;
            default:return (T)i;
        }
//        return (T)obj;
    }

    @Test
    public void testValue(){
        String string = this.getvalue(1);
        Integer inti = this.getvalue(2);
        int i = this.getvalue(23);
        System.out.println(string);
        System.out.println(inti);
        System.out.println(i);
    }

    @Test
    public void object2Xmltest(){
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
    public void xml2ObjectTest(){
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
