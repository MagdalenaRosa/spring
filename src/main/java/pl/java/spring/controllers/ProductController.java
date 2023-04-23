package pl.java.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.java.spring.models.Product;
import pl.java.spring.services.ProductService;

@Controller
public class ProductController {
    //    ProductService service = new ProductService();// tak się nie robi bo mamy zależność to my ja zarządzamy
    ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String showProducts(Model model) {
        model.addAttribute("title", "Product List");
        model.addAttribute("db", productService.getProduct());
        model.addAttribute("actionUri", "/saveProduct");// w tym bo to jest ten w którym się znajduje to moje action
        return "products/Products";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(Product productFrom) {
        productService.insertProduct(productFrom);
        return "redirect:/";
    }

    @GetMapping("/removeProduct")
    public String removeProduct(@RequestParam Integer productId) {
        productService.removeProduct(productId);
        return "redirect:/";
    }

    @PostMapping("/editedProduct")
    public String saveEditedProduct(Product productForm, @RequestParam Integer productId) {
        productService.updateProduct(productId, productForm);
        return "redirect:/";
    }

    @GetMapping("/editProduct")
    public String showEditProduct(@RequestParam Integer productId, Model model) {
        model.addAttribute("actionUri", "/editedProduct?productId=" + productId);
        bindProductModel(productId, model);
        return "products/edit-Product";

    }

    @GetMapping("/productDetails")
    public String showDetails(@RequestParam Integer productId, Model model) {
        model.addAttribute("id", productId);

        bindProductModel(productId, model);
        return "products/Product";
    }

    private void bindProductModel(Integer productId, Model model) {
        var optionalProduct = productService.findProduct(productId);
        if (optionalProduct.isPresent()) {
            var product = optionalProduct.get();// to jestem pewna że lista nie jest pusta
            model.addAttribute("product", product);
        }
    }

}

