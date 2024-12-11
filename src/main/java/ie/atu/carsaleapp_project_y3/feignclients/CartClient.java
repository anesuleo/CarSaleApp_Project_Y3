package ie.atu.carsaleapp_project_y3.feignclients;

import ie.atu.carsaleapp_project_y3.entity.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="cart-service", url="http://localhost:8081/cart")
public interface CartClient {
    @PostMapping
    public String makeCart(@RequestBody Car car);
    public ResponseEntity<String> addToCart(@RequestBody Car car, @RequestParam("userId") int userId);
}
