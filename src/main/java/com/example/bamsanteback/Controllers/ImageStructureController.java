package com.example.bamsanteback.Controllers;

import com.example.bamsanteback.Dao.ImageStructureDAO;
import com.example.bamsanteback.Dao.StructureDao;
import com.example.bamsanteback.Entities.ImageStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
public class ImageStructureController {
    @Autowired
    ImageStructureDAO imageStructureDAO;
    @Autowired
    StructureDao structureDao;

    public String DIRECTORY="C:/Users/MHD/Desktop/BamTech/medecinProject/src/assets/imguploaded/";

    @PostMapping(value = "/imageClinique/edit/{structure}")
    public Boolean saveImageClinique(@PathVariable Integer structure, @RequestBody List<String> listB64){
        for(String imgStr : listB64)
            imageStructureDAO.save(
                    new ImageStructure(null, uploadClinique64String(imgStr,structure), new Date(), structureDao.findById(structure).get())
            );
        return true;
    }

    String uploadClinique64String(String urlimage, Integer idStructure){
        String fileName="";
        //On verifie si le type de contenu est different de text
        if (urlimage!=(null) && !urlimage.equals("")) {
            try {
                byte[] fileFromBase64 = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(urlimage);
                //On crée le nom de l'image
                fileName = "clinique_image_"+new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date())+"_"+idStructure+".png";
                //directory = chemin de l'image
                String directory = this.DIRECTORY+"clinique/"+ fileName;
                new FileOutputStream(directory).write(fileFromBase64); //upload l'image
                //preuveTacheEntity.setUrlfichier(imageName);  //Change la b64 en url
            } catch (IOException e) {
                System.out.println(e.getMessage());
                fileName = "";
            }
        }
        return fileName;
    }
}
