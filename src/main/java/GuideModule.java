import com.google.inject.AbstractModule;

/**
 * Created by thokle on 26/10/2016.
 */
public class GuideModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(BlockingHandler.class).asEagerSingleton();
        bind(Handler.class).asEagerSingleton();
        bind(HelloWorld.class);
        bind(JumpHandler.class);
        bind(LundBendsen.class).asEagerSingleton();
    }
}
