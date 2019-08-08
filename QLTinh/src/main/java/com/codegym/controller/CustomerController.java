package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.CustomerService;
import com.codegym.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
   private ProvinceService provinceService;
    @ModelAttribute("provinces")
    public Iterable<Province> provinces(){
        return provinceService.showAll();
    }

    @GetMapping("/search-province")
    public String searchProvince(@RequestParam("province") Long id,Model model){
        Province province=provinceService.findById(id);
        Iterable<Customer> customers=customerService.findAllByProvince(province);
        model.addAttribute("searchProvince",province);
        model.addAttribute("searchCustomer",customers);
        return "customer/search";
    }

    @GetMapping("/show-customer")
    public ModelAndView showCustomer(@PageableDefault(2) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("customer/list");
        modelAndView.addObject("customers", customerService.showAll(pageable));
        return modelAndView;
    }

    @GetMapping("/show-createCustomer")
    public String showCreate(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/create";
    }

    @PostMapping("/create-customer")
    public String createCustomer(@ModelAttribute Customer customer, Model model) {
        customerService.save(customer);
        model.addAttribute("customer", new Customer());
        model.addAttribute("messageCreate", "Them thanh cong");
        return "customer/create";
    }

    @GetMapping("/show-editCustomer/{id}")
    public String showEditCustomer(@PathVariable Long id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customer/edit";
    }

    @PostMapping("/edit-customer")
    public String editCustomer(@ModelAttribute Customer customer, Model model) {
        customerService.save(customer);
        model.addAttribute("customer", customer);
        model.addAttribute("messageEdit", "Sua thanh cong");
        return "customer/edit";
    }

    @GetMapping("/show-deleteCustomer/{id}")
    public String showDeleteCustomer(@PathVariable Long id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customer/delete";
    }

    @PostMapping("/delete-customer")
    public String deleteCustomer(@ModelAttribute Customer customer, Model model) {
        Long id = customer.getId();
        customerService.remove(id);
        model.addAttribute("customer", customer);
        model.addAttribute("messagedelete", "Xoa  thanh cong");
        return "customer/delete";
    }

    @GetMapping("/show-viewCustomer/{id}")
    public String showViewCustomer(@PathVariable Long id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "customer/view";
    }
}
