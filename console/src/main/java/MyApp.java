import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import uni.eszterhazy.keretrendszer.controller.DolgozoController;
import uni.eszterhazy.keretrendszer.dao.DolgozoDAO;
import uni.eszterhazy.keretrendszer.service.DolgozoService;

public class MyApp {
    public static void main(String[] args){
      /*  ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        DolgozoDAO dao = (DolgozoDAO) context.getBean("mongodb");
        System.out.println(dao.readAllDolgozo());
       DolgozoService service = (DolgozoService) context.getBean("dolgozoService");
        System.out.println(service.getAllDolgozo());
        DolgozoController controller = (DolgozoController) context.getBean("dolgozoCont");
        controller.printAll();*/

        ApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");
        DolgozoService service = (DolgozoService) context.getBean("dolgozoService");
        System.out.println(service.getAllDolgozo());
    }
}
