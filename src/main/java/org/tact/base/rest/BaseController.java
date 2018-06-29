package org.tact.base.rest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tact.image.TextWriter;
import org.tact.image.TextWriterTamil;

@ComponentScan("org.tact")
@RestController
@RequestMapping(value = "/base")
@SuppressWarnings("unchecked")
public class BaseController {
	
    @RequestMapping(value = "", method = RequestMethod.GET)
    public <T> T listUsers() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("one", "two");
        map.put("three", "four");
        map.put("five", "six");
        map.put("seven", textWriterTmail.getSomeString("raja"));
        
        return (T) map;
    }
    
    @Autowired
    TextWriterTamil textWriterTmail;
    
    @Autowired
    TextWriter textWriter;
    
    /**
     * 
     * @param file
     * @param redirectAttributes
     * @return
     * 
     * possible url:
     * 		/base/upload
     *		http://localhost:1878/base/upload
     */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
    public <T> T upload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {
    	
    	if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "");
            
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            
            map.put("result", 102);
            map.put("error", "Please select a file to upload");
            
            return (T) map;
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get("d:/csp/test/" + file.getOriginalFilename());
            Files.write(path, bytes);

            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("result", 0);
            map.put("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
            map.put("error", "0");
            
            return (T) map;

        } catch (IOException e) {
        	
        	e.printStackTrace();
            
        	Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("result", 0);
            map.put("message", "File upload Error : "+e.getMessage());
            map.put("error", "0");
            
            return (T) map;
        }
    }
	
	/**
     * 
     * @param file
     * @param redirectAttributes
     * @return
     * 
     * possible url:
     * 		/base/create/meme
     *		http://localhost:1878/base/create/meme
     */	
	@RequestMapping(value = "/create/meme", method = RequestMethod.POST)
    public <T> T createMeme(@RequestParam("file") MultipartFile file,
    		@RequestParam("content") String content) {
		
		// check content for 30 characters max; if more, throw an exception
		
		// restrict non image files
		
		// restrict non JPG files
    	
    	if (file.isEmpty()) {            
            
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            
            map.put("result", 102);
            map.put("error", "Please select a file to upload");
            
            return (T) map;
        }

        try {
        	
        	String fileName = file.getOriginalFilename();
        	String filePath = "d:/csp/test/" + fileName;

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(filePath);
            Files.write(path, bytes);
                        
            
            // successfully uploaded. Now it is time to create a meme
            boolean createdFlag = textWriter.createMeme(filePath, content);
            
            if(createdFlag){
            	Map<String, Object> map = new LinkedHashMap<String, Object>();
                map.put("result", 0);
                map.put("message", "Meme created '" + filePath + "'");
                map.put("error", "0");
                
                return (T) map;
            } else {
            	Map<String, Object> map = new LinkedHashMap<String, Object>();
                map.put("result", 254);
                map.put("message", "Something went wrong");
                map.put("error", "Unknown error");
                
                return (T) map;
            }            

        } catch (Exception e) {
        	
        	e.printStackTrace();
            
        	Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("result", 0);
            map.put("message", "File upload Error : "+e.getMessage());
            map.put("error", "0");
            
            return (T) map;
        }
    }
	
	/**
     * 
     * @param file
     * @param redirectAttributes
     * @return
     * 
     * possible url:
     * 		/base/create/tamil/meme
     *		http://localhost:1878/base/create/tamil/meme
     */	
	@RequestMapping(value = "/create/tamil/meme", method = RequestMethod.POST)
    public <T> T createTamilMeme(@RequestParam("file") MultipartFile file
            ) {
    	
    	if (file.isEmpty()) {
            
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            
            map.put("result", 102);
            map.put("error", "Please select a file to upload");
            
            return (T) map;
        }

        try {
        	
        	String fileName = file.getOriginalFilename();
        	String filePath = "d:/csp/test/" + fileName;

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(filePath);
            Files.write(path, bytes);
                        
            
            // successfully uploaded. Now it is time to create a meme
            boolean createdFlag = textWriterTmail.createMeme(filePath, "இவன் ரெம்ப நல்லவன் போல");
            
            if(createdFlag){
            	Map<String, Object> map = new LinkedHashMap<String, Object>();
                map.put("result", 0);
                map.put("message", "Meme created '" + filePath + "'");
                map.put("error", "0");
                
                return (T) map;
            } else {
            	Map<String, Object> map = new LinkedHashMap<String, Object>();
                map.put("result", 254);
                map.put("message", "Something went wrong");
                map.put("error", "Unknown error");
                
                return (T) map;
            }            

        } catch (Exception e) {
        	
        	e.printStackTrace();
            
        	Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("result", 0);
            map.put("message", "File upload Error : "+e.getMessage());
            map.put("error", "0");
            
            return (T) map;
        }
    }
}
