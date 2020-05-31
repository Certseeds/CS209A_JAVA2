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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Hashtable;
import java.util.List;

public class ConcreteCinema implements Cinema {
    List<Movie> movies = new ArrayList<>();
    List<MovieHall> movieHalls = new ArrayList<>();
    Hashtable<Integer, Integer> hashtable_mv_hall = new Hashtable<>();
    Hashtable<Integer, Integer> hashtable_mv_type = new Hashtable<>();
    static int order_movie = 0;
    static int order_mvhall = 0;

    @Override
    public void addMovieHall(int capacity) {
        this.movieHalls.add(new MovieHall(++order_mvhall, capacity));
    }

    @Override
    public List<String> getAllMovieHallsCapacity() {
        List<String> will_return = new ArrayList<>();
        for (MovieHall mh : this.movieHalls) {
            will_return.add(mh.toString());
        }
        return will_return;
    }

    @Override
    public void addMovie(String name, int runtime, int hallNumber, double price, int type, Time startTime) {
        MovieHall mv_hall = get_mvhall(hallNumber);
        if (mv_hall == null) {
            return;
        }
        List<Movie> mvs_typesame = getMoviesByHall(hallNumber);
        for (Movie mv : mvs_typesame) {
            int begin_begin = Time.minus(startTime, mv.getStartTime()) - mv.getRuntime();
            int end_end = Time.minus(mv.getStartTime(), startTime) + runtime;
            if (Time.minus(startTime, mv.getStartTime()) < 0) {
                if (Time.minus(startTime, mv.getStartTime()) + runtime + 20 > 0) {
                    return;
                }
            } else {
                if (Time.minus(mv.getStartTime(), startTime) + mv.getRuntime() + 20 > 0) {
                    return;
                }
            }
        }
        if (type == 0) {
            this.movies.add(new OrdinaryMovie(++order_movie, name, startTime, runtime, price, mv_hall.getSize()));
            hashtable_mv_hall.put(order_movie, hallNumber);
            hashtable_mv_type.put(order_movie, 0);
        } else if (type == 1) {
            this.movies.add(new ThreeDMovie(++order_movie, name, startTime, runtime, price, mv_hall.getSize()));
            hashtable_mv_hall.put(order_movie, hallNumber);
            hashtable_mv_type.put(order_movie, 1);
        }

    }

    @Override
    public List<Movie> getAllMovies() {
        return this.movies;
    }

    @Override
    public List<Movie> getMoviesFromMovieHallOrderByStartTime(int hallNumber) {
        List<Movie> will_return = getMoviesByHall(hallNumber);
        Collections.sort(will_return);
        return will_return;
    }

    @Override
    public double reserveMovie(int movieId, int arg) {
        Movie mv = getMovieById(movieId);
//        if (mv != null) {
//            if (hashtable_mv_type.get(mv.getId()) == 0){
//                int buys = Math.min()
//            }
//        }
        return mv.purchase(arg);
    }

    @Override
    public Movie getMovieById(int movieId) {
        for (Movie mv : this.movies) {
            if (mv.getId() == movieId) {
                return mv;
            }
        }
        return null;
    }

    @Override
    public double getOneMovieIncome(int movieId) {
        return getMovieById(movieId).getSaled_money();
    }

    @Override
    public double getTotalIncome() {
        double will_return = 0;
        for (Movie mv : this.movies) {
            will_return += mv.getSaled_money();
        }
        return will_return;
    }

    @Override
    public List<Movie> getAvailableMoviesByName(Time currentTime, String name) {
        List<Movie> will_return = new ArrayList<>();
        for (Movie mv: this.movies){
            if (mv.getName().equals(name) && mv.getTicketsLeft() > 0 && Time.minus(mv.getStartTime(),currentTime) > 0){
                will_return.add(mv);
            }
        }
        Collections.sort(will_return);
        return will_return;
    }

    public MovieHall get_mvhall(int num) {
        if (num <= 0 || num > order_mvhall) {
            return null;
        }
        for (MovieHall mh : this.movieHalls) {
            if (mh.getOrder() == num) {
                return mh;
            }
        }
        return null;
    }

    public List<Movie> getMoviesByHall(int hall) {
        List<Movie> will_return = new ArrayList<>();
        for (Movie mv : this.movies) {
            if (hashtable_mv_hall.getOrDefault(mv.getId(), -1) == hall) {
                will_return.add(mv);
            }
        }
        return will_return;
    }
}
