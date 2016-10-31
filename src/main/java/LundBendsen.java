import ratpack.handling.*;
import ratpack.websocket.WebSocket;
import ratpack.websocket.WebSockets;
import ratpack.websocket.internal.DefaultWebSocket;

/**
 * Created by thokle on 28/10/2016.
 */
public class LundBendsen implements ratpack.handling.Handler
{
    @Override
    public void handle(Context ctx) throws Exception {
     ctx.getResponse().send("From lund&bendsen");

        ctx.getResponse().sendStream();
    }
}
