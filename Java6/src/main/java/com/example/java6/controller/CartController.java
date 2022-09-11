package com.example.java6.controller;

import com.example.java6.entity.Cart;
import com.example.java6.entity.Product;
import com.example.java6.entity.User;
import com.example.java6.service.CartService;
import com.example.java6.service.ProductService;
import com.example.java6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CartController extends BaseController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    protected CartController(CartService cartService) {
        super(cartService);
    }

    @GetMapping("/cart")
    private String showIndex(Model model) {
        List<Cart> carts = cartService.list();
//        Integer size = carts.size();
//        model.addAttribute("size", size);
        model.addAttribute("listcart", carts);
        return "views/cart/cart";
    }
    @GetMapping("/addcart/{id}")
    private String add(@PathVariable Integer id,Cart cart,@RequestParam("quantity") Integer quantity) {
        Product product = productService.findById(id);
        User user= userService.findByUserName("TQT@gmail.com");
        cart.setUser(user);
        cart.setPrice(product.getPrice());
        Cart c=cartService.getByProduct(product);
        if(c==null) {
            cart.setProduct(product);
            cart.setQuantity(quantity);
            cartService.add(cart);
        }else {
            c.setQuantity(quantity+c.getQuantity());
            cartService.add(c);
        }
        return "redirect:/product";
    }
}
