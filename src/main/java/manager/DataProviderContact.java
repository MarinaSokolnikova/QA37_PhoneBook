package manager;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    public Iterator<Object[]> contactCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.csv.csv"));
        String line = reader.readLine();
        while (line!= null){
            String[] all = line.split(",");
            list.add(new Object[]{Contact.builder().name(all[0]).lastName(all[1]).email(all[2]).phone(all[3]).address(all[4]).description(all[5]).build()});
            line = reader.readLine();
        }

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
