package ru.job4j.serialization.java;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "privateSchool")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrivateSchool {

    @XmlAttribute
    private boolean category;
    @XmlAttribute
    private int age;
    private String name;
    private Professor professor;
    @XmlElementWrapper(name = "scholars")
    @XmlElement(name = "scholar")
    private String[] scholars;

    public PrivateSchool() {
    }

    public PrivateSchool(boolean category, int age, String name, Professor professor, String[] scholars) {
        this.category = category;
        this.age = age;
        this.name = name;
        this.professor = professor;
        this.scholars = scholars;
    }

    @Override
    public String toString() {
        return "PrivateSchool{"
                + "category=" + category
                + ", age=" + age
                + ", name=" + name
                + ", professor=" + professor
                + ", scholars=" + Arrays.toString(scholars)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
            final PrivateSchool privateSchool = new PrivateSchool(true, 32,
                    "Школа сотрудничества", new Professor(35, "Alexey"),
                    new String[]{"German", "Vladimir", "Victoria"});

            JAXBContext context = JAXBContext.newInstance(PrivateSchool.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            String xml = "";
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(privateSchool, writer);
                xml = writer.getBuffer().toString();
                System.out.println(xml);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Unmarshaller unmarshaller = context.createUnmarshaller();
            try (StringReader reader = new StringReader(xml)) {
                 /* десериализуем */
                 PrivateSchool result = (PrivateSchool) unmarshaller.unmarshal(reader);
                 System.out.println(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
