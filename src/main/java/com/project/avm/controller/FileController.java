package com.project.avm.controller;

import com.project.avm.model.FileRepository;
import com.project.avm.model.User;
import com.project.avm.model.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.avm.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
public class FileController{
    private FileRepository fileRepository;
    private UserRepository userRepository;

    public FileController(FileRepository fileRepository, UserRepository userRepository) {
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
    }
    
    @GetMapping(value="/files")
    public List<File> index(){
        return fileRepository.findAll();
    }

    @RequestMapping(value="/file/user/{id}", method = RequestMethod.GET)
    public List<File> getFilesFromUser(@PathVariable int id){
        return userRepository.getOne(id).getFile();
    }

    public User getUser(int id){
        return userRepository.getOne(id);
    }

    @RequestMapping(value="/file", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> create(@RequestParam("file") MultipartFile file, @RequestParam("user_id") int id) throws IOException {
        User user = getUser(id);
        java.io.File directory = new java.io.File("repository");
        if(!directory.exists()){
            if(directory.mkdir()){
                java.io.File convertfile = new java.io.File(directory.getPath()+"/"+file.getOriginalFilename());
                convertfile.createNewFile();
                FileOutputStream fout = new FileOutputStream(convertfile);
                fout.write(file.getBytes());
                fout.close();
                fileRepository.save(new File(file.getOriginalFilename(),convertfile.getPath() , Long.toString(file.getSize()), user));
                return new ResponseEntity<>("Upload feito com sucesso ", HttpStatus.OK);
            }

        }
        else{
            java.io.File convertfile = new java.io.File("repository/"+file.getOriginalFilename());
            convertfile.createNewFile();
            FileOutputStream fout = new FileOutputStream(convertfile);
            fout.write(file.getBytes());
            fout.close();
            fileRepository.save(new File(file.getOriginalFilename(), convertfile.getPath(), Long.toString(file.getSize()), user));
            return new ResponseEntity<>("Upload feito com sucesso ", HttpStatus.OK);

        }

        return new ResponseEntity<>("Ocorreu um erro no upload ", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/file/delete" , method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> delete(@RequestBody Map<String, String> body){
        int file_id = Integer.parseInt(body.get("file_id") );
        int user_id = Integer.parseInt(body.get("user_id"));
        File file = fileRepository.getOne(file_id);
        User user = userRepository.getOne(user_id);
        java.io.File filedelete = new java.io.File(file.getPath());
        if(filedelete.exists() && file.getUser().getId() == user.getId()){
            fileRepository.delete(file);
            filedelete.delete();
             return new ResponseEntity<>("Arquivo deletado", HttpStatus.NOT_FOUND);
        }else{
             return new ResponseEntity<>("Ocorreu um erro no delete ", HttpStatus.NOT_FOUND);
        }

        //return new ResponseEntity<>("Ocorreu um erro no delete ", HttpStatus.NOT_FOUND);
    }

    
}
