package co.edu.poli.facturacion.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.edu.poli.facturacion.model.Product;
import co.edu.poli.facturacion.repository.ProductRepository;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://127.0.0.1:4200")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
    	Product a = productRepository.findById(id).get();
		return a;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product productDetails) {
        Product product = productRepository.findById(id).get();

        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setQuantity(productDetails.getQuantity());

        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        Product product = productRepository.findById(id).get();

        productRepository.delete(product);
    }

 //LA SOLICITUD SE DEBE HACER ASÍ: http://127.0.0.1:8080/products/search?name=tun (DONDE TUN ES UNA CADENA QUE PUEDE ESTAR EN EL NOMBRE DEL PRODUCTO)
    @GetMapping("/search")
    public List<Product> searchProductsByName(@RequestParam("name") String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
}
