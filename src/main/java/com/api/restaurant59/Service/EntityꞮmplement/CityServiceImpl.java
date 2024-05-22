package com.api.restaurant59.Service.EntityꞮmplement;

import com.api.restaurant59.DTO.CityDTO;
import com.api.restaurant59.Exception.ResourceNotFoundException;
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

        //Mappe le DTO en entité
        City city = CityMapper.mapToCityEntity(cityDto);
        //Sauvegarde l'entité dans le repository
        City savedCity = cityRepository.save(city);
        // Mappe l'entité sauvegardée en DTO
        CityDTO savedCityDto = CityMapper.mapToCityDTO(savedCity);

        //Retourne le DTO sauvegardé
        return  savedCityDto;
    }



    @Override
    public List<CityDTO> readAll() {

        // Récupère toutes les entités du repository
        List<City> cities = cityRepository.findAll();

        // Mappe chaque entité en DTO et retourne la liste des DTO
        return cities.stream().map(CityMapper::mapToCityDTO).collect(Collectors.toList());
    }



    @Override
    public CityDTO getById(Integer id) {
        // Cherche une entité City par son identifiant dans le repository.
        // Optional<City> peut contenir une entité City ou être vide si aucune entité n'a été trouvée avec cet identifiant.
        Optional<City> optionalCity = cityRepository.findById(id);


        // Si une entité City est trouvée, elle est convertie en CityDTO par le CityMapper.
        // Sinon, une exception ResourceNotFoundException est lancée avec un message d'erreur.
        return optionalCity.map(CityMapper::mapToCityDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Error! ID not found! :("));
    }



    @Override
    public CityDTO update(Integer id, CityDTO updatedCityDto) {

        City city = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Error! ID not found! :("));

        // Mise à jour des propriétés
        city.setCityName(updatedCityDto.getCityName());
        city.setPostalCode(updatedCityDto.getPostalCode());
        city.setInseeCode(updatedCityDto.getInseeCode());

        City updatedCity = cityRepository.save(city);

        return CityMapper.mapToCityDTO(updatedCity);
    }



    @Override
    public void deleteById(Integer id) {
        cityRepository.deleteById(id);
    }


}
