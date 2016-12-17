package org.ircenter.server.web.image;

import org.ircenter.serv.ImageServer;
import org.ircenter.serv.dataserver.ImageService;
import org.ircenter.server.dao.images.AddImageResult;
import org.ircenter.server.dao.images.ImageDAO;
import org.ircenter.server.service.user.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/image")
public class ImageController {

    private ImageServer imageServer;
    private ImageDAO imageDAO;

//    @RequestMapping(value = "/getimage/{id}/", method = RequestMethod.GET)
//    public ModelAndView getThemeMessages(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) throws IOException {
//
//        response.setContentType("image/jpeg");
//        response.setHeader("Cache-Control", "max-age=" + TimeUnit.MINUTES.toSeconds(10));
//        ServletOutputStream out = response.getOutputStream();
//
//        File file = new File("F:\\JOB\\IRCENTER\\CurrentProject\\ChurchDonation\\zagl.jpg");
//
//        byte[] b = new byte[(int) file.length()];
//        try {
//            FileInputStream fileInputStream = new FileInputStream(file);
//            fileInputStream.read(b);
//
//        } catch (FileNotFoundException e) {
//            System.out.println("File Not Found.");
//            e.printStackTrace();
//        }
//
//        out.write(b);
//        out.flush();
//        out.close();
//        return null;
//    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void uploadImage(HttpServletRequest request, HttpServletResponse httpResponse) throws IOException {
        List<MultipartFile> pictures = new ArrayList<MultipartFile>();
        if (request instanceof DefaultMultipartHttpServletRequest) {
            pictures = ((DefaultMultipartHttpServletRequest) request).getMultiFileMap().get("upload");
            if (pictures != null) {
                InputStream inputStream = pictures.get(0).getInputStream();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();

                int nRead;
                byte[] data = new byte[16384];

                while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }

                buffer.flush();
                AddImageResult result = imageDAO.addNewPhoto("", UserHelper.getUserId(), buffer.toByteArray());
                byte[] bytes = imageServer.getBytes(result.getImageId(), ImageService.IMG_SIZE_170_121);
                ServletOutputStream out = httpResponse.getOutputStream();
                String funNum = request.getParameter("CKEditorFuncNum");
                String filePath = "/image/getimage/2/" + result.getImageId();
                String res = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction(" + funNum + ", '" + filePath + "', \"\" );</script>";
                out.write(res.getBytes());
                out.flush();
                out.close();

                //String res = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction("+funNum+",  \"/getimage/2/9\", \"\" );</script>";
            }
        }
    }

    @RequestMapping(value = "/getimage/{size}/{id}", method = RequestMethod.GET)
    public void getImage(HttpServletResponse response, @PathVariable Integer size, @PathVariable Long id) throws IOException {
        if (size >= 0 && size <= 8) {
            byte[] bytes = imageServer.getBytes(id, size);
            ServletOutputStream out = response.getOutputStream();
            out.write(bytes);
            out.flush();
            out.close();
        }
    }

    @RequestMapping(value = "/media_remove", method = RequestMethod.GET)
    public String removeImage(@RequestParam Integer imageId) {
        imageDAO.deletePhoto(imageId);
        return "redirect:/admin/media";
    }


    @RequestMapping(value = "/load_new_avatar", method = RequestMethod.GET)
    public String getFriendsRequestForm(Model model, HttpServletRequest request) {
        return "new/load_new_avatar";
    }

    @Autowired
    public void setImageServer(ImageServer imageServer) {
        this.imageServer = imageServer;
    }

    @Autowired
    public void setImageDAO(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }
}
