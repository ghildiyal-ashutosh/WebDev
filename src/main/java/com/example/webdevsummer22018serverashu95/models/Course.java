package com.example.webdevsummer22018serverashu95.models;





import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Course 
{
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  private String title;
  private String ownedBy;
  
  @OneToMany (mappedBy = "course",orphanRemoval=true)
  private List<Module> module;
  
public List<Module> getModule() 
{
	return module;
}
public void setModule(List<Module> module) 
{
	this.module = module;
}
public String getOwnedBy() 
{
	return ownedBy;
}
public void setOwnedBy(String ownedBy) 
{
	this.ownedBy = ownedBy;
}
@Temporal(TemporalType.TIMESTAMP)
  private Date created;
  
  @Temporal(TemporalType.TIMESTAMP)
  private Date modified;
  
public int getId() 
  {
	return id;
	}
public void setId(int id) 
{
	this.id = id;
}
public String getTitle() 
{
	return title;
}
public void setTitle(String title) 
{
	this.title = title;
}
public Date getCreated()
{
	return created;
}
public void setCreated(Date created) 
{
	this.created = created;
}
public Date getModified() 
{
	return modified;
}
public void setModified(Date modified) 
{
	this.modified = modified;
}

}




