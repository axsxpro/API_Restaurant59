package com.api.restaurant59.Service.EntityꞮmplement;

import com.api.restaurant59.DTO.CityDTO;
import com.api.restaurant59.Mapper.CityMapper;
import com.api.restaurant59.Model.Entity.City;
import com.api.restaurant59.Model.Repository.CityRepository;
import com.api.restaurant59.Service.EntityService.CityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {


    private CityRepository cityRepository;


    @Override
    public CityDTO create(CityDTO cityDto) {

        City city = CityMapper.mapToCityEntity(cityDto);
        City savedCity = cityRepository.save(city);
        CityDTO savedCityDto = CityMapper.mapToCityDTO(savedCity);

        return  savedCityDto;
    }


    public List<CityDTO> readAll() {
        List<City> cities = cityRepository.findAll();
        return cities.stream().map(CityMapper::mapToCityDTO).collect(Collectors.toList());
    }


    public CityDTO update(CityDTO cityDto) {

        City existingCity = cityRepository.findById(cityDto.getIdCity()).get();

        // Mise à jour des propriétés
        existingCity.setCityName(cityDto.getCityName());
        existingCity.setInseeCode(cityDto.getInseeCode());
        City updatedCity = cityRepository.save(existingCity);

        return CityMapper.mapToCityDTO(updatedCity);
    }


    public CityDTO getById(Integer id) {
        Optional<City> optionalCity = cityRepository.findById(id);
        return optionalCity.map(CityMapper::mapToCityDTO).orElse(null);
    }


    public void deleteById(Integer id) {
        cityRepository.deleteById(id);
    }


}
