//package listener;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//import java.sql.DriverManager;
//
//@WebListener
//public class AppContextListener implements ServletContextListener {
//    public void contextDestroyed(ServletContextEvent event)  {
//        try{
//            while(DriverManager.getDrivers().hasMoreElements()){
//                DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    public void contextInitialized(ServletContextEvent event)  {
//    }
//}