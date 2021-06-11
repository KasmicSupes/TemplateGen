package com.example.template_db_v4;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TemplateRepository extends CrudRepository<Template,Integer>{

	//public List<Template> findByOrganisation_idAndType(String organisation_Id,String type);
}

