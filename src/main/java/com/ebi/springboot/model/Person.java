package com.ebi.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.ebi.springboot.validation.SecondGroup;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Entity class for a person
 * @author Yogesh Patil
 *
 */
@Entity
@Table(name = "PERSON")
public class Person {
		@JsonIgnore
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
		
		@NotEmpty(message = "firstName.null")
	    @Size(min = 3, max = 150, message = "firstName.length", groups = SecondGroup.class)
	 	@JsonProperty("first_name")
	 	@Column(name = "first_name", length = 150, nullable = false)
	    private String firstName;

		@NotEmpty(message = "lastName.null")
	    @Size(min = 3, max = 150, message = "lastName.length", groups = SecondGroup.class)
	 	@JsonProperty("last_name")
	    @Column(name = "last_name", length = 150, nullable = false)
	    private String lastName;
		
		@JsonProperty("age")
		@NotEmpty(message = "age.null")
		@Column(name = "age", length = 3, nullable = false)
	    private String age;
		
		@NotEmpty(message = "favouriteColour.null")
		@JsonProperty("favourite_colour")
	    @Column(name = "favourite_colour", nullable = false)
	    private String favouriteColour;

		
		
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
		}

		public String getFavouriteColour() {
			return favouriteColour;
		}

		public void setFavouriteColour(String favouriteColour) {
			this.favouriteColour = favouriteColour;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((age == null) ? 0 : age.hashCode());
			result = prime * result + ((favouriteColour == null) ? 0 : favouriteColour.hashCode());
			result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
			result = prime * result + (int) (id ^ (id >>> 32));
			result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
			if (favouriteColour == null) {
				if (other.favouriteColour != null)
					return false;
			} else if (!favouriteColour.equals(other.favouriteColour))
				return false;
			if (firstName == null) {
				if (other.firstName != null)
					return false;
			} else if (!firstName.equals(other.firstName))
				return false;
			if (id != other.id)
				return false;
			if (lastName == null) {
				if (other.lastName != null)
					return false;
			} else if (!lastName.equals(other.lastName))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
					+ ", favouriteColour=" + favouriteColour + "]";
		}

		
	    
	
}
