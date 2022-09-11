package com.example.java6.controller;

import com.example.java6.dto.request.ProductRequest;
import com.example.java6.entity.Product;
import com.example.java6.entity.ProductType;
import com.example.java6.service.CartService;
import com.example.java6.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Autowired
    public ProductService productService;

    protected ProductController(CartService cartService) {
        super(cartService);
    }

    @GetMapping()
    public String showProduct(Model model, @RequestParam(name = "name", required = false) String name){
        List<Product> products;
        if (StringUtils.hasText(name)){
            products = productService.getAllByName(name);
        }else {
            products = productService.getAll();
        }
        model.addAttribute("listproduct", products);
        return "views/product/listproduct";
    }
    @GetMapping("/new-product")
    public String showFormCreate(Model model, Product product){
        ProductType[] productTypes = ProductType.values();
        model.addAttribute("productReq",new ProductRequest());
        model.addAttribute("type", productTypes);
        return "views/product/addproduct";
    }
    @PostMapping("/create-product")
    public String createProduct(@ModelAttribute(name = "productReq")ProductRequest productRequest, BindingResult result,
                                Model model){
        if (result.hasErrors()){
            model.addAttribute("productReq",productRequest);
            result.toString();
            return "views/product/addproduct";
        }
        productService.add(productRequest);
        return "redirect:/product";
    }
    @GetMapping("/list")
    public String showListProduct(Model model, @RequestParam(name = "page",defaultValue = "0") Integer page){
        Page<Product> products = productService.getAllPage(page);
        model.addAttribute("listproduct", products.getContent());
        model.addAttribute("currentPage", products.getNumber());
        model.addAttribute("maxPage", products.getTotalPages());
        return "views/product/allproduct";
    }
    @GetMapping("/edit")
    public String showFormUpdate(Model model, @RequestParam(name = "id",defaultValue = "0") Integer id){
        Product product = productService.findById(id);
        ProductType[] productTypes = ProductType.values();
        model.addAttribute("type", productTypes);
        model.addAttribute("productReq", new ProductRequest());
        model.addAttribute("product", product);
        return "views/product/updateproduct";
    }
    @PostMapping("/update")
        public String updateProduct(Model model,
                @ModelAttribute(name = "productReq")ProductRequest productRequest,
                @RequestParam(name = "id",defaultValue = "0") Integer id){
            productService.update(id,productRequest);
            Product product = productService.findById(id);
            return "redirect:/product/list";
    }
    @GetMapping("/delete")
    private String deleteProduct(@RequestParam(name = "id",defaultValue = "0") Integer id) {
        Product product = productService.findById(id);
        productService.delete(id, product);
        return "redirect:/product/list";
    }
    @GetMapping("/detail")
    private String detail(Model model,@RequestParam(name = "id",defaultValue = "0") Integer id) {
        Product product = productService.findById(id);
        List<Product>products = productService.getAll();
        model.addAttribute("products", products);
        model.addAttribute("product", product);
        return "views/product/detailproduct";
    }
}
