import ratpack.guice.Guice;
import ratpack.hikari.HikariModule;
import ratpack.server.RatpackServer;


/**
 * Created by thokle on 25/10/2016.
 */
public class StartRatPack {


    public static void main(String... args) throws Exception {

    RatpackServer.start(ratpackServerSpec -> ratpackServerSpec.registry(Guice.registry( bindingsSpec -> bindingsSpec.module(GuideModule.class).module(HikariModule.class , hikariConfig -> {

        hikariConfig.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");

        hikariConfig.setJdbcUrl("jdbc:mysql://localhost");
        hikariConfig.setUsername("root");
        hikariConfig.setCatalog("lundogbendsen");
    }))).serverConfig( serverConfigBuilder -> { serverConfigBuilder.development(true).port(7777);


    }).handlers(chain ->
            {

                chain.get(HelloWorld.class);
                chain.post("insert",BlockingHandler.class);
                chain.get("get",BlockingHandler.class);
                chain.get("jump/:id", JumpHandler.class);
                chain.put("update",BlockingHandler.class);
                chain.delete("delete",BlockingHandler.class);

                chain.prefix("", pre -> {
                        pre.all(HelloWorld.class);



                });
            }
    ));

    }

}
