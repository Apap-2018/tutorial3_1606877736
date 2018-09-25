package com.apap.tutorial3.controller;

import com.apap.tutorial3.model.CarModel;
import com.apap.tutorial3.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarService mobileService;

    @RequestMapping("/car/add")
    public String add(@RequestParam(value = "id", required = true) String id,
                      @RequestParam(value = "brand", required = true) String brand,
                      @RequestParam(value = "type", required = true) String type,
                      @RequestParam(value = "price", required = true) Long price,
                      @RequestParam(value = "amount", required = true) Integer amount){
        CarModel car = new CarModel(id, brand, type, price, amount);
        mobileService.addCar(car);
        return "add";
    }

    @RequestMapping("/car/view")
    public String view(@RequestParam(value = "id", required = true) String id, Model model){
        CarModel archive = mobileService.getCarDetail(id);
        model.addAttribute("car", archive);
        return "view-car";
    }

    @RequestMapping("/car/view/{id}")
    public String viewPath(@PathVariable String id, Model model){
        CarModel archive = mobileService.getCarDetail(id);
        if(archive != null) {
            model.addAttribute("car", archive);
            return "view-car";
        } else {
            model.addAttribute("idError", id);
            return "view-error";
        }
    }

    @RequestMapping("/car/viewall")
    public String viewall(Model model){
        List<CarModel> archive = mobileService.getCarList();
        model.addAttribute("listcar", archive);
        return "viewall-car";
    }

    @RequestMapping("/car/update/{id}/amount/{amount}")
    public String updateAmount(@PathVariable String id, @PathVariable Integer amount, Model model){
        CarModel archive = mobileService.getCarDetail(id);
        if(archive != null) {
            model.addAttribute("amountbefore", archive.getAmount());
            archive.setAmount(amount);
            model.addAttribute("carUpdate", archive);

            return "update-amount";
        } else {
            model.addAttribute("idError", id);
            return "view-error";
        }

    }

    @RequestMapping("/car/delete/{id}")
    public String deleteCar(@PathVariable String id, Model model) {
        CarModel archive = mobileService.getCarDetail(id);
        if (archive != null) {
            mobileService.deleteCar(archive);
            return "delete-success";
        } else {
            model.addAttribute("idError", id);
            return "view-error";
        }
    }

}
