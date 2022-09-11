package com.example.java6.controller;

import com.example.java6.entity.*;
import com.example.java6.repository.IOderResponsitory;
import com.example.java6.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    private final IOderResponsitory oderResponsitory;
    private final ProductService productService;
    private final UserService userService;
    private final CartService cartService;
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

    public OrderController(IOderResponsitory oderResponsitory, ProductService productService, UserService userService, CartService cartService, OrderService orderService, OrderDetailService orderDetailService) {
        this.oderResponsitory = oderResponsitory;
        this.productService = productService;
        this.userService = userService;
        this.cartService = cartService;
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
    }

//    @GetMapping("/order")
//    @ResponseBody
//    public Object[] check(@RequestParam(name = "chk[]",required = false) Integer[] chk, @RequestParam(name="quantity[]",required = false) Integer[] quantity) {
//        List<String> list=new ArrayList<>();
//        if (chk==null) {
//            list.add("Không có sản phẩm được chọn");
//            return list.toArray();
//        } else {
//            int check=0;
//            for (int i = 0; i < chk.length; i++) {
//                Product product = productService.findById(chk[i]);
//                if (product.getQuantity() < quantity[i]) {
//                    check++;
//                    list.add("Sản phẩm: "+product.getName()+" chỉ còn "+product.getQuantity()+"\n");
//                }
//            }
//            if(check==0) {
//                BigDecimal total = BigDecimal.ZERO;
//                User user = userService.findByUserName("TQT@gmail.com");
//                Order order = new Order();
//                order.setAddress("Bac Ninh");
//                order.setCreatedDate();
//                order.setUser(user);
//                order.setTrangThai(0);
//                order.setTotal(total);
//                order = orderService.add(order);
//                for (int i = 0; i < chk.length; i++) {
//                    OrderDetail orderDetail = new OrderDetail();
//                    Product product = productService.findById(chk[i]);
//                    orderDetail.setOrder(order);
//                    orderDetail.setPrice(product.getPrice());
//                    orderDetail.setProduct(product);
//                    orderDetail.setQuantity(quantity[i]);
//                    total = total.add(orderDetail.getPrice().multiply(new BigDecimal(orderDetail.getQuantity())));
//                    orderDetailService.add(orderDetail);
//                    product.setQuantity(product.getQuantity() - quantity[i]);
//                    productService.updateProduct(product.getId(),product);
//                    cartService.delete(cartService.getByProduct(product).getId());
//                }
//                order.setTotal(total);
//                orderService.add(order);
//                list.add("Thêm thành công");
//                return list.toArray();
//            }else {
//                return list.toArray();
//            }
//        }
//    }
    @GetMapping("/listorder")
    private String allOrder(Model model, @RequestParam(name = "page",defaultValue = "0") Integer page) {
        List<Cart> carts = cartService.list();
        Integer size = carts.size();
        model.addAttribute("size", size);
        Page<Order> orders = oderResponsitory.findAll(PageRequest.of(page, 3));
        model.addAttribute("listorder", orders.getContent());
        model.addAttribute("currentPage", orders.getNumber());
        model.addAttribute("maxPage", orders.getTotalPages());
        model.addAttribute("view", "/WEB-INF/views/allorder.jsp");
        return "index";
    }
    @GetMapping("/delete/{id}")
    private String deteteById(Model model,@PathVariable("id") Integer id) {
        cartService.delete(id);
        return "redirect:/cart";
    }
    @GetMapping("/cancle/{id}")
    private String updateTrangThai1(Model model,@PathVariable("id") Integer id) {
        Order order = orderService.getId(id);
        order.setTrangThai(1);
        List<OrderDetail> orderDetails=order.getOrderDetails();
        for(OrderDetail orderDetail : orderDetails) {
            Product product = orderDetail.getProduct();
            product.setQuantity(product.getQuantity()+ orderDetail.getQuantity());
            productService.addForOder(product);
        }
        orderService.add(order);
        return "redirect:/listorder";
    }
    @GetMapping("/accept/{id}")
    private String updateTrangThai2(Model model,@PathVariable("id") Integer id) {
        Order order = orderService.getId(id);
        order.setTrangThai(2);
        orderService.add(order);
        return "redirect:/listorder";
    }
    @GetMapping("/listorderdetail/{id}")
    private String orderDetail(Model model,@PathVariable("id") Integer id) {
        Order order = orderService.getId(id);
        List<OrderDetail> orderDetails= orderDetailService.listOrderDetail(order);
        model.addAttribute("orderid", order);
        model.addAttribute("listdetail", orderDetails);
        model.addAttribute("view", "/WEB-INF/views/detailorder.jsp");
        return "index";
    }
}

