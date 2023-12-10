package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class WayneEnterprises {
    static Queue<Integer> qAtlanta = new LinkedList<>();
    static Queue<Integer> qGotham = new LinkedList<>();
    final static Object atlantaKey = new Object();
    final static Object gothamKey = new Object();
    static int atlantaTimedOut = 0 ;
    static int gothamTimedOut = 0 ;
    static int moneyEarned = 0 ;
    static int atlantaCargo = 0 ;
    static int gothamCargo = 0 ;
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ExecutorService taskAssignService = Executors.newFixedThreadPool(7);

        Future<?> atlantafuture = taskAssignService.submit(() -> {
            while (moneyEarned <= 1000000) {
               Future<?> future =  executorService.submit(new Atlanta());
                if (moneyEarned >= 1000000) {
                    System.out.println("Money Earned as per Quota Atlanta Shipping stopped!!!");
                    //executorService.shutdownNow();
                    break;
                }
               try {
                   future.get(36, TimeUnit.SECONDS);
               }
               catch (TimeoutException t){
                   System.out.println("Timed Out for a Atlanta task!!!!");
                   atlantaTimedOut+=1 ;
               } catch (ExecutionException |InterruptedException e) {

               }finally {
                   if (atlantaTimedOut==3){
                       moneyEarned-=250 ;
                       atlantaTimedOut = 0;
                       future.cancel(true);
                   }
               }
            }
            executorService.shutdownNow();
        });
       Future<?> gothamFuture = taskAssignService.submit(() ->{
            while(moneyEarned<=1000000) {
                Future<?> future = executorService.submit(new Gotham());
                if (moneyEarned >= 1000000) {
                    System.out.println("Money Earned as per Quota Gotham Shipping stopped!!!");
                    //executorService.shutdownNow();
                    break;
                }
                try {
                    future.get(36,TimeUnit.SECONDS);//After 36 seconds the customer will cancel its order after waiting
                }catch(TimeoutException t){
                    System.out.println("Timed Out for Gotham  task!!!");
                    gothamTimedOut+=1;
                } catch (ExecutionException | InterruptedException e) {

                }finally {
                    if (gothamTimedOut==3){
                        moneyEarned-=250;
                        gothamTimedOut=0;
                        future.cancel(true);
                    }
                }
            }
            executorService.shutdownNow();
           System.out.println("\n\nTotal Money Earned: $"+moneyEarned+"USD");
        });
       /* if (moneyEarned>=30000){
            executorService.shutdownNow();
            taskAssignService.shutdownNow();
        }*/
        taskAssignService.shutdownNow();

    }

    static class Atlanta implements Runnable{
        public int temp = 0 ;
        @Override
        public void run() {
            int atlantaCount = 0;
            QueueAtlanta();
            synchronized (atlantaKey){
                while(!Thread.interrupted()){
                    try {
                        //System.out.println("Shipped: " + qAtlanta.remove());
                        atlantaCount++;
                        moneyEarned+=(1000*temp);
                        System.out.println("Atlanta has Shipped "+atlantaCargo+ " ton of Orders.");
                        atlantaCargo = 0 ;
                        temp = 0;
                        QueueAtlanta();
                        System.out.println("Revenue: "+moneyEarned);

                        if (atlantaCount == 5){
                            System.out.println("Atlanta Ship is under Maintenance!!!");
                            Thread.sleep(60000);
                            atlantaCount = 0 ;
                        }
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e){
                       // System.out.println("Quota Completed");
                        break;
                    }
                    //System.out.println("Money Earned By Atlanta:"+moneyEarned);
                }
            }
        }

        private void QueueAtlanta(){
            Random random = new Random();
            for (int i = 0 ;i<100;i++){
                qAtlanta.add(10+random.nextInt(50));
            }
            while(atlantaCargo<=230){
                atlantaCargo+=qAtlanta.remove();
                temp++;
            }
            if (qAtlanta.isEmpty()){
                QueueAtlanta();
            }
        }
    }

    static class Gotham implements Runnable{
        public int temp = 0 ;
        @Override
        public void run() {
            int gothamCount = 0;
            QueueGotham();
            synchronized (gothamKey){
                while(!Thread.interrupted()){
                    try {
                        //System.out.println("Shipped: " + qGotham.remove());
                        gothamCount++;
                        moneyEarned+=(1000*temp);
                        System.out.println("Gotham has Shipped "+gothamCargo+ " tons of Orders.");
                        gothamCargo=0;
                        temp = 0 ;
                        QueueGotham();
                        System.out.println("Revenue: "+moneyEarned);
                        //System.out.println("Money Earned is:"+moneyEarned);
                        //gothamCount will keep in check how many times the thread havr worked and after 5 rounds it will take rest for 1 min
                        if (gothamCount == 5){
                            System.out.println("Gotham Ship is under Maintenance!!!!");
                            Thread.sleep(60000);
                            gothamCount = 0 ;

                        }
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e){
                        //System.out.println("Quota Completed");

                        break;//adding break in catch statement so that threads stops running
                    }
                    //System.out.println("Money Earned is By Gotham: "+moneyEarned);
                }
            }
        }
        private void QueueGotham(){
            Random random = new Random();
            for (int i = 0 ;i<100;i++){
                qGotham.add(10+random.nextInt(50));
            }
            while(gothamCargo<=230){
                gothamCargo+=qGotham.remove();
                temp++;
            }
            if (qGotham.isEmpty()){
                QueueGotham();
            }
        }
    }
}