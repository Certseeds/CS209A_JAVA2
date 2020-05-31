/*  CS209A_JAVA2 
    Copyright (C) 2020 nanoseeds
    
    CS209A_JAVA2 is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    CS209A_JAVA2 is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
    */
/**
 * @Github: https://github.com/Certseeds/CS209A_JAVA2
 * @Organization: SUSTech
 * @Author: nanoseeds
 * @Date: 2020-05-31 20:38:12
 * @LastEditors : nanoseeds
 */
package com.nanoseeds.java1_assignment6;

import java.util.List;

public class ConcreteCinema implements Cinema {
    List<Movie> movies;

    // TODO add more function.
    // TODO add a class that represents movie hall
    @Override
    public void addMovieHall(int capacity) {

    }

    @Override
    public List<String> getAllMovieHallsCapacity() {
        return null;
    }

    @Override
    public void addMovie(String name, int runtime, int hallNumber, double price, int type, Time startTime) {

    }

    @Override
    public List<Movie> getAllMovies() {
        return null;
    }

    @Override
    public List<Movie> getMoviesFromMovieHallOrderByStartTime(int hallNumber) {
        return null;
    }

    @Override
    public double reserveMovie(int movieId, int arg) {
        return 0;
    }

    @Override
    public Movie getMovieById(int movieId) {
        return null;
    }

    @Override
    public double getOneMovieIncome(int movieId) {
        return 0;
    }

    @Override
    public double getTotalIncome() {
        return 0;
    }

    @Override
    public List<Movie> getAvailableMoviesByName(Time currentTime, String name) {
        return null;
    }
}
