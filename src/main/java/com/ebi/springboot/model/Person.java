package com.ebi.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Entity class for a person
 * @author Yogesh Patil
 *
 */
@Entity
@Table(name = "PERSON")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	 	@Column(name = "first_name", length = 150, nullable = false)
	    private String first_name;

	    @Column(name = "last_name", length = 150, nullable = false)
	    private String last_name;

		@Column(name = "age", length = 3, nullable = false)
	    private String age;

	    @Column(name = "favourite_colour", nullable = false)
	    private String favourite_colour;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getFirst_name() {
			return first_name;
		}

		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}

		public String getLast_name() {
			return last_name;
		}

		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
		}

		public String getFavourite_colour() {
			return favourite_colour;
		}

		public void setFavourite_colour(String favourite_colour) {
			this.favourite_colour = favourite_colour;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((age == null) ? 0 : age.hashCode());
			result = prime * result + ((favourite_colour == null) ? 0 : favourite_colour.hashCode());
			result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
			result = prime * result + (int) (id ^ (id >>> 32));
			result = prime * result + ((last_name == null) ? 0 : last_name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Person other = (Person) obj;
			if (age == null) {
				if (other.age != null)
					return false;
			} else if (!age.equals(other.age))
				return false;
			if (favourite_colour == null) {
				if (other.favourite_colour != null)
					return false;
			} else if (!favourite_colour.equals(other.favourite_colour))
				return false;
			if (first_name == null) {
				if (other.first_name != null)
					return false;
			} else if (!first_name.equals(other.first_name))
				return false;
			if (id != other.id)
				return false;
			if (last_name == null) {
				if (other.last_name != null)
					return false;
			} else if (!last_name.equals(other.last_name))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Person [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", age=" + age
					+ ", favourite_colour=" + favourite_colour + "]";
		}
	    
	    
	
}
