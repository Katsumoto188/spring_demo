package com.example.demo.service;

import com.example.demo.model.domain.PhotoDomain;
import com.example.demo.model.request.PhotoRequest;
import com.example.demo.model.response.PhotoResponse;
import com.example.demo.repository.PhotoRepository;
import com.example.demo.service.db.PhotoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PhotoServiceTest {

    @InjectMocks
    private PhotoService photoService;

    @Mock
    private PhotoRepository photoRepository;

    private PhotoDomain photoDomain;
    private PhotoResponse photo;
    private String photoId;
    private PhotoRequest photoRequest;
    private PhotoDomain photoDomainWithoutAllIds;
    private PhotoDomain photoDomainWithoutOriginalsId;

    @BeforeEach
    private void init()
    {
        photoDomain = CompletePhotoBuilder.validPhotoDomain();
        photo = CompletePhotoBuilder.validPhoto();
        photoId = "507f191e810c19729de860ea";
        photoRequest = CompletePhotoBuilder.validPhotoRequest();
        photoDomainWithoutAllIds = CompletePhotoBuilder.validPhotoDomainWithoutAllIds();
        photoDomainWithoutOriginalsId = CompletePhotoBuilder.validPhotoDomainWithoutOriginalsId();
    }

    @Test
    public void testFindAll()
    {
        when(photoRepository.findAll()).thenReturn(Collections.singletonList(photoDomain));
        List<PhotoResponse> photos = photoService.getAll();

        Assertions.assertEquals(Collections.singletonList(photo), photos);
        verify(photoRepository).findAll();
    }

    @Test
    public void testFindById()
    {
        when(photoRepository.findById(photoId)).thenReturn(Optional.of(photoDomain));
        PhotoResponse photo = photoService.getById(photoId);

        Assertions.assertEquals(this.photo, photo);
        verify(photoRepository).findById(photoId);
    }

    @Test
    public void testFindByIdError()
    {
        when(photoRepository.findById(photoId)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> {
                    photoService.getById(photoId);
                });
    }

//    @Test
//    public void testInsert()
//    {
//        when(photoRepository.insert(photoDomainWithoutAllIds)).thenReturn(photoDomainWithoutAllIds);
//        PhotoDomain photoDomainResult = photoService.create(photoRequest);
//
//        Assertions.assertEquals(this.photoDomainWithoutAllIds, photoDomainResult);
//        verify(photoRepository).insert(photoDomainWithoutAllIds);
//    }

//    @Test
//    public void testUpdate()
//    {
//        when(photoRepository.findById("sad1234sadf12@er234R$")).thenReturn(Optional.of(photoDomain));
//        when(photoRepository.save(photoDomainWithoutOriginalsId)).thenReturn(photoDomainWithoutOriginalsId);
//
//        PhotoDomain photoDomainResult = photoService.update(photoRequest, "sad1234sadf12@er234R$");
//        Assertions.assertEquals(this.photoDomainWithoutOriginalsId, photoDomainResult);
//
//        verify(photoRepository).save(photoDomainWithoutOriginalsId);
//        verify(photoRepository).findById("sad1234sadf12@er234R$");
//    }

    @Test
    public void testDelete()
    {
        photoRepository.deleteById("507f191e810c19729de860ea");
        verify(photoRepository).deleteById("507f191e810c19729de860ea");
    }
}