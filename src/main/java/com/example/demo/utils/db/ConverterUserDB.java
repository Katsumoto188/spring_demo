package com.example.demo.utils.db;

import com.example.demo.model.domain.AlbumDomain;
import com.example.demo.model.domain.complete.*;
import com.example.demo.model.domain.user.UserAddressDomain;
import com.example.demo.model.domain.user.UserAddressGeoDomain;
import com.example.demo.model.domain.user.UserCompanyDomain;
import com.example.demo.model.domain.user.UserDomain;
import com.example.demo.model.dto.user.UserAddressDTO;
import com.example.demo.model.dto.user.UserAddressGeoDTO;
import com.example.demo.model.dto.user.UserCompanyDTO;
import com.example.demo.model.gateway.CommentGateway;
import com.example.demo.model.gateway.response.UserResponseGateway;
import com.example.demo.model.gateway.user.UserAddressGateway;
import com.example.demo.model.gateway.user.UserAddressGeoGateway;
import com.example.demo.model.gateway.user.UserCompanyGateway;
import com.example.demo.model.gateway.user.UserGateway;
import com.example.demo.model.request.UserRequest;
import com.example.demo.model.request.complete.*;
import com.example.demo.model.response.UserResponse;
import com.example.demo.model.response.complete.PhotoCompleteResponse;
import com.example.demo.model.response.complete.UserCompleteResponse;
import com.example.demo.utils.gateway.MapperGateway;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component

public class ConverterUserDB {

    public static UserDomain convertUserRequestToDomain (UserRequest user){
        return UserDomain.builder()
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .address(Objects.isNull(user.getAddress()) ? null : convertUserAddressDTOToDomain(user.getAddress()))
                .phone(user.getPhone())
                .website(user.getWebsite())
                .company(Objects.isNull(user.getCompany()) ? null : convertUserCompanyDTOToDomain((user.getCompany())))
                .build();
    }

    private static UserAddressDomain convertUserAddressDTOToDomain (UserAddressDTO address){
        return UserAddressDomain.builder()
                .street(address.getStreet())
                .suite(address.getSuite())
                .city(address.getCity())
                .zipcode(address.getZipcode())
                .geo(Objects.isNull(address.getGeo()) ? null : UserAddressGeoDomain.builder()
                        .lat(address.getGeo().getLat())
                        .lng(address.getGeo().getLng())
                        .build())
                .build();
    }

    private static UserCompanyDomain convertUserCompanyDTOToDomain (UserCompanyDTO company){
        return UserCompanyDomain.builder()
                .name(company.getName())
                .catchPhrase(company.getCatchPhrase())
                .bs(company.getBs())
                .build();
    }

    public static UserResponse convertUserDomainToResponse (UserDomain user){
        return UserResponse.builder()
                .id(user.getId().toHexString())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .address(Objects.isNull(user.getAddress()) ? null : convertUserAddressDomainToDTO(user.getAddress()))
                .phone(user.getPhone())
                .website(user.getWebsite())
                .company(Objects.isNull(user.getCompany()) ? null : convertUserCompanyDomainToDTO((user.getCompany())))
                .build();
    }

    private static UserAddressDTO convertUserAddressDomainToDTO (UserAddressDomain address){
        return UserAddressDTO.builder()
                .street(address.getStreet())
                .suite(address.getSuite())
                .city(address.getCity())
                .zipcode(address.getZipcode())
                .geo(Objects.isNull(address.getGeo()) ? null : UserAddressGeoDTO.builder()
                        .lat(address.getGeo().getLat())
                        .lng(address.getGeo().getLng())
                        .build())
                .build();
    }

    private static UserCompanyDTO convertUserCompanyDomainToDTO (UserCompanyDomain company){
        return UserCompanyDTO.builder()
                .name(company.getName())
                .catchPhrase(company.getCatchPhrase())
                .bs(company.getBs())
                .build();
    }

    public static UserCompanyDomain convertUserCompanyGatewayToDomain(UserCompanyGateway company)
    {
        return UserCompanyDomain
                .builder()
                .bs(company.getBs())
                .catchPhrase(company.getCatchPhrase())
                .name(company.getCatchPhrase())
                .build();
    }

    public static UserDomain convertUserGatewayToDomain (UserGateway user)
    {
        return UserDomain
                .builder()
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .website(user.getWebsite())
                .address(Objects.isNull(user.getAddress()) ? null : convertAddressGatewayToDomain(user.getAddress()))
                .company(Objects.isNull(user.getCompany()) ? null : convertUserCompanyGatewayToDomain(user.getCompany()))
                .build();
    }


