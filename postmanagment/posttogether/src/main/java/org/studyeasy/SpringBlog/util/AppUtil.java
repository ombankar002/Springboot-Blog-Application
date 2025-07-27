package org.studyeasy.SpringBlog.util;

import java.io.File;

public class AppUtil {
    public static String get_upload_path(String fileName){
        return new File("src\\main\\resources\\static\\uploads").getAbsolutePath() + "\\" + fileName;

    }
    
    
}
