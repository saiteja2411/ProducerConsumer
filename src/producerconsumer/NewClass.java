package producerconsumer;

public class NewClass {
    static int i=0;
    static{
        System.out.println("static block test newclass");
    }
    public NewClass() throws InterruptedException {
        System.out.println("producerconsumer.NewClass.<init>()");
        Shared s = new Shared();
        //System.out.println(s.getClass().getFields());
        
        ThreadDemo td1= new ThreadDemo(s);
        ThreadDemo2 td2= new ThreadDemo2(s);
        //td1.start();
        //td2.start();
        
        Thread.sleep(10000);
       s.flag=false;
        
 
}
    
    interface Imatch{
        void addVal();
    }
}
    
class ThreadDemo extends Thread implements NewClass.Imatch {

    Shared s1;
    volatile boolean flag=true;
    public ThreadDemo(Shared c) {
        s1=c;
    }
    
    public void run(){
        if(flag==true){
        try{
            s1.producer();
        }
        catch(Exception e){
            
        }
        }
    }

    @Override
    public void addVal() {
        System.out.println("in Imatch interface in addVal ");
        flag=false;
    }
}

class ThreadDemo2 extends Thread implements NewClass.Imatch{

    Shared s2;
    volatile boolean flag=true;
    public ThreadDemo2(Shared c) {
        s2=c;
    }
    public void run(){
        if(flag){
        try{
            s2.consumer();
        }
        catch(Exception e){
            
        }
        }
    }
    
    public void addVal() {
        System.out.println("in Imatch interface in addVal ");
        flag=false;
        }
}
