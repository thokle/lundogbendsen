import ratpack.server.RatpackServer;

/**
 * Created by thokle on 27/10/2016.
 */
public class helloStarter {

    public  static void main(String ... args) throws  Exception{
        RatpackServer.start(ratpackServerSpec -> ratpackServerSpec.handlers( as -> {
            as.get("",a-> { a.render("HELLO WORLD");});

        }));


    }

}