    public static UserAddressDomain convertAddressGatewayToDomain(UserAddressGateway address)
    {
        return UserAddressDomain
                .builder()
                .street(address.getStreet())
                .city(address.getCity())
                .suite(address.getSuite())
                .zipcode(address.getZipcode())
                .geo( (Objects.isNull(address.getGeo()) ? null:convertAddressGeoGatewayToGeoDomain(address.getGeo())) )
                .build();
    }

    public static UserAddressGeoDomain convertAddressGeoGatewayToGeoDomain(UserAddressGeoGateway geo)
    {
        return UserAddressGeoDomain
                .builder()
                .lat(Double.parseDouble(geo.getLat()))
                .lng(Double.parseDouble(geo.getLng()))
                .build();
    }

    public static UserCompleteDomain convertUserResponseGatewayToCompleteDomain(UserResponseGateway userResponseGateway)
    {
        return UserCompleteDomain
                .builder()
                .name(userResponseGateway.getName())
                .username(userResponseGateway.getUsername())
                .email(userResponseGateway.getEmail())
                .phone(userResponseGateway.getPhone())
                .website(userResponseGateway.getWebsite())
                .address(convertAddressGatewayToDomain(userResponseGateway.getAddress()))
                .company(convertUserCompanyGatewayToDomain(userResponseGateway.getCompany()))
                .albums(MapperGateway.mapToAlbumCompleteDomainList(userResponseGateway))
                .posts(MapperGateway.mapToPostCompleteDomainList(userResponseGateway))
                .todos(MapperGateway.mapToCompleteTodoDomainList(userResponseGateway.getTodoGateways()))
                .build();
    }

    public static UserCompanyDTO convertUserCompanyDomainToCompanyDTO(UserCompanyDomain userCompanyDomain)
    {
        return UserCompanyDTO
                .builder()
                .bs(userCompanyDomain.getBs())
                .catchPhrase(userCompanyDomain.getCatchPhrase())
                .name(userCompanyDomain.getName())
                .build();
    }

    public static UserAddressGeoDTO convertAddressGeoDomainToGeoDTO(UserAddressGeoDomain userAddressGeoDomain)
    {
        return UserAddressGeoDTO
                .builder()
                .lat(userAddressGeoDomain.getLat())
                .lng(userAddressGeoDomain.getLng())
                .build();
    }

    public static UserAddressDTO convertUserAddressDomainToAddressDTO(UserAddressDomain userAddressDomain)
    {
        return UserAddressDTO
                .builder()
                .city(userAddressDomain.getCity())
                .geo(convertAddressGeoDomainToGeoDTO(userAddressDomain.getGeo()))
                .street(userAddressDomain.getStreet())
                .suite(userAddressDomain.getSuite())
                .zipcode(userAddressDomain.getZipcode())
                .build();
    }









    public static UserCompanyDomain convertToUserCompanyDomain(UserCompanyDTO userCompanyDTO)
    {
        return UserCompanyDomain
                .builder()
                .name(userCompanyDTO.getName())
                .catchPhrase(userCompanyDTO.getCatchPhrase())
                .bs(userCompanyDTO.getBs())
                .build();
    }

    public static UserAddressGeoDomain convertToUserAddressGeoDomain(UserAddressGeoDTO userAddressGeoDTO)
    {
        return UserAddressGeoDomain
                .builder()
                .lng(userAddressGeoDTO.getLng())
                .lat(userAddressGeoDTO.getLat())
                .build();
    }

    public static UserAddressDomain convertToUserAddressDomain(UserAddressDTO userAddressDTO)
    {
        return UserAddressDomain
                .builder()
                .geo(convertToUserAddressGeoDomain(userAddressDTO.getGeo()))
                .zipcode(userAddressDTO.getZipcode())
                .suite(userAddressDTO.getSuite())
                .city(userAddressDTO.getCity())
                .street(userAddressDTO.getStreet())
                .build();
    }

    public static UserCompleteDomain convertToUserCompleteDomain(UserCompleteRequest userCompleteRequest)
    {
        return UserCompleteDomain
                .builder()
                .name(userCompleteRequest.getName())
                .username(userCompleteRequest.getUsername())
                .phone(userCompleteRequest.getPhone())
                .website(userCompleteRequest.getWebsite())
                .email(userCompleteRequest.getEmail())
                .company(convertToUserCompanyDomain(userCompleteRequest.getCompany()))
                .address(convertToUserAddressDomain(userCompleteRequest.getAddress()))

                .albums(MapperDB.mapAlbumCompleteDomainList(userCompleteRequest.getAlbums()))
                .posts(MapperDB.mapPostCompleteDomainList(userCompleteRequest.getPosts()))
                .todos(MapperDB.mapTodoCompleteDomainList(userCompleteRequest.getTodos()))
                .build();
    }
}
