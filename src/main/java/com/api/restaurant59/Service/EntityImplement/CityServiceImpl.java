package com.api.restaurant59.Service.EntityImplement;

import com.api.restaurant59.DTO.CityDTO;
import com.api.restaurant59.Exception.ResourceNotFoundException;
import com.api.restaurant59.Mapper.CityMapper;
import com.api.restaurant59.Model.Entity.City;
import com.api.restaurant59.Model.Repository.CityRepository;
import com.api.restaurant59.Service.EntityService.CityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {


    @Autowired
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


    //get cities avec pagination
    @Override
    public Page<CityDTO> readAll(int page, int size) {

        // Crée un objet Pageable avec le numéro de page, la taille de la page, et le tri par ID croissant.
        Pageable pageable = PageRequest.of(page, size, Sort.by("idCity").ascending());
        // Utilise le repository pour trouver toutes les entités City en fonction de l'objet Pageable.
        Page<City> cityPage = cityRepository.findAll(pageable);

        // Mappe chaque entité City de la page à un CityDTO en utilisant le mapper spécifié.
        return cityPage.map(CityMapper::mapToCityDTO);
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

        // Cherche une entité par son identifiant
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Error! ID not found! :("));

        // Mise à jour des propriétés
        city.setCityName(updatedCityDto.getCityName());
        city.setPostalCode(updatedCityDto.getPostalCode());
        city.setInseeCode(updatedCityDto.getInseeCode());

        // Sauvegarde l'entité mise à jour
        City updatedCity = cityRepository.save(city);

        // Mappe l'entité mise à jour en DTO et la retourne
        return CityMapper.mapToCityDTO(updatedCity);
    }



    @Override
    public void deleteById(Integer id) {

        // Supprime l'entité par son identifiant.
        cityRepository.deleteById(id);
    }


}
