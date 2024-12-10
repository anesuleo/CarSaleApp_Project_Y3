package ie.atu.carsaleapp_project_y3.feignclients;

import ie.atu.carsaleapp_project_y3.entity.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="cart-service", url="http://localhost:8081/cart")
public interface CartClient {
    @PostMapping
    public String makeCart(@RequestBody Car car);
}
