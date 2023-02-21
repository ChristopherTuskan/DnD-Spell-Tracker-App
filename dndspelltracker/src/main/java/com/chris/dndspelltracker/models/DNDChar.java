package com.chris.dndspelltracker.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="dndchars")
public class DNDChar {
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @NotEmpty(message="First Name is required!")
	    @Size(min=2, max=128, message="Must be at least 2 characters")
	    private String firstName;
	    
	    @NotEmpty(message="Last Name is required!")
	    @Size(min=2, max=128, message="Must be at least 2 characters")
	    private String lastName;
	    
	    @Max(20)
	    @Min(1)
	    private Integer level;
	    
	    @NotEmpty(message="Class is required!")
	    private String dndclass;
	    
	    @Column(updatable=false)
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date createdAt;
	    
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date updatedAt;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="user_id")
	    private User user;
	    
	    @ManyToMany
	    @JoinTable(name="dndcharacter_spell",
	    	joinColumns = @JoinColumn(name="dndcharacter_id"),
	    		inverseJoinColumns = @JoinColumn(name="spell_id"))
	    private List<Spell> spells = new ArrayList<>();
	    
	    public DNDChar() {}
	    
	    public String getName() {
	    	String name = this.firstName + " " + this.lastName;
	    	return name;
	    }
	    
	    public Long getId() {
			return id;
		}



		public void setId(Long id) {
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



		public Integer getLevel() {
			return level;
		}



		public void setLevel(Integer level) {
			this.level = level;
		}



		public String getDndclass() {
			return dndclass;
		}



		public void setDndclass(String dndclass) {
			this.dndclass = dndclass;
		}



		public Date getCreatedAt() {
			return createdAt;
		}



		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}



		public Date getUpdatedAt() {
			return updatedAt;
		}



		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}



		public User getUser() {
			return user;
		}



		public void setUser(User user) {
			this.user = user;
		}



		public List<Spell> getSpells() {
			return spells;
		}



		public void setSpells(List<Spell> spells) {
			this.spells = spells;
		}
		
		public boolean charHasSpell(Spell spell) {
			List <Spell> charSpells = this.getSpells();
			for (int i=0; i<charSpells.size(); i++) {
				Spell charSpell = charSpells.get(i);
				if (spell.getName().equals(charSpell.getName())) {
					return true;
				}
			}
			return false;
		}
		
		public Map<String, Boolean> displaySpellLevels(List<Spell> charSpells) {
			
			Map<String, Boolean> displayedLevels = new HashMap<String, Boolean>();
			
			for (int i=0; i<charSpells.size(); i++) {
				Spell currentSpell = charSpells.get(i);
				if (i==0) {
					displayedLevels.put(currentSpell.getName(), true) ;
				}
				if (i>0){
					Spell prevSpell = charSpells.get(i-1);
					if (currentSpell.getLevel().equals(prevSpell.getLevel())) {
						displayedLevels.put(currentSpell.getName(), false);
					}
					else {
						displayedLevels.put(currentSpell.getName(), true);
					}
				}
			}
			return displayedLevels;
		}
		
		


		@PrePersist
	    protected void onCreate(){
	        this.createdAt = new Date();
	    }
	    @PreUpdate
	    protected void onUpdate(){
	        this.updatedAt = new Date();
	    }
}
