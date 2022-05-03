package com.example.movie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.movie.bean.Movie;
import com.example.movie.controller.DeleteResponse;
import com.example.movie.repository.MovieRepository;

@Component
@Service
@Transactional
public class MovieService {
	
	@Autowired
	MovieRepository movierepo;


	public List<Movie> getAllMovie() {
		return movierepo.findAll();
	}
	

	public Movie getMoviebyId( int id) {	
		Optional<Movie> movie = movierepo.findById(id);
		return movie.isEmpty() ? null: movie.get();		
	}
	

	public Movie addMovie(Movie mov) {
		movierepo.save(mov);
		return mov;
	}


	public String updateMovie(Movie movie) {
		if(checkid(movie.getId())) {
			movierepo.save(movie);
			return "Updated successfully";
		}
		else {
			return "Id not found";
		}
	}


	public DeleteResponse deleteMovie(int id) {
		DeleteResponse addres = new DeleteResponse();
		addres.setId(id);
		
		if(checkid(id)){
			movierepo.deleteById(id);
			addres.setMessage("Deleted successfully");	
			return addres;
		}
		else {
			addres.setMessage("Check id, it doesn't exist here");
			return addres;
		}
	}
	
	
	private boolean checkid(int id) {
		List<Movie> movie=movierepo.findAll();
		for(Movie m:movie) {
			if(m.getId()==id) {			
				return true;
			}
		}
		return false;
	}

	
}
