package br.com.spedro.Meme.feign;

import br.com.spedro.Meme.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Configuration
@EnableDiscoveryClient
@EnableFeignClients
public class UserFeign {

    private final UserClient userClient;

    @Autowired
    public UserFeign(UserClient userClient) {
        this.userClient = userClient;
    }

    @FeignClient(name = "UserServiceClient")
    interface UserClient {

        @RequestMapping(path = "user/find-by-id/{id}", method = RequestMethod.GET)
        User findUserById(@PathVariable(value = "id") String id);

        @RequestMapping(path = "user/is-registered/{id}")
        Boolean isUserRegistered(@PathVariable(value = "id") String id);

    }

    public User findUserById(String id) {
        return userClient.findUserById(id);
    }

    public Boolean isUserRegistered(String id) {
        return userClient.isUserRegistered(id);
    }

}
