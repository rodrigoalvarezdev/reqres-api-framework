package endpoints;

public class Endpoints {
	 // GET
    public static final String LIST_USERS = "/users?page={page}";
    public static final String SINGLE_USER = "/users/{id}";

    // POST
    public static final String CREATE_USER = "/users";

    // PUT
    public static final String UPDATE_USER = "/users/{id}";

    // PATCH
    public static final String PATCH_USER = "/users/{id}";

    // DELETE
    public static final String DELETE_USER = "/users/{id}";

    // POST LOGIN
    public static final String LOGIN = "/login";

    // POST REGISTER
    public static final String REGISTER = "/register";
}
