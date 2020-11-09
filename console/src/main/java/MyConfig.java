import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import uni.eszterhazy.keretrendszer.dao.DolgozoDAO;
import uni.eszterhazy.keretrendszer.dao.json.DolgozoDAOJSON;
import uni.eszterhazy.keretrendszer.dao.mongo.DolgozoDAOMongo;
import uni.eszterhazy.keretrendszer.service.DolgozoService;
import uni.eszterhazy.keretrendszer.service.impl.DolgozoServiceImpl;

import java.io.IOException;
import java.net.UnknownHostException;

@Configuration
@ComponentScan({"uni.eszterhazy.keretrendszer.controller"})
public class MyConfig {

    @Bean(name="mongoDBDAO")
    public DolgozoDAO mongodb(){
        try {
            return new DolgozoDAOMongo("mongodb://localhost:27018", "dolgozok","dolgozo");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Qualifier("jsonDBDAO")
    @Bean
    public DolgozoDAO jsondb(){
        try {
            return new DolgozoDAOJSON("Dolgozo.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public DolgozoService dolgozoService(DolgozoDAO mongoDBDAO){
        return new DolgozoServiceImpl(mongoDBDAO);
    }
}
