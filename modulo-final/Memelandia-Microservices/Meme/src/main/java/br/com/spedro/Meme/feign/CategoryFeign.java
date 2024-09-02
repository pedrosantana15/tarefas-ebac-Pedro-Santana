package br.com.spedro.Meme.feign;

import br.com.spedro.Meme.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

@Configuration
@EnableFeignClients
@EnableDiscoveryClient
public class CategoryFeign {

    @Autowired
    private CategoryClient categoryClient;

    @FeignClient(name = "CategoryServiceClient")
    interface CategoryClient {

        @RequestMapping(path = "category/find-by-id/{id}", method = RequestMethod.GET)
        @ResponseBody
        Category findCategoryById(@RequestParam("id") String id);

        @RequestMapping(path = "category/is-registered/{id}", method = RequestMethod.GET)
        Boolean isCategoryRegistered(@RequestParam("id") String id);

    }

    public Category findCategoryById(String id) {
        return categoryClient.findCategoryById(id);
    }

    public Boolean isCategoryRegistered(String id) {
        return categoryClient.isCategoryRegistered(id);
    }

}
