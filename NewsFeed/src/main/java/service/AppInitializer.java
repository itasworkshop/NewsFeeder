package service;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppInitializer implements ServletContextListener {
   
   @Resource(mappedName = "concurrent/MyFeed")
   private ManagedScheduledExecutorService executor;
   
   @Inject
   private FeedReader reader;

   @Override
   public void contextInitialized(ServletContextEvent sce) {
       System.out.println("---- app starting");
       ScheduledFuture future = executor.scheduleAtFixedRate(reader, 5, 5, TimeUnit.SECONDS);
       sce.getServletContext().setAttribute("reader", future);
   }

   @Override
   public void contextDestroyed(ServletContextEvent sce) {
       System.out.println("---- app shutting down");
       ScheduledFuture future = (ScheduledFuture)sce.getServletContext().getAttribute("reader");
       future.cancel(true);        
   }
   
}