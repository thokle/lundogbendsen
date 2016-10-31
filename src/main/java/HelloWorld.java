import ratpack.handling.*;

/**
 * Created by thokle on 27/10/2016.
 */
public class HelloWorld  implements ratpack.handling.Handler{
    @Override
    public void handle(Context ctx) throws Exception {
        ctx.render("Index");
    }
}
