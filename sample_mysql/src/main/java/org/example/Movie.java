/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author llyxi
 */
public class Movie {
    private final SimpleIntegerProperty movieid;
    private final SimpleStringProperty moviename;
    private final SimpleStringProperty diretor;
    private final SimpleStringProperty mainActor;
    private final SimpleDoubleProperty Price;
    private final SimpleIntegerProperty duration;

    public Movie(int movieid, String moviename, String diretor, String  mainActor, double Price, int duration) {
        this.movieid = new SimpleIntegerProperty(movieid);
        this.moviename = new SimpleStringProperty(moviename);
        this.diretor = new SimpleStringProperty(diretor);
        this.mainActor = new SimpleStringProperty(mainActor);
        this.Price = new SimpleDoubleProperty(Price);
        this.duration = new SimpleIntegerProperty(duration);
    }
     public int getMovieid() {
		return this.movieid.get();
	}
	public void setMovieid(int movieid) {
        this.movieid.set(movieid);
    }
	public String getMoviename() {
		return this.moviename.get();
	}
	public void setMoviename(String moviename) {
		this.moviename.set(moviename);
	}
	public String getDiretor() {
		return this.diretor.get();
	}
	public void setDiretor(String diretor) {
		this.diretor.set(diretor);
	}
	public String getMainActor() {
		return this.mainActor.get();
	}
	public void setMainActor(String mainActor) {
		this.mainActor.set(mainActor);
	}
        public double getPrice(){
              return this.Price.get();
        }
        public void setPrice(double Price){
            this.Price.set(Price);
        }
        public int getDuration() {
		return this.duration.get();
	}
	public void setDuration(int duration) {
        this.duration.set(duration);
    }
}
