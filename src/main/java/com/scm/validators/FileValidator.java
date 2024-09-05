package com.scm.validators;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<ValidFile,MultipartFile> {


    private static final long MAX_FILE_SIZE=1024*1024*2;//5megabyte
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        
             if(file==null||file.isEmpty()){
              //  context.disableDefaultConstraintViolation();
               // context.buildConstraintViolationWithTemplate("file cannot be empty").addConstraintViolation();
                 return true;
             }
             if(file.getSize()>MAX_FILE_SIZE){
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("file size should be less than 2mb.").addConstraintViolation();
                 return false;
             }
              

             //resolution check
            // try {
            //     BufferedImage bufferedImage= ImageIO.read(file.getInputStream());

            //     if(bufferedImage.gr)
            // } catch (IOException e) {
            //     // TODO Auto-generated catch block
            //     e.printStackTrace();
            // }
            return true;
            
    }

}
