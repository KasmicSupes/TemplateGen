package com.example.template_db_v4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;




@Service
@Transactional
public class TemplateService implements TemplateServiceI{
	
	@Autowired
	private TemplateRepository templateRepository;
	private List<String> finalPlaceholders=Arrays.asList("${name}");
	@Override
	public Iterable<Template> findTemplates()
	{
		return templateRepository.findAll();
	}
	
	@Override
	public ArrayList<String> findPlaceholders(String content)
	{
		ArrayList<String> allMatches = new ArrayList<String>();
		Matcher m = Pattern.compile("\\$\\{([^}]*)\\}").matcher(content);
		while (m.find()) 
		{
			int fixpHL=finalPlaceholders.size();
			for(int i=0;i<fixpHL;i++)
			{
				if(finalPlaceholders.get(i).equals(m.group()))
				{
					allMatches.add(m.group());
					System.out.println(m.group());
				}
			}
			//allMatches.add(m.group());
			
		}
		/*
		ArrayList<String> allMatches2 = new ArrayList<String>();
		int fixpHL=finalPlaceholders.size();
		int tempHL=allMatches.size();
		int flag;
		for(int i=0;i<tempHL;i++)
		{
			flag=0;
			/*for(int j=0;j<fixpHL;j++)
			{
				if(allMatches.get(i)==finalPlaceholders.get(j))
				{
					flag+=1;
				}
			}
			if(allMatches.get(i).equals(finalPlaceholders.get(0)))
			{
				allMatches2.add(allMatches.get(i));
			}
		}*/
		return allMatches;
	}

	@Override
	public String addTemplate(Template template) throws TemplateNotFoundException
	{
		//try {
			ArrayList<String> temp=findPlaceholders(template.getContent());

		template.setPlaceholders(findPlaceholders(template.getContent()));
		templateRepository.save(template);
		return "saved";
		//}
		//catch(java.sql.ConstraintViolationException e)
		//{
		//	throw e;
		//}
	}
	@Override
	public Template findTemplate(Integer id)
	{
		return templateRepository.findById(id).orElseThrow(() -> new TemplateNotFoundException("User not found with id :"+id));
		
	}
	@Override
	public String delTemplate(Integer id)
	{
		templateRepository.findById(id).orElseThrow(() -> new TemplateNotFoundException("User not found with id :"+id));
		templateRepository.deleteById(id);
		return "deleted";
				
	}
	@Override
    public ResponseEntity<Void> updateTemplate(Integer id,TemplateUpdateDto templateUpdateDto) {
    	Template template = templateRepository.findById(id).orElseThrow(() -> new TemplateNotFoundException("User not found with id :"+id));
        JsonNullableUtils.changeIfPresent(templateUpdateDto.getOrganisation_id(), template::setOrganisation_id);
        JsonNullableUtils.changeIfPresent(templateUpdateDto.getType(), template::setType);
        JsonNullableUtils.changeIfPresent(templateUpdateDto.getContent(), template::setContent);
        template.setPlaceholders(findPlaceholders(template.getContent()));
        templateRepository.save(template);
        return ResponseEntity.noContent().build();

    }
	
	
	
}
