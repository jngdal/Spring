package com.example.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.Foo;
import com.example.domain.FooCommand;
import com.example.repositories.FooRepository;

@Controller
@RequestMapping("/")
public class HelloController {

	@Autowired
	ServletContext context;
    @Autowired
    FooRepository FooRepository;
	@RequestMapping(method = RequestMethod.GET)
	public String welcome() {

		return "index";
	}

	@InitBinder("foocommand")
	void innit(WebDataBinder binder){
		
		binder.registerCustomEditor(Set.class,"foos",new CustomCollectionEditor(Set.class){
			
			@Override
			protected Object convertElement(Object element) {
				if(element instanceof Foo) {
					return element;
				}
				if(element instanceof String){
				try {
					int fooId = Integer.parseInt(element.toString());
					Foo foo = FooRepository.findId(fooId);
					
					return foo;
				} catch (Exception e) {
					System.out.println( e.getMessage());
					return null;
				}
				}
			return null;
			}
		
			
		});
		
	}
	
	
	@ModelAttribute("foolist")
    public Set<Foo> initializeFoos() {
		return FooRepository.findAll();
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "login";
	}
	@RequestMapping(value = "/colletion", method = RequestMethod.GET)
	public String colletion(ModelMap model) {
		FooCommand foocommand=new FooCommand();
		Set<Foo> foos=FooRepository.findAll();
		foos.removeIf(x->x.getId()==1);
		foocommand.setFoos(foos);
		foocommand.setId(1);
		for (Foo foo : foos) {
		System.out.println(foo.getName());	
		}
		model.addAttribute("foocommand",foocommand);
		
		return "collection";
	}
	
	@RequestMapping(value = "/colletion", method = RequestMethod.POST)
	public String postcolletion(@ModelAttribute("foocommand") FooCommand foocommand,BindingResult result, ModelMap model) {

		System.out.println(foocommand.getId());
		for (Foo foo : foocommand.getFoos()) {
			System.out.println(foo.getName());
		}
		return "redirect:/";
	}
	
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload() {

		return "fileupload/upload";
	}

	@RequestMapping(value = "/speed", method = RequestMethod.POST)
	public @ResponseBody boolean speedupload() {

		return true;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody boolean postupload(
			@RequestParam("fileupload") MultipartFile file) {

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				File a = new File("src/main/resources/static/images/"
						+ file.getOriginalFilename());
				System.out.println(a.canWrite() + a.getPath());
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(a));

				stream.write(bytes);
				stream.close();
				return true;
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
		/*
		 * String filename ="images/"+file.getOriginalFilename();
		 * System.out.println(filename);
		 * System.out.println(context.getRealPath("/"+filename)); File
		 * uploadFile = new File(context.getRealPath("/"+filename));
		 * System.out.print(uploadFile.getAbsolutePath()); try {
		 * file.transferTo(uploadFile);
		 * 
		 * BufferedImage oBufferedImage = ImageIO.read(uploadFile);
		 * 
		 * int type = oBufferedImage.getType() == 0 ?
		 * BufferedImage.TYPE_INT_ARGB : oBufferedImage.getType(); BufferedImage
		 * resizedImage = new BufferedImage(100, 130, type); Graphics2D g =
		 * resizedImage.createGraphics(); g.drawImage(oBufferedImage, 0, 0, 100,
		 * 130, null); g.dispose(); g.setComposite(AlphaComposite.Src);
		 * 
		 * g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		 * RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		 * g.setRenderingHint(RenderingHints.KEY_RENDERING,
		 * RenderingHints.VALUE_RENDER_QUALITY);
		 * g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		 * RenderingHints.VALUE_ANTIALIAS_ON); uploadFile.delete();
		 * 
		 * ImageIO.write( resizedImage, file.getOriginalFilename().substring(
		 * file.getOriginalFilename().lastIndexOf('.') + 1,
		 * file.getOriginalFilename().length()), new File( filename));
		 * 
		 * return true; } catch (Exception e) { // TODO Auto-generated catch
		 * block
		 * 
		 * return false; }
		 */
	}

}
