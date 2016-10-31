import ratpack.exec.Blocking;
import ratpack.form.Form;
import ratpack.handling.*;
import ratpack.handling.Handler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;

/**
 * Created by thokle on 26/10/2016.
 */
public class BlockingHandler implements Handler {
    @Override
    public void handle(Context ctx) throws Exception {

        Connection connection = ctx.get(DataSource.class).getConnection();
        if (ctx.getRequest().getMethod().isPost()) {

            ctx.parse(Form.class).then(u -> {

                Blocking.get(() -> {
                    try {

                        PreparedStatement statement = connection.prepareStatement("insert into blog(tekst,username) values(?,?)");
                        statement.setString(1, u.get("tekst"));
                        statement.setString(2, u.get("username"));
                        return statement.execute() ? "failure¡" : "success";
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return "failure";
                }).then(resultat ->  ctx.render(json(resultat)));


            });

        } if(ctx.getRequest().getMethod().isGet()){
            try{
                Blocking.get(() -> {
                PreparedStatement  preparedStatement =     connection.prepareStatement("select * from blog");
                    List<Blog> stringList = new ArrayList();
                  ResultSet resultSet =  preparedStatement.executeQuery();
                    while(resultSet.next()) {
                        Blog blog = new Blog();
                        blog.setId(resultSet.getInt(1));
                        blog.setTekst(resultSet.getString(2));
                        blog.setUsername(resultSet.getString(3));
                        stringList.add(blog);

                    }
                    return  stringList;
                }).then(res  ->    ctx.render(json(res)));


            }catch (Exception e){

            }



        } if(ctx.getRequest().getMethod().isPut()){
            try{

                ctx.parse(fromJson(Blog.class)).then(as -> {
                    Blocking.get(() -> {
                   PreparedStatement preparedStatement =  connection.prepareStatement("update blog set tekst= ? , username=? where id=?");
                    preparedStatement.setString(1,as.getTekst());
                    preparedStatement.setString(new Integer(2),as.getUsername());
                    preparedStatement.setInt(new Integer(3), as.getId());
                  return preparedStatement.executeUpdate();

                    }).then(put -> {
                        ctx.getResponse().send(""+put);

                    });
                });


            }catch (Exception e){}




        }
    }

}
