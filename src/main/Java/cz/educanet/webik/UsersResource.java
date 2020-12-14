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
    public Response odhlaseni() {
        loggedManager.prihlasenej = null;
        return Response.ok("Byl jsi odhlasen").build();
    }

    public Response ziskejUzivatele() {
        if();
    }
}