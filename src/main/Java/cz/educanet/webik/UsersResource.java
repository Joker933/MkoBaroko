package cz.educanet.webik;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

    @Inject
    private UsersManager userManager;
    @Inject
    private LoggedManager loggedManager;

    @Path("/register")
    @POST
    public Response register(
            @FormParam("prvniJmeno")
            String prvniJmeno,
            @FormParam("jmeno")
            String jmeno,
            @FormParam("prijmeni")
            String prijmeni,
            @FormParam("heslo")
            String heslo,
            @FormParam("email")
            String email
    ) {
        User docasnyUser = new User(prvniJmeno, prijmeni, jmeno, heslo, email);

        if (userManager.existujeJmeno(jmeno)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            userManager.uloz(docasnyUser);
            return Response.ok("Uzivatel byl zaregistrovan").build();
        }
    }

    @Path("Login")
    @POST
    public Response prihlaseni(
            @FormParam("Jmeno") String jmeno,
            @FormParam("Heslo") String heslo,
            @FormParam("Email") String email

            ) {
            User docasny = userManager.ziskejJmenoHeslo(jmeno, heslo);
            if (docasny == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            } else {
                loggedManager.prihlasenej = docasny;
                return Response.ok().build();
            }
    }

    @Path("loggout")
    @POST
    public Response odhlaseni() {
        loggedManager.prihlasenej = null;
        return Response.ok("Byl jsi odhlasen").build();
    }

    public Response ziskejUzivatele() {
        if(loggedManager.prihlasenej == null){
           return Response.status(Response.Status.UNAUTHORIZED).build();
        } else {
            return Response.ok(loggedManager.prihlasenej.dostanJmeno()).build();
        }
    }
}
