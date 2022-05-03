package com.example.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie.bean.Movie;
import com.example.movie.service.MovieService;

@RestController
public class MovieController {
	
	@Autowired
	MovieService movieservice;
	
	@RequestMapping("/")
	public String home() {
		return "ALL MOVIEs";
	}
	
	@GetMapping("/movie")
	public List<Movie> getallmovies() {
		return movieservice.getAllMovie();
	}
	
	@GetMapping("/movie/{id}")
	public Movie getmoviebyid(@PathVariable("id") int id) {
			return movieservice.getMoviebyId(id);
	}
	
	@PostMapping("/movie/addmovie")
	public Movie addmovies(@RequestBody Movie mov) {
		return movieservice.addMovie(mov);
	}
	
	@PutMapping("/movie/updatemovie")
	public String updatemovie(@RequestBody Movie mov) {
		return movieservice.updateMovie(mov);
	}
	
	@DeleteMapping("/movie/deletemovie/{id}")
	public DeleteResponse deletemovie(@PathVariable("id") int id) {
		return movieservice.deleteMovie(id);
	}

}
