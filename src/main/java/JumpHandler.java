import ratpack.handling.*;

/**
 * Created by thokle on 26/10/2016.
 */
public class JumpHandler implements ratpack.handling.Handler {
    @Override
    public void handle(Context ctx) throws Exception {


      String id =   ctx.getPathTokens().get("id");


        if(id.equals("2")){
            ctx.insert(new HelloWorld());

        }else {
            ctx.insert(new LundBendsen());

        }



    }
}
