package com.stackroute.goingPlaces.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.goingPlaces.Services.MarkerService;
import com.stackroute.goingPlaces.Services.MarkerServicesDef;
import com.stackroute.goingPlaces.domain.Markers;
import com.stackroute.goingPlaces.repository.MarkerRepo;


public class MarkerServiceTest {
	private MarkerServicesDef markerservice;
    @Mock
    private MarkerRepo repo;
    @Mock
    private Markers marker;
    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        markerservice=new MarkerService();
        markerservice.setMarkerdao(repo);
    }
    @Test
    public void shouldReturnAllMarkers() throws Exception {
        // Arrange
        when(repo.findAll()).thenReturn(new ArrayList<Markers>());
        // Act
        List<Markers> retrievedProduct = markerservice.getAllMarkers();
        // Assert
        assertThat(retrievedProduct, is(equalTo(new ArrayList<Markers>())));

  }
    @Test
    public void shouldReturnSavedMarker() throws Exception {
        // Arrange
        when(repo.save(marker)).thenReturn(marker);
        // Act
        String savedMarker = markerservice.AddnewMarkers(marker);
        // Assert
        assertThat(savedMarker, is(equalTo("success")));
    }
    @Test
    public void shouldCallDeleteMethodOfProductRepository_whenDeleteProductIsCalled() throws Exception {
        // Arrange
        doNothing().when(repo).delete(5);
        MarkerRepo my = Mockito.mock(MarkerRepo.class);
        // Act
        markerservice.deleteMarker(5);
        // Assert
        verify(repo, times(1)).delete(5);
    }
}
