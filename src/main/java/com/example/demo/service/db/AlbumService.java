package com.example.demo.service.db;

import com.example.demo.model.domain.AlbumDomain;
import com.example.demo.model.gateway.AlbumGateway;
import com.example.demo.model.gateway.PhotoGateway;
import com.example.demo.model.request.AlbumRequest;
import com.example.demo.model.response.AlbumResponse;
import com.example.demo.repository.AlbumRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.db.ConverterAlbumDB;
import com.example.demo.utils.gateway.FilterGateway;
import com.example.demo.utils.response.ConverterResponse;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class AlbumService {

    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;
    private final PhotoService photoService;

    public List<AlbumResponse> getAll (){
        List<AlbumDomain> albums = albumRepository.findAll();
        return albums.stream().map(ConverterResponse::convertAlbumDomainToResponse)
                .collect(Collectors.toList());
    }

    public AlbumResponse getById (String id){
        AlbumDomain album = albumRepository.findById(id).orElseThrow();
        return ConverterResponse.convertAlbumDomainToResponse(album);
    }

    public AlbumResponse create (AlbumRequest request){
        userRepository.findById(request.getUserId()).orElseThrow();
        AlbumDomain album = ConverterAlbumDB.convertAlbumRequestToDomain(request);
        album = albumRepository.insert(album);
        return ConverterResponse.convertAlbumDomainToResponse(album);
    }

    public String create (AlbumGateway request, String userId){
//        userRepository.findById(userId).orElseThrow();
        AlbumDomain album = ConverterAlbumDB.convertAlbumGatewayToDomain(request);
        album.setUserId(new ObjectId(userId));
        album = albumRepository.insert(album);
        return album.getId().toHexString();
    }

    public AlbumResponse update (AlbumRequest request, String id){
        userRepository.findById(request.getUserId()).orElseThrow();
        getById(id);
        AlbumDomain album = ConverterAlbumDB.convertAlbumRequestToDomain(request);
        album.setId(new ObjectId(id));
        album = albumRepository.save(album);
        return ConverterResponse.convertAlbumDomainToResponse(album);
    }

    public void delete (String id){
        getById(id);
        albumRepository.deleteById(id);
    }

    public void purge()
    {
        albumRepository.deleteAll();
    }

    public void seed(List<AlbumGateway> albumGateways, String userId, List<PhotoGateway> photoGateways)
    {
        albumGateways.forEach(albumGateway ->
        {
            String albumId = create(albumGateway, userId);
            List<PhotoGateway> albumPhotos = FilterGateway.filteredPhotoByAlbumId(photoGateways, albumGateway.getId());
            photoService.seed(albumPhotos, albumId);
        });
    }
}
