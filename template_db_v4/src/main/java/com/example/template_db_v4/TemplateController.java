package com.example.template_db_v4;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TemplateController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	@Autowired
	private TemplateService templateService;
	
	@GetMapping("/getTemplates")
	public Iterable<Template> getTemplates()
	{
		return templateService.findTemplates();
	}
	
	@PostMapping("/addTemplate")
	public String addNewTemplate(@RequestBody Template template)
	{
		return templateService.addTemplate(template);
		
	}
	@GetMapping("/getTemplate")
	public Template getTEmplate(@RequestParam Integer id)
	{
		return templateService.findTemplate(id);
	}
	@DeleteMapping("/deleteTemplate")
	public String delTemplate(@RequestParam Integer id)
	{
		return templateService.delTemplate(id);
	}
	@PatchMapping("/update/{id}")
    public ResponseEntity<Void> updateTemplate(@PathVariable("id") Integer id,@Valid @RequestBody TemplateUpdateDto templateUpdateDto) {
		return templateService.updateTemplate(id, templateUpdateDto);
    }
	@RequestMapping(value="/authenticate",method=RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try 
		{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		}
		catch (BadCredentialsException e) 
		{
			throw new Exception("Incorrect username or password", e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}
