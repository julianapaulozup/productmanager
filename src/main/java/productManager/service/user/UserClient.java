package productManager.service.user;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;


@Headers("Authorization: token OAUTH-TOKEN" )
public interface UserClient {

    @RequestLine("GET /{login}")
    User findByLogin(@Param("login") String login);

    @RequestLine("GET ")
    List<UserResource> findAll();

}