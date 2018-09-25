package com.apap.tutorial3.service;


import com.apap.tutorial3.model.CarModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarInMemoryService implements CarService{
    private List<CarModel> archiveCar;

    public CarInMemoryService(){
        archiveCar = new ArrayList<>();
    }

    @Override
    public void addCar(CarModel car){
        archiveCar.add(car);
    }

    @Override
    public void deleteCar(CarModel car){
        archiveCar.remove(car);
    }

    @Override
    public List<CarModel> getCarList(){
        return archiveCar;
    }

    public CarModel getCarDetail(String id){
        for (CarModel car : archiveCar) {
            if(car.getId().equalsIgnoreCase(id)){
                return car;
            }
        }
        return null;
    }


}
