package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {

    @DataProvider
    public Iterator<Object[]> example(){
        List<Object[]> list = new ArrayList<>();
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        //read from file --> add to list
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/Data.csv.csv")));
        String line = reader.readLine();
        while (line != null){
            String[] all = line.split(",");
            list.add(new Object[]{new User().setEmail(all[0]).setPassword(all[1])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginData(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"ssa@gmail.com", "Ssa12345$"});
        list.add(new Object[]{"ssa@gmail.com", "Ssa12345$"});
        return list.iterator();
    }


    @DataProvider
    public Iterator<Object[]> loginModels(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().setEmail("ssa@gmail.com").setPassword("Ssa12345$")});
        list.add(new Object[]{new User().setEmail("ssa@gmail.com").setPassword("Ssa12345$")});
        return list.iterator();
    }
}
