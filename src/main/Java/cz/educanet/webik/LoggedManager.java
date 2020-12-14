package cz.educanet.webik;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class LoggedManager implements Serializable {

    User prihlasenej = null;

}
