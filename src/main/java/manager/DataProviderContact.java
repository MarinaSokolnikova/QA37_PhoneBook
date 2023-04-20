package manager;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderContact {
    @DataProvider
    public Iterator<Object[]> example(){
        List<Object[]> list = new ArrayList<>();
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactSuccess(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .name("Bob")
                .lastName("Smit")
                .phone("6471746178678")
                .email("bob@gmail.com")
                .address("Tel Aviv")
                .description("all fields")
                .build()});

        list.add(new Object[]{Contact.builder()
                .name("CrisReq")
                .lastName("Braun")
                .phone("917201736102333")
                .email("cris@gmail.com")
                .address("Rehovot")
                .build()});
        return list.iterator();
    }
}
