package com.example.java6.api;

import com.example.java6.entity.Order;
import com.example.java6.entity.OrderDetail;
import com.example.java6.entity.Product;
import com.example.java6.entity.User;
import com.example.java6.repository.IOderResponsitory;
import com.example.java6.service.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderApi {

    private final IOderResponsitory oderResponsitory;
    private final ProductService productService;
    private final UserService userService;
    private final CartService cartService;
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

    public OrderApi(IOderResponsitory oderResponsitory, ProductService productService, UserService userService, CartService cartService, OrderService orderService, OrderDetailService orderDetailService) {
        this.oderResponsitory = oderResponsitory;
        this.productService = productService;
        this.userService = userService;
        this.cartService = cartService;
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/order")
    @ResponseBody
    public Object[] check(@RequestParam(name = "chk[]",required = false) Integer[] chk, @RequestParam(name="quantity[]",required = false) Integer[] quantity) {
        List<String> list=new ArrayList<>();
        if (chk==null) {
            list.add("Không có sản phẩm được chọn");
            return list.toArray();
        } else {
            int check=0;
            for (int i = 0; i < chk.length; i++) {
                Product product = productService.findById(chk[i]);
                if (product.getQuantity() < quantity[i]) {
                    check++;
                    list.add("Sản phẩm: "+product.getName()+" chỉ còn "+product.getQuantity()+"\n");
                }
            }
            if(check==0) {
                BigDecimal total = BigDecimal.ZERO;
                User user = userService.findByUserName("TQT@gmail.com");
                Order order = new Order();
                order.setAddress("Bac Ninh");
                order.setCreatedDate();
                order.setUser(user);
                order.setTrangThai(0);
                order.setTotal(total);
                order = orderService.add(order);
                for (int i = 0; i < chk.length; i++) {
                    OrderDetail orderDetail = new OrderDetail();
                    System.out.println(chk[i]);
                    Product product = productService.findById(chk[i]);
                    orderDetail.setOrder(order);
                    orderDetail.setPrice(product.getPrice());
                    orderDetail.setProduct(product);
                    orderDetail.setQuantity(quantity[i]);
                    total = total.add(orderDetail.getPrice().multiply(new BigDecimal(orderDetail.getQuantity())));
                    orderDetailService.add(orderDetail);
                    product.setQuantity(product.getQuantity() - quantity[i]);
                    productService.updateProduct(product.getId(),product);
                    cartService.delete(cartService.getByProduct(product).getId());
                }
                order.setTotal(total);
                orderService.add(order);
                list.add("Thêm thành công");
                return list.toArray();
            }else {
                return list.toArray();
            }
        }
    }
}
