package new_shop.controllers;

import new_shop.model.entity.Product;
import new_shop.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("catalog/products")
public class ProductController {

    ProductRepository productRepository;
    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("list")
    ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("catalog/products/list");
        modelAndView.addAllObjects(Map.of("products",this.productRepository.findAll()));
        return modelAndView;
    }

    @RequestMapping("/{id}")
    public String editProduct(@PathVariable(name = "id") Integer productId, Model model) {
        Product product = productRepository.findById(productId).orElse(null);

        model.addAttribute("product", product);
        return "catalog/products/product";
    }


}
